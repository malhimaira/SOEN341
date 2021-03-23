/**
 * Testing Class used for aunit testing on the Parser class by using a DummyLexer class
 * Useful for Testing the Parsing class itself (alone) without the need of testing the Lexer
 */
public class TestParser {

    public static void main(String[] args) {

        //Instantiating a DummyLexer Object with filled, hard-coded list of tokens.
        ILexer dlex = new DummyLexer();
        ErrorReporter errorReporter = new ErrorReporter(); 
        //Instantiating a parser using the dummy lexer.
        IParser parserObject = new Parser(dlex,errorReporter);

        //Printing the expected output
        System.out.println("Test Parser");
        System.out.println("[halt 0 row:1 column:1, and D row:2 column:1, shl 18 row:3 column:1, tgt 1D row:4 column:1, exit 3 row:5 column:1, enter.u5 8F row:6 column:1 ;Test Comment, ;Comment and no Instruction]");

        //Printing the actual output
        System.out.println(parserObject.parse());
    }
}
