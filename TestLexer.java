import java.io.File;
import java.io.FileInputStream;

public class TestLexer {

	public static void main(String args[]) {
		
		//Get File from Assembly Source Reader
		File asmFile = new File("TestSymbolTable.asm");
		AssemblySourceReader reader = new AssemblySourceReader(asmFile);
        FileInputStream inputReader = reader.readAsmFile();
        
		//Take file stream created in the reader
        Lexer lexer = new Lexer (inputReader);
        
        System.out.println("Test Lexer");
        System.out.println("{tne 1B, [EOL=EOL], tlt 1C, [EOL=EOL], tge 1F, [EOL=EOL], halt 0, [EOL=EOL], [EOF=EOF]}");

        Token t = lexer.getNextToken();
        String strST="{";
        
        while(t != null) {
        	strST += t+", ";
        	t = lexer.getNextToken();
        }
        strST = strST.substring(0, strST.length()-2)+'}';
        
        System.out.println(strST);
    }

}
