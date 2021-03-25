public class TestParserErrors {

    public static void main(String[] args) {

        //Instantiating a DummyLexer Object with filled, hard-coded list of tokens.
        ILexer bdlex = new BrokenDummyLexer();
        ErrorReporter errorReporter = new ErrorReporter();
        //Instantiating a parser using the dummy lexer.
        IParser parserObject = new Parser(bdlex,errorReporter);

        //Printing the expected output
        System.out.println("Test ParserErrors");

        //Printing the actual output
        parserObject.parse();

        errorReporter.report();

    }
}
