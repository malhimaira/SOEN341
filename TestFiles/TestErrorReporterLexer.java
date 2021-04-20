package TestFiles;
import java.io.File;
import java.io.FileInputStream;
import FrontEnd.*;
import BackEnd.*;
import main.*;
import Tables.*;


public class TestErrorReporterLexer {

	public static void main(String args[]) {
		File asmFile = new File("TestErrorReporterLexer.asm");
		AssemblySourceReader reader = new AssemblySourceReader(asmFile);
        FileInputStream inputReader = reader.readAsmFile();
        
		//Take file stream created in the reader
        ErrorReporter errorReporter = new ErrorReporter(asmFile.getName());
        SymbolTable symTab = new SymbolTable();
        LabelTable labTab = new LabelTable();
        Lexer lexer = new Lexer (inputReader, symTab, labTab, errorReporter);

        System.out.println("Test LexerErrors");
		errorReporter.report();
    }

}
