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
        System.out.println("[halt 0 row:1 column:1, and D row:2 column:1, shl 18 row:3 column:1, tgt 1D row:4 column:1, exit 3 row:5 column:1, ldc.i3 95 row:6 column:1, enter.u5 7F row:7 column:1 ;Test Comment, ;Comment and no Instruction, testLabel br.i8 E0 row:9 column:2, lda.i16 D5 row:10 column:2]");

        //Printing the actual output
        System.out.println(parserObject.parse());
    }
}
