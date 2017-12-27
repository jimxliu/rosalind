import java.net.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FindProteinMotif {
  public static void main(String[] args) throws Exception {
    if(args.length < 1){ // Must run the program with the input file
      System.out.println(args[0]);
      System.err.println("No file input");
      System.exit(1);
    }
    // Read the dataset with a list of accessIDs using bufferedreader
    File file = new File(args[0]);
    BufferedReader br = new BufferedReader(new FileReader(file));
    String accessID;
    while((accessID = br.readLine()) != null) { // Read each accessID
      URL fastaURL = new URL("http://www.uniprot.org/uniprot/"+accessID+".fasta"); // Obtain the fasta file of each accessID from uniprot url
      BufferedReader in = new BufferedReader(new InputStreamReader(fastaURL.openStream())); // Read the fasta file

      String proteinInfo; // Get the metadata of the protein from the fasta file. Might not be used later
      proteinInfo = in.readLine();
      //System.out.println(proteinInfo);

      String proteinSeq = ""; // Protein sequence
      String inputLine; // The sequence is chopped into lines
      while((inputLine = in.readLine()) != null)
        proteinSeq += inputLine; // Connect all the lines into one sequence
      //System.out.println(proteinSeq);
      in.close();
      Pattern pattern = Pattern.compile("N[^P][ST][^P]"); // Define the N-glycosylation motif
      Matcher matcher = pattern.matcher(proteinSeq); // The pattern is compared against the protein sequence

      boolean found = false;
      int start = 0; // Starting position on the sequence to look for the motif
      String locations = ""; // String of locations containing the motif
      while(matcher.find(start)) { // If there is a match
        found = true; // found is true
        /*System.out.format("I found the text" +
                    " \"%s\" starting at " +
                    "index %d and ending at index %d.%n",
                    matcher.group(),
                    matcher.start()+1,
                    matcher.end()+1);
        */
        start = matcher.start() + 1; // Update the start position
        locations += (start + " "); // Add the location of the found motif to the list
      }
      if(found) { // If found, print out the accessID and all the locations
        System.out.println(accessID);
        System.out.println(locations);
      }
    }
    br.close(); // Dont forget to close the bufferereader
  }
}
