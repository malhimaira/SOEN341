import java.io.File;
import java.io.FileInputStream;

public class TestLexerRelativeInstruction {
	 
	public static void main(String args[]) {
		File asmFile = new File("rela01.asm");
		AssemblySourceReader reader = new AssemblySourceReader(asmFile);
	    FileInputStream inputReader = reader.readAsmFile();
	    
		//Take file stream created in the reader
	    ErrorReporter errorReporter = new ErrorReporter(asmFile.getName());
	    SymbolTable symTab = new SymbolTable();
	    LabelTable labTab = new LabelTable();
	    Lexer lexer = new Lexer (inputReader, symTab, labTab, errorReporter);
	    
	    Token t = lexer.getNextToken();
   
        while(t != null) {
        	
        	System.out.println(t);
        	
        	t = lexer.getNextToken();
        }
        System.out.println();
        System.out.println(lexer.getLabelTable());
	    
	}
    
}
