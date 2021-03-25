import java.io.File;
import java.io.FileInputStream;

public class TestErrorReporterLexer {

	public static void main(String args[]) {
		File asmFile = new File("TestErrorReporterLexer.asm");
		AssemblySourceReader reader = new AssemblySourceReader(asmFile);
        FileInputStream inputReader = reader.readAsmFile();
        
		//Take file stream created in the reader
        ErrorReporter errorReporter = new ErrorReporter(asmFile.getName());
        Lexer lexer = new Lexer (inputReader, errorReporter);

        System.out.println("Test LexerErrors");
		errorReporter.report();
        // System.out.println("Comment = ; comment. row: 1 column: 1\n" + 
        // 		" EOL row: 1 column: 11\n" + 
        // 		" $ FFFFFFFF row:2 column:3\n" + 
        // 		" Comment = ; comment. row: 2 column: 12\n" + 
        // 		" EOL row: 2 column: 22\n" + 
        // 		" tlt 1C row:3 column:3\n" + 
        // 		" Comment = ; comment. row: 3 column: 15\n" + 
        // 		" EOL row: 3 column: 25\n" + 
        // 		" % FFFFFFFF row:4 column:10\n" + 
        // 		" 5 FFFFFFFF row:4 column:13\n" + 
        // 		" Comment = ; comment.  row: 4 column: 15\n" + 
        // 		" EOL row: 4 column: 26\n" + 
        // 		" halt 0 row:5 column:3\n" + 
        // 		" EOL row: 5 column: 15\n" + 
        // 		" EOF row: 6 column: 1");

        // Token t = lexer.getNextToken();
        // String strST="";
        // System.out.println();
        // while(t != null) {
        // 	strST += t+"\n ";
        // 	t = lexer.getNextToken();
        // }
        // System.out.println(strST);
    }

}
