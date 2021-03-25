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
    }

}
