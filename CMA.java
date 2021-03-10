import java.io.File;
import java.io.FileInputStream;

/**
 * Main class, reads file name from console. Usage is java CMA File.asm
 */
public class CMA {

	public static void main(String[] args) {
			//TODO adapt this for getting options later.
	        if (args.length != 1) {
	            System.out.println("Usage: CMA File.asm");
	            return;
	        }
			File asmFile = new File(args[0]);
			//Get file name without the extension to help us produce properly named listing file and executable
			int fileExtensionIndex = asmFile.getName().indexOf(".");
			String fileName = asmFile.getName().substring(0,fileExtensionIndex); //Gets name of file minus extension.

			//Open the stream to the file
	        AssemblySourceReader reader = new AssemblySourceReader(asmFile);
	        FileInputStream inputReader = reader.readAsmFile();
	        
			//Take file stream created in the reader
	        Lexer lexer = new Lexer (inputReader);
	        
			//Parse tokens from the lexer
	        Parser parser = new Parser(lexer);
	        
	        IIR IR =  parser.parse();
			//Pass IR to the code generator, which produces executable (eventually) and optional listing file
	        CodeGenerator cg = new CodeGenerator(IR, fileName, true); //Set to true for now since we always want the listing file for sprint 2
			System.out.println("Done!");
	        
	        
	        


	}

}
