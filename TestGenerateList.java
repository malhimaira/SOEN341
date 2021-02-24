import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

//Testing out the part that goes throught the ListingGenerator
public class TestGenerateList extends Exception{

    public static void main(String[] args) throws FileNotFoundException {
      System.out.println("Testing Generated List classes:");

      /*Parts from TestParse as we need to test if the ListingGenerator can actually do it's job*/
      //Instantiating a DummyLexer Object with filled, hard-coded list of tokens.
      ILexer dlex = new DummyLexer();
      //Instantiating a parser using the dummy lexer.
      IParser parserObject = new Parser(dlex);

      //Printing the expected output
      System.out.println("Test Parser");
      System.out.println("[halt 0, and D, shl 18, tgt 1D, exit 3]");

      //Printing the actual output
      System.out.println(parserObject.parse());
      /*End of stuff taken for testing purposes from TestParser*/

      //Implementation of what is within the stub and ListingGenerator file
      PrintStream outputList = new PrintStream("testList.lst");
      System.setOut(outputList);

      ListingGenerator testListing = new ListingGenerator(parserObject.getIR());

      System.out.println(ListingGenerator.header + "\n");
      int i = 1;
      for(LineStatement ls : testListing.getStub().getArrayLineStat()){
        //setAddr(i);
        System.out.println(i + "\t" + ls.toString()+"\n");
        i++;
      }
    }


}
