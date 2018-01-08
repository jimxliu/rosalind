import java.util.*;
import java.io.*;
/*
 * A DNA string is a reverse palindrome if it is equal to its reverse complement. For instance, GCATGC is a reverse palindrome because its reverse complement is GCATGC.
 * Given: A DNA string of length at most 1 kbp in FASTA format.
 * Return: The position and length of every reverse palindrome in the string having length between 4 and 12. You may return these pairs in any order.
*/
public class LocateReversePalindrome {
  public static Map<Character,Character> basePair;
  static {
    basePair = new HashMap<Character,Character>();
    basePair.put('A','T');
    basePair.put('T','A');
    basePair.put('C','G');
    basePair.put('G','C');
  }
  /* @param str The string to be checked if itself is reverse palindrome
   * String length between 4 and 12 (inclusive)
   * The string length must be an even number
   * @return true if the string is reverse palindrome
  */
  public boolean isReversePalindrome(String str) {
    if(str.length() == 2) {
      return basePair.get(str.charAt(0)) == str.charAt(1);
    } else {
      if(basePair.get(str.charAt(0)) == str.charAt(str.length() - 1)) {
        return isReversePalindrome(str.substring(1,str.length() - 1));
      } else {
        return false;
      }
    }
  }
  /*
   * @param sequence DNA sequence
   * @param report if true, report the starting positions and lengths of all the reverse palindrome strings
   * @return print out all the substrings in the sequence that are reverse palindrome
  */
  public void printReversePalindrome(String sequence, boolean report ) {
    for(int start = 0; start < sequence.length() - 3; start++) {
      int end = start + 12;
      if(end > sequence.length()) {
        end = sequence.length();
        if((end-start) % 2 == 1){
          end -= 1;
        }
      }
      for(; end >= start + 4; end -= 2) {
        String subSeq = sequence.substring(start,end);
        if(this.isReversePalindrome(subSeq)){
          //System.out.println(subSeq);
          if(report) {
            // print out start position and length of each reverse palindrome string
            System.out.format("%d %d\n", start + 1, end - start);
          }
        }
      }
    }
  }
  // Overload printReversePalindrome
  public void printReversePalindrome(String sequence) {
      this.printReversePalindrome(sequence,false);
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
      //System.out.println(sequence);
      LocateReversePalindrome lrp = new LocateReversePalindrome();
      lrp.printReversePalindrome(sequence,true);
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
