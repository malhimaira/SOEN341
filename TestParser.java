import java.util.ArrayList;

/**
 * Testing Class used for aunit testing on the Parser class by using a DummyLexer class
 * Useful for Testing the Parsing class itself (alone) without the need of testing the Lexer
 */
public class TestParser {

    public static void main(String[] args) {
        // Initializing a Parser object to run the test
        IParser parserObject = new Parser();

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
        ILexer dlex = new DummyLexer(tokenList);

        //Printing the expected output
        System.out.println("Test Parser");
        System.out.println("[halt 0, and D, shl 18, tgt 1D, exit 3]");

        //Calling the Parser on the DummyLexer Object
        parserObject.parseTokens(dlex);

        //Printing the actual output
        System.out.println(parserObject.getIR());
    }
}
