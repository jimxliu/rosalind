import java.util.*;
import java.io.*;

/* After identifying the exons and introns of an RNA string, we only need to delete the introns and concatenate the exons to form a new string ready for translation.
 * Given: A DNA string ss (of length at most 1 kbp) and a collection of substrings of ss acting as introns. All strings are given in FASTA format.
 * Return: A protein string resulting from transcribing and translating the exons of ss. (Note: Only one solution will exist for the dataset provided.)
 */

public class SplicingRNA {
  // Codon table
  public static final Map<String,String> table;
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
    table = Collections.unmodifiableMap(tempMap);
  }
  /* Splice introns out of the complementary DNA sequence
   * @param sequence DNA sequence
   * @param introns The list of intron sequences
   * @return post-splicing DNA sequence
   */
  public String splice(String sequence, ArrayList<String> introns) {
    for(String intron : introns) {
      sequence = sequence.replace(intron, "");
    }
    return sequence;
  }

  /* @param sequence cDNA sequence
   * @return protein sequence
   */
  public String translate(String dna) {
    String protein = "";
    for(int i=0; i < dna.length() - 2; i += 3) {
      String aa = table.get(dna.substring(i,i+3));
      if(aa.equals("Stop")) { // Stop at the stop codon, or to the end
        break;
      }
      protein += aa;
    }
    return protein;
  }

  public static void main(String[] args) throws IOException {
    if(args.length < 1 || args.length > 1) {
      System.out.println("Not input fasta file or too many input files.");
      System.exit(1);
    }
    // The fasta file consists of an ID and string of the target sequence and IDs and strings of introns
    BufferedReader br = null; // Buffered reader to read the fasta file
    ArrayList<String> introns = new ArrayList<String>();
    br = new BufferedReader(new FileReader(new File(args[0])));
    String fastaID = br.readLine();
    System.out.println(fastaID);

    String sequence = ""; // DNA sequence
    String subSequence = ""; // sequence in the file is separated by lines
    while((subSequence = br.readLine()) != null) {
      if(!subSequence.startsWith(">"))
        sequence += subSequence;
      else // exit and starts reading introns
        break;
    }

    // Start reading intron sequencs. Unlike DNA sequence, one intron sequence per line
    // Add them to the arraylist
    String intron = "";
    while((intron = br.readLine()) != null) {
      if(!intron.startsWith(">"))
        introns.add(intron);
    }
    //System.out.format("Sequence: %n%s%n",sequence);
    //System.out.format("Introns: %s",introns);
    // code above works well
    SplicingRNA srna = new SplicingRNA();
    String splicedCDNA = srna.splice(sequence,introns);
    String proteinSeq = srna.translate(splicedCDNA);
    System.out.format("Translated protein sequence: %s%n",proteinSeq);
  }
}
