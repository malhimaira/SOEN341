import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
/**
 * 
 * This is a batch testing file to test unit tests (AUnit Test - hardcoded result vs program, 
 * cannot test source listing (integration test, not AUnit) here as expected hardcoded string is odd due to spacing and new lines   
 * 
 * 
 * PLEASE ADD AUNIT TESTS HERE IF THEY DEPEND ON LEXER OR PARSER
 *  (otherwise, just make a separate class called "Test____"
 
 *
 *
 */
public class TestBatchUnit {

	public static void main(String[] args) {
		
		int testNo;
		File asmFile;
		if (args.length != 2) {
           
			System.out.println("Please add the following two arguments: Test Number (int) and then the .asm file name");
            return;
        }
		try{
			testNo= Integer.parseInt(args[0]);
			asmFile = new File(args[1]);
		}
		catch(Exception e) {
			System.out.println("Please enter an integer only for the Test Number and then the .asm file name");
			return;
		}
		
		//SET VARIABLE BELOW TO TEST NUMBER
		
		
		/**
         * Test Lexer
         */	
		
		//Get File from Assembly Source Reader
		AssemblySourceReader reader = new AssemblySourceReader(asmFile);
        FileInputStream inputReader = reader.readAsmFile();
        
		//Take file stream created in the reader
        Lexer lexer = new Lexer (inputReader);
        
      //************************************ WRITE THE HASHED EXEPECTED SYMBOL TABLE HERE *************************************** 
        System.out.println("Test "+testNo+": Lexer Symbol Table");
        System.out.println("{halt=halt 0, EOL=[EOL=EOL], tge=tge 1F, tne=tne 1B, tlt=tlt 1C, EOF=[EOF=EOF]}");

        System.out.println(lexer.getSymbolTable());
        
        
        /**
         * Test Parser (and Test Lexer's getNextToken() method)
         */
        IParser parserObject = new Parser(lexer);
        ArrayList<ILineStatement> IR =  parserObject.parse();

      //************************************ WRITE THE SEQUENTIAL EXPECTED TRAVERSAL OF IR (FROM PARSER) HERE ********************* 
        //Printing the expected output
        System.out.println("Test "+testNo+": Parser");
        System.out.println("[tne 1B, tlt 1C, tge 1F, halt 0]");
        System.out.println(IR);

	}

}
