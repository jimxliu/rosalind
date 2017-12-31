/*
Given: A protein string of length at most 1000 aa.
Return: The total number of different RNA strings from which the protein could have been translated, modulo 1,000,000. (Don't neglect the importance of the stop codon in protein translation.)
*/

import java.util.*;
import java.io.Console;

public class ReverseTranslation { // Frequency of a type of amino acid represented in the codon table
  private static final Map<String,Integer> table; // Construct the amino acide frequence table
  static {
    Map<String,Integer> tempMap = new HashMap<String,Integer>();
    tempMap.put("A",4);
    tempMap.put("C",2);
    tempMap.put("D",2);
    tempMap.put("E",2);
    tempMap.put("F",2);
    tempMap.put("G",4);
    tempMap.put("H",2);
    tempMap.put("I",3);
    tempMap.put("K",2);
    tempMap.put("L",6);
    tempMap.put("M",1);
    tempMap.put("N",2);
    tempMap.put("P",4);
    tempMap.put("Q",2);
    tempMap.put("R",6);
    tempMap.put("S",6);
    tempMap.put("T",4);
    tempMap.put("V",4);
    tempMap.put("W",1);
    tempMap.put("Y",2);
    tempMap.put("Stop",3);
    table = Collections.unmodifiableMap(tempMap); // Can't be modified
  }
  public static void main(String[] args) {
    // System.out.println(table);
    Console console = System.console(); // Input from command line
    if (console == null) { // If console fails
      System.err.println("No console.");
      System.exit(1);
    }
    int variety = 1; // The total number of reverse-translated mRNA, starting from 1
    String proteinSeq = console.readLine("Enter protein sequence: "); // Input protein sequence
    for(int i=0; i < proteinSeq.length(); i++) { // Iterate through the sequence
      char aa = proteinSeq.charAt(i); // Each amino acid
      variety *= table.get(String.valueOf(aa)); // Found the frequency in the table
      variety %= 1000000; // Don't forget to do modulo
    }
    variety *= table.get("Stop"); // Don't forget the Stop codon
    variety %= 1000000;

    System.out.format("Total number of different mRNA strings possibly translated from the protein: %d\n", variety);
  }
}
