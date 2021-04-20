package TestFiles;
import FrontEnd.*;
import BackEnd.*;
import main.*;
import Tables.*;

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
        SymbolTable symTab = new SymbolTable();
        LabelTable labTab = new LabelTable();
        Lexer lexer = new Lexer (inputReader, symTab, labTab, errorReporter);
        
        System.out.println("Test Lexer Symbol Table");
        System.out.println("{tne 1B row:1 column:2=tne 1B row:1 column:2, EOL row: 1 column: 7=EOL row: 1 column: 7, EOL row: 4 column: 15=EOL row: 4 column: 15, EOL row: 2 column: 8=EOL row: 2 column: 8, halt 0 row:4 column:3=halt 0 row:4 column:3, EOL row: 3 column: 8=EOL row: 3 column: 8, tge 1F row:3 column:3=tge 1F row:3 column:3, EOF row: 5 column: 1=EOF row: 5 column: 1, tlt 1C row:2 column:3=tlt 1C row:2 column:3}");

        System.out.println(lexer.getSymbolTable());
    }

}
