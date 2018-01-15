/*
 * For a collection of strings, a larger string containing every one of the smaller strings as a substring is called a superstring.
 * By the assumption of parsimony, a shortest possible superstring over a collection of reads serves as a candidate chromosome.
 * Given: At most 50 DNA strings of approximately equal length, not exceeding 1 kbp, in FASTA format (which represent reads deriving from the same strand of a single linear chromosome).
*        The dataset is guaranteed to satisfy the following condition: there exists a unique way to reconstruct the entire chromosome from these reads by gluing together pairs of reads that overlap by more than half their length.
 * Return: A shortest superstring containing all the given strings (thus corresponding to a reconstructed chromosome).
 * input file name: rosalind_long.txt
 */

import java.util.*;
import java.io.*;
public class ShortestOverlappingSuperstring {
  /* @param s1
   * @param s2
   * @return the length of the max overlapped region
   */
  public int findOverlappingPair(String s1, String s2){
    int overlap12 = 0; // stores maxinmu overlap length of the matching prefix and suffix
    int overlap21 = 0;
    // Check if s1's end overlaps s2's start
    for(int i = 0; i < s1.length(); i ++){
      String s1Sub = s1.substring(i);
      if(s2.startsWith(s1Sub)){
        overlap12 = s1.length() - i;
        break;
      }
    }
    // Check if s1's end overlaps s2's start
    for(int i = 0; i < s2.length(); i ++){
      String s2Sub = s2.substring(i);
      if(s1.startsWith(s2Sub)){
        overlap21 = s2.length() - i;
        break;
      }
    }
    return (overlap12 > overlap21) ? overlap12 : overlap21;
  }

  /* @param s1,s2
   * @param overlap The length of overlapped region
   * @return combined superstring out of the pair of the overlapped strings
   */
  public String combineString(String s1, String s2, int overlap){
    if(s1.contains(s2.substring(0,overlap))){
      return s1 + s2.substring(overlap);
    } else {
      return s2 + s1.substring(overlap);
    }
  }

  /* @param arr The array of all the reads in String
   * @param len Length of the array
   * @return The shortest common superstring of all the reads, aka, the reconstructed sequence
   */
  public String findShortestSuperstring(String[] arr, int len) {
    // run len - 1 times to consider every pair
    while(len != 1){
      int maxOverlap = 0; // maximu overlap length of all pairs
      int p,q; // p and q store the indeces of the pair with max overlap length
      p = q = 0;;
      // Consider every pair
      for(int i = 0; i < len; i++){
        for(int j = i + 1; j < len; j++){
          // overlap stores the overlap length of the two strings being compared
          int overlap = this.findOverlappingPair(arr[i],arr[j]);
          if(maxOverlap < overlap){
            maxOverlap = overlap;
            p = i;
            q = j;
          }
        }
      }
      // reduce (but not really reduce) lengh of the arr by 1 by combining two strings
      len--;
      if(maxOverlap == 0){ // if no overlap, append the last string to the first
        arr[0] += arr[len];
      } else { // if there's a max overlap
        arr[p] = this.combineString(arr[p],arr[q],maxOverlap); // copy combined string to index q
        arr[q] = arr[len]; // replace the string at q with the last indexed string
      }
    }
    return arr[0];
  }
  public static void main(String[] args) {
    if(args.length != 1){
      System.err.println("Invalid fasta file input");
      System.exit(1);
    }
    try{
      BufferedReader br = new BufferedReader(new FileReader(args[0]));
      LinkedList<String> list = new LinkedList<String>();
      LinkedList<String> ids = new LinkedList<String>();
      String line = "";
      while((line = br.readLine()) != null) {
        String sequence = "";
        if(line.startsWith(">")){
          ids.add(line.substring(1));
        } else {
          sequence += line;
        }
        while((line = br.readLine()) != null){
          if(line.startsWith(">")){
            ids.add(line.substring(1));
            break;
          } else {
            sequence += line;
          }
        }
        list.add(sequence);
      }
      int n = list.size();
      String[] arr = new String[n];
      arr = list.toArray(arr);
      ShortestOverlappingSuperstring sos = new ShortestOverlappingSuperstring();
      System.out.println("Superstring:");
      System.out.println(sos.findShortestSuperstring(arr,n));
    } catch (Exception ex){
      ex.printStackTrace();
    }
  }
}
