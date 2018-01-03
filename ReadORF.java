import java.util.*;
import java.io.*;

public class ReadORF {
  public static final Map<String,String> codonTable;
  static {
    Map<String,String> tempMap = new HashMap<String,String>();
    tempMap.put("TTT","F");tempMap.put("CTT","L");tempMap.put("ATT","I");tempMap.put("GTT","V");
    tempMap.put("TTC","F");tempMap.put("CTC","L");tempMap.put("ATC","I");tempMap.put("GTC","V");
    tempMap.put("TTA","L");tempMap.put("CTA","L");tempMap.put("ATA","I");tempMap.put("GTA","V");
    tempMap.put("TTG","L");tempMap.put("CTG","L");tempMap.put("ATG","M");tempMap.put("GTG","V");
    tempMap.put("TCT","S");tempMap.put("CCT","P");tempMap.put("ACT","T");tempMap.put("GCT","A");
    tempMap.put("TCC","S");tempMap.put("CCC","P");tempMap.put("ACC","T");tempMap.put("GCC","A");
    tempMap.put("TCA","S");tempMap.put("CCA","P");tempMap.put("ACA","T");tempMap.put("GCA","A");
    tempMap.put("TCG","S");tempMap.put("CCG","P");tempMap.put("ACG","T");tempMap.put("GCG","A");
    tempMap.put("TAT","Y");tempMap.put("CAT","H");tempMap.put("AAT","N");tempMap.put("GAT","D");
    tempMap.put("TAC","Y");tempMap.put("CAC","H");tempMap.put("AAC","N");tempMap.put("GAC","D");
    tempMap.put("TAA","Stop");tempMap.put("CAA","Q");tempMap.put("AAA","K");tempMap.put("GAA","E");
    tempMap.put("TAG","Stop");tempMap.put("CAG","Q");tempMap.put("AAG","K");tempMap.put("GAG","E");
    tempMap.put("TGT","C");tempMap.put("CGT","R");tempMap.put("AGT","S");tempMap.put("GGT","G");
    tempMap.put("TGC","C");tempMap.put("CGC","R");tempMap.put("AGC","S");tempMap.put("GGC","G");
    tempMap.put("TGA","Stop");tempMap.put("CGA","R");tempMap.put("AGA","R");tempMap.put("GGA","G");
    tempMap.put("TGG","W");tempMap.put("CGG","R");tempMap.put("AGG","R");tempMap.put("GGG","G");
    codonTable = Collections.unmodifiableMap(tempMap);
  }

  public static String reverseComplement(String str) {
    Map<Character,Character> basePair = new HashMap<Character,Character>();
    basePair.put('A','T');
    basePair.put('T','A');
    basePair.put('C','G');
    basePair.put('G','C');

    // Reversed complementary string
    StringBuilder revCompSB = new StringBuilder();
    for(int i = str.length() - 1; i >= 0; i--) {
      // Add the complementary base of the base on the original string to the reversed compelment string
      revCompSB.append(basePair.get(str.charAt(i)));
    }
    //System.out.format("Reversed complementary sequence: %s\n",revCompStr.toString());
    return revCompSB.toString();
  }

  public static String translate(String dna) {
    String protein = "";
    for(int i=0; i < dna.length() - 2; i += 3) {
      String aa = codonTable.get(dna.substring(i,i+3));
      protein += aa;
    }
    return protein;
  }

  public static void findORF (String sequence, Set<String> list) {
    // Find a start codon and a nearest stop codon and print out the protein sequence of this subsequence
    while(sequence.length() >= 6) { // the sequence cant be short than just a start and a stop codon (6 na)
      int start = -1;
      if(-1 < (start = sequence.indexOf("ATG"))) { // Found the first occuring start codon
        for(int i = start + 3; i < sequence.length() - 2; i += 3) { // three bases a codon, ORF
          if(codonTable.get(sequence.substring(i,i+3)).equals("Stop")) { // If a stop codon can be found
            int stop = i;
            String candidate = translate(sequence.substring(start,stop));
            list.add(candidate);
            //System.out.format("start from: %d, stop at: %d\n",start,stop);
            break;
          }
        }
        sequence = sequence.substring(start+1);
      } else { // Couldn't find a start codon
        break;
      }
    }
  }

  public static Set<String> getProteins(String dna) {
    Set<String> list = new HashSet<String>(); // List to contain all the possible protein candidates
    findORF(dna,list);
    findORF(reverseComplement(dna),list);
    return list;
  }

  public static String readFasta(String filename) {
    BufferedReader br = null;
    String sequence = "";
    try {
      // Read the fasta file using bufferedreader
      File file = new File(filename);
      br = new BufferedReader(new FileReader(file));
      String fastaID;
      fastaID = br.readLine();
      System.out.println(fastaID);

      String currentLine = "";
      while((currentLine = br.readLine()) != null) {
        sequence += currentLine;
      }
      System.out.println(sequence);
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      try {
        if(br != null) {
          br.close();
        }
        //System.out.println("Read the fasta successfully!");
      } catch(Exception ex) {
        ex.printStackTrace();
      }
    }
    return sequence;
  }

  public static void main(String[] args) {
    if(args.length < 1){ // Must run the program with the input file
      System.err.println("No file input");
      System.exit(1);
    }
    //System.out.println(CodonTable.table);
    String dnaSequence = ReadORF.readFasta(args[0]); // Get the sequence from the fasta file
    Set<String> candidates = null;
    candidates = readFasta.getProteins(dnaSequence); 

    if(candidates != null){
     System.out.println("The possible protein candidates are:");
     for(String protein : candidates){
       System.out.println(protein);
     }
    } else {
     System.out.println("No open reading frame found!");
    }
  }
}
