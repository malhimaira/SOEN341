import java.io.File;
import java.io.FileInputStream;

public class TestLexerSymbolTable {

	public static void main(String args[]) {
		
		//Get File from Assembly Source Reader
		File asmFile = new File("TestSymbolTable.asm");
		AssemblySourceReader reader = new AssemblySourceReader(asmFile);
        FileInputStream inputReader = reader.readAsmFile();
        
		//Take file stream created in the reader
        ErrorReporter errorReporter = new ErrorReporter(asmFile.getName());
        Lexer lexer = new Lexer (inputReader, errorReporter);
        
        System.out.println("Test Lexer Symbol Table");
        System.out.println("{halt=halt 0 row:4 column:2, EOL=EOL row: 4 column: 15, tge=tge 1F row:3 column:2, tne=tne 1B row:1 column:2, tlt=tlt 1C row:2 column:2}");

        System.out.println(lexer.getSymbolTable());
    }

}
