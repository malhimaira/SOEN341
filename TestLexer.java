import java.io.File;
import java.io.FileInputStream;

public class TestLexer {

	public static void main(String args[]) {
		File asmFile = new File("TestSymbolTable.asm");
		AssemblySourceReader reader = new AssemblySourceReader(asmFile);
        FileInputStream inputReader = reader.readAsmFile();
        
		//Take file stream created in the reader
        ErrorReporter errorReporter = new ErrorReporter(asmFile.getName());
        SymbolTable symTab = new SymbolTable();
        Lexer lexer = new Lexer (inputReader, symTab, errorReporter);
        
        System.out.println("Test Lexer");
        System.out.println("tne 1B row:1 column:2, EOL row: 1 column: 7, tlt 1C row:2 column:3, EOL row: 2 column: 8, tge 1F row:3 column:3, EOL row: 3 column: 8, halt 0 row:4 column:3, EOL row: 4 column: 15, EOF row: 5 column: 1, ");
        
        Token t = lexer.getNextToken();
        String strST="";
       // System.out.println();
        while(t != null) {
        	
        	strST += t+", ";
        	
        	t = lexer.getNextToken();
        }
        System.out.println(strST);

        //System.out.println(lexer.getSymbolTable());

    
    }

}
