import java.io.Console;
import java.util.Arrays;

/*
 * Assume that an alphabet AA has a predetermined order; that is, we write the alphabet as a permutation A=(a1,a2,…,ak)A=(a1,a2,…,ak), where a1<a2<⋯<aka1<a2<⋯<ak. For instance, the English alphabet is organized as (A,B,…,Z)(A,B,…,Z).
 * Given two strings ss and tt having the same length nn, we say that ss precedes tt in the lexicographic order (and write s<Lexts<Lext) if the first symbol s[j]s[j] that doesn't match t[j]t[j] satisfies sj<tjsj<tj in AA.
 * Given: A collection of at most 10 symbols defining an ordered alphabet, and a positive integer nn (n≤10n≤10).
 * Return: All strings of length nn that can be formed from the alphabet, ordered lexicographically (use the standard order of symbols in the English alphabet).
 */

public class EnumerateKmers {
  /* recursively enumerate and print out all the kmers in an alphabetical order
   * @param str A string of all the symbols
   * @param array An array representative of the string formed by the symbols (not necessary all)
   * @param n The number of characters already filled in the array
   */
  public void enumerate(String str, char[] array, int k) {
    if(k == array.length){
      String result = new String(array);
      System.out.println(result);
    } else {
      for(int i = 0; i < str.length(); i++){
        array[k] = str.charAt(i);
        this.enumerate(str,array,k+1);
      }
    }
  }

  public static void main(String[] args) {
    Console con = System.console();
    if(con == null) {
      System.out.println("Error on input.");
      System.exit(1);
    }
    // Assume the symbols are pre-ordered in a lexicographical order
    String str = con.readLine("Enter a collection of symbol: ");
    int n = Integer.valueOf(con.readLine("Enter a number (<= 10): "));
    char[] array = new char[n];

    EnumerateKmers ek = new EnumerateKmers();
    ek.enumerate(str,array,0);
  }
}
