import java.io.File;
import java.io.FileInputStream;

/**
 * Main class, reads file name from console. Usage is java CMA File.asm
 */
public class CMA {

	public static void main(String[] args) {
		//TODO adapt this for getting options later.

		//Comment out those two lines to make
		Option optionUser = new Option();
		optionUser.userInput(args);


		File asmFile = optionUser.getAsmFile();
		//Get file name without the extension to help us produce properly named listing file and executable
		int fileExtensionIndex = asmFile.getName().indexOf(".");
		String fileName = asmFile.getName().substring(0,fileExtensionIndex); //Gets name of file minus extension.

		//Open the stream to the file
		AssemblySourceReader reader = new AssemblySourceReader(asmFile);
		FileInputStream inputReader = reader.readAsmFile();

		//Create error reporter
		ErrorReporter errorReporter = new ErrorReporter(asmFile.getName());

		//Create Symbol Table
		SymbolTable st = new SymbolTable();

		//Create LabelTable
		LabelTable lt = new LabelTable();

		//Take file stream created in the reader
		Lexer lexer = new Lexer (inputReader, st, lt, errorReporter);

		//Parse tokens from the lexer
		Parser parser = new Parser(lexer,errorReporter);

		IIR IR =  parser.parse();

			//If there are errors, do not continue to create the executable/listing file.
			if (errorReporter.hasErrors())
			{
				errorReporter.report();
				System.exit(0);
			}
			//Pass IR to the code generator, which produces executable (eventually) and optional listing file
	        CodeGenerator cg = new CodeGenerator(IR, fileName, optionUser.getListingStatus()); //Set to true for now since we always want the listing file for sprint 2
			cg.generate(); //Generate listing file if enabled and generate the exe
			System.out.println("Done!");

	}

}
