/* Note that a matching contained in the basepair edges will represent one possibility for base pairing interactions in ss, as shown in Figure 5. For such a matching to exist, ss must have the same number of occurrences of 'A' as 'U' and the same number of occurrences of 'C' as 'G'.
 * Given: An RNA string ss of length at most 80 bp having the same number of occurrences of 'A' as 'U' and the same number of occurrences of 'C' as 'G'.

 * Return: The total possible number of perfect matchings of basepair edges in the bonding graph of ss.
 * input file name: dataset/rosalind_pmuch.txt
*/

import java.io.*;
import java.math.BigInteger;
public class PerfectMatchings {
  /* @param str The RNA string contains the same number of occurrences of 'A' as 'U' and the same number of occurrences of 'C' as 'G'.
   * @return The total possible number of perfect matchings of basepair edges in the bonding graph of str
  */
  public BigInteger getPerfectMatchings(String str) {
    // Count how many A's(or U's) and C's(or G's) in the string
    int occurA = this.countNucleotides(str,'A');
    int occurG = this.countNucleotides(str,'G');
    System.out.format("A occurs:%d, G occurs: %d%n",occurA,occurG);
    return this.factorial(occurA).multiply(this.factorial(occurG));
  }

  public BigInteger factorial(int n){
    BigInteger result = new BigInteger("1");
    for(int i = 2; i <= n; i++){
      result = result.multiply(BigInteger.valueOf(i));
    }
    return result;
  }

  /* @param sequence a nucleotide sequence
   * @param nu Nucleotide: A, C, G, U or T depending on RNA or DNA
   * @return Number of occurences of the nucleotide
   */
  public int countNucleotides(String sequence, char nu) {
    int count = 0;
    for(int i = 0; i < sequence.length(); i++) {
      if(sequence.charAt(i) == nu) {
        count++;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    if(args.length < 1){ // Must run the program with the input file
      System.err.println("No file input");
      System.exit(1);
    }
    BufferedReader br = null;
    try {
      // Read the fasta file using bufferedreader
      File file = new File(args[0]);
      br = new BufferedReader(new FileReader(file));
      String fastaID;
      fastaID = br.readLine();
      System.out.println(fastaID);

      String sequence = "";
      String currentLine = "";
      while((currentLine = br.readLine()) != null) {
        sequence += currentLine;
      }
      System.out.println(sequence);
      PerfectMatchings pm = new PerfectMatchings();
      BigInteger totalPM = pm.getPerfectMatchings(sequence);
      System.out.format("Total number of perfect matching: %s%n",totalPM.toString());
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      try {
        if(br != null) {
          br.close();
        }
        System.out.println("Read the fasta successfully!");
      } catch(Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
