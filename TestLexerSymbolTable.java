import java.io.File;
import java.io.FileInputStream;

public class TestLexerSymbolTable {

	public static void main(String args[]) {
		
		//Get File from Assembly Source Reader
		File asmFile = new File("TestSymbolTable.asm");
		AssemblySourceReader reader = new AssemblySourceReader(asmFile);
        FileInputStream inputReader = reader.readAsmFile();
        
		//Take file stream created in the reader
        Lexer lexer = new Lexer (inputReader);
        
        System.out.println("Test Lexer Symbol Table");
        System.out.println("{halt=halt 0, EOL=[EOL=EOL], tge=tge 1F, tne=tne 1B, tlt=tlt 1C, EOF=[EOF=EOF]}");

        System.out.println(lexer.getSymbolTable());
    }

}
