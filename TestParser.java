/**
 * Testing Class used for aunit testing on the Parser class by using a DummyLexer class
 * Useful for Testing the Parsing class itself (alone) without the need of testing the Lexer
 */
public class TestParser {

    public static void main(String[] args) {

        //Instantiating a DummyLexer Object with filled, hard-coded list of tokens.
        ILexer dlex = new DummyLexer(); 
        //Instantiating a parser using the dummy lexer.
        IParser parserObject = new Parser(dlex);

        //Printing the expected output
        System.out.println("Test Parser");
        System.out.println("[halt 0, and D, shl 18, tgt 1D, exit 3]");

        //Printing the actual output
        System.out.println(parserObject.parse());
    }
}
