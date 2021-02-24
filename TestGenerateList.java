import java.util.ArrayList;

//Testing out the part that goes throught the ListingGenerator
public class TestGenerateList {

    public static void main(String[] args) {
      System.out.println("Testing Generated List classes:");

      /*Parts from TestParse as we need to test if the ListingGenerator can actually do it's job*/
      // Initializing a Parser object to run the test
      Parser parserObject = new Parser();

      // Initializing an ArrayList of Tokens to use as a Hardcoded Test case
      ArrayList<Token> tokenList = new ArrayList<Token>();

      //Adding Tokens to the ArrayList
      tokenList.add(new Mnemonic("halt"));
      tokenList.add(new EOL("EOL"));

      tokenList.add(new Mnemonic("and"));
      tokenList.add(new EOL("EOL"));

      tokenList.add(new Mnemonic("shl"));
      tokenList.add(new EOL("EOL"));

      tokenList.add(new Mnemonic("tgt"));
      tokenList.add(new EOL("EOL"));

      tokenList.add(new Mnemonic("exit"));
      tokenList.add(new EOL("EOL"));

      tokenList.add(new EOF("EOF"));

      //Instantiating a DummyLexer Object with filled TokenList
      DummyLexer dlex = new DummyLexer(tokenList);

      //Printing the expected output
      System.out.println("Test Parser");
      System.out.println("[halt 0, and D, shl 18, tgt 1D, exit 3]");

      //Calling the Parser on the DummyLexer Object
      parserObject.parseTokens(dlex);

      //Printing the actual output
      System.out.println(parserObject.getIR());
      /*End of stuff taken for testing purposes from TestParser*/

      //Implementation of what is within the stub and ListingGenerator file

    }


}
