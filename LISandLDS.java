import java.util.*;
import java.io.*;
/* Longest Increasing and Decreasing Subsequences
 * A subsequence of a permutation is a collection of elements of the permutation in the order that they appear. For example, (5, 3, 4) is a subsequence of (5, 1, 3, 4, 2).
 * A subsequence is increasing if the elements of the subsequence increase, and decreasing if the elements decrease. For example, given the permutation (8, 2, 1, 6, 5, 7, 4, 3, 9), an increasing subsequence is (2, 6, 7, 9), and a decreasing subsequence is (8, 6, 5, 4, 3). You may verify that these two subsequences are as long as possible.
 * Given: A positive integer n≤10000n≤10000 followed by a permutation ππ of length nn.
 * Return: A longest increasing subsequence of ππ, followed by a longest decreasing subsequence of ππ.
 * Input file: rosalind_lgis.txt
 */
public class LISandLDS {
  /* Use dynamic programming to calculate the lengths of LIS and LDS of a given int sequence, and print out both sequences
   * @param arr The int array representative of the int sequence
   * @param n The total number of numbers in the sequence
   */
  public static void printLongestInDecreasingSubsequence(int[] arr, int n) {
    // lis array stores the length of the LIS with the indexed element being the end element
    // index array stores the index of the previous element in the LIS and LDS
    // ....Here to initialize the arrays
    int[] lis = new int[n]; // Socres of each index for LIS
    int[] lds = new int[n]; // Socres of each index for LDS
    int[] indexInc = new int[n]; // stores the index of the previous element in the LIS
    int[] indexDec = new int[n]; // stores the index of the previous element in the LDS
    // initialize above arrays
    for(int i = 0; i < n; i++){
      lis[i] = 1;
      lds[i] = 1;
      indexInc[i] = i;
      indexDec[i] = i;
    }
    // Compute lis values from bottom up
    for(int i = 0; i < n ; i++){
      for(int j = 0; j < i; j++) {
        // If find an element satisfying the following condition, update lis[i]
        if(arr[j] < arr[i] && lis[i] < lis[j] + 1) {
          lis[i] = lis[j] + 1;
          indexInc[i] = j;
        }
        // If find an element satisfying the following condition, update lds[i]
        if(arr[j] > arr[i] && lds[i] < lds[j] + 1) {
          lds[i] = lds[j] + 1;
          indexDec[i] = j;
        }
      }
    }
    // Get the length of LIS
    int endIndexInc = 0; // index of end element of LIS
    int maxInc = 0; // Length of LIS
    int endIndexDec = 0; // index of end element of LIS
    int maxDec = 0; // Length of LIS
    for(int i = 0; i < n; i++) {
      if(maxInc < lis[i]){
        maxInc = lis[i];
        endIndexInc = i;
      }
      if(maxDec < lds[i]){
        maxDec = lds[i];
        endIndexDec = i;
      }
    }
    System.out.format("Length of LIS is: %d%n",maxInc);
    System.out.format("Length of LDS is: %d%n",maxDec);
    // Print out the LIS and LDS sequences
    int ki = endIndexInc;
    int kd = endIndexDec;
    LinkedList<Integer> sequenceInc = new LinkedList<Integer>();
    LinkedList<Integer> sequenceDec = new LinkedList<Integer>();
    for(int i = 0; i < maxInc; i++){
      sequenceInc.addFirst(arr[ki]);
      ki= indexInc[ki];
    }
    System.out.println("LIS sequence:");
    //System.out.println(sequenceInc);
    for(Integer num : sequenceInc){
      System.out.format("%d ", num);
    }

    for(int i = 0; i < maxDec; i++){
      sequenceDec.addFirst(arr[kd]);
      kd = indexDec[kd];
    }
    System.out.println("\nLDS sequence:");
    //System.out.println(sequenceDec);
    for(Integer num : sequenceDec){
      System.out.format("%d ", num);
    }
  }

  public static void main(String[] args) {
    if(args.length != 1){
      System.out.println("Incorrect number of input file.");
      System.exit(1);
    }
    String filename = args[0];
    try{
      FileReader fr = new FileReader(filename);
      BufferedReader br = new BufferedReader(fr);

      int n = Integer.parseInt(br.readLine());

      String inputString= br.readLine();
      String [] arrayOfStr = inputString.split(" ");
      int [] intArray = new int[n];
      if(n != arrayOfStr.length){
        System.out.println("Split went wrong.");
        System.exit(2);
      }
      for(int i = 0; i < n; i++){
        intArray[i] = Integer.parseInt(arrayOfStr[i]);
      }

      LISandLDS.printLongestInDecreasingSubsequence(intArray,n);
      // close BufferedReader
      br.close();
    }
    catch(FileNotFoundException ex) {
      System.out.println("Unable to open file '" + filename + "'");
    }
    catch(IOException ex) {
      System.out.println("Error reading file '" + filename + "'");
            // Or we could just do this:
            // ex.printStackTrace();
    }
  }
}
