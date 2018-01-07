import java.io.Console;
import java.util.*;

/* In a weighted alphabet, every symbol is assigned a positive real number called a weight. A string formed from a weighted alphabet is called a weighted string, and its weight is equal to the sum of the weights of its symbols.
  The standard weight assigned to each member of the 20-symbol amino acid alphabet is the monoisotopic mass of the corresponding amino acid.
    Given: A protein string P of length at most 1000 aa.
    Return: The total weight of P. Consult the monoisotopic mass table.
*/

public class ProteinMass {
  // Construct protein mass table
  public static final Map<Character,Double> proteinMassTable;
  static {
    proteinMassTable = new HashMap<Character,Double>();
    proteinMassTable.put('A',71.03711);proteinMassTable.put('C',103.00919);
    proteinMassTable.put('D',115.02694);proteinMassTable.put('E',129.04259);
    proteinMassTable.put('F',147.06841);proteinMassTable.put('G',57.02146);
    proteinMassTable.put('H',137.05891);proteinMassTable.put('I',113.08406);
    proteinMassTable.put('K',128.09496);proteinMassTable.put('L',113.08406);
    proteinMassTable.put('M',131.04049);proteinMassTable.put('N',114.04293);
    proteinMassTable.put('P',97.05276);proteinMassTable.put('Q',128.05858);
    proteinMassTable.put('R',156.10111);proteinMassTable.put('S',87.03203);
    proteinMassTable.put('T',101.04768);proteinMassTable.put('V',99.06841);
    proteinMassTable.put('W',186.07931);proteinMassTable.put('Y',163.06333);
  }
  /* @param protein dnaSequence
   * @return protein mass
  */
  public double calculate(String seq) {
    double mass = 0;
    for(int i = 0; i < seq.length(); i++){
      mass += proteinMassTable.get(seq.charAt(i));
    }
    return mass;
  }

  public static void main(String[] args) {
    ProteinMass pm = new ProteinMass();
    Console con = System.console();
    if(con != null) {
      String input = con.readLine("Enter a protein sequence:");
      double weight = pm.calculate(input);
      System.out.printf("Protein weight: %.3f Da\n",weight);
    } else {
      System.out.println("No input.");
    }
  }
}
