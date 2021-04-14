import java.io.File;
import java.io.FileInputStream;

/**
 * Main class, reads file name from console. Usage is java CMA File.asm
 */
public class CMA {

	//Behavoirs depending on user inputs
	public static void notationMsg(){
		System.out.println("Usage: cma [ Options ] <file>.asm");
		System.out.println("If you need more detail use the help option:\t-h \tOR \t-help");
	}

	public static void helpMsg(){
		System.out.println("\nUsage: cma [ Options ] <file>.asm\n");
		System.out.printf("%-15s%-15s%-15s\n","Short version","Long version","Meaning\n");
		System.out.printf("%-15s%-15s%-15s\n","-h","-help","Print the usage of the program.");
		System.out.printf("%-15s%-15s%-15s\n","-v","-verbose","Verbose during the execution of the program.");
		System.out.printf("%-15s%-15s%-15s\n","-b","-banner","Print the banner of the program.");
		System.out.printf("%-15s%-15s%-15s\n","-l","-listing","Generate a listing of the assembly file.");
	}

	public static boolean checkFile(String fileName){
		if(fileName.contains(".asm") && fileName.length() > 4){
			File asmFile = new File(fileName);
			if(asmFile.exists()) {
				return true;
			}else{
				System.out.println("\nThis ASM file does not exist in this directory");
				return false;
			}
		}else{
			return false;
		}
	}

	public static void doExecutable(File fileIn){
		System.out.println("\nExe behavior");
	}

	public static void doVerbose(File fileIn){
		System.out.println("\nVerbose behavior");
		doExecutable(fileIn);
	}

	public static void doListing(File fileIn){
		System.out.println("\nListing behavior");
		doExecutable(fileIn);
	}

	public static void main(String[] args) {
		//TODO adapt this for getting options later.

		//Variable for ASM file
		File asmFile;

		//Checking User inputs
		//Bad Case - User had put nothing past CMA or put too many arguments
		if(args.length < 1 || args.length > 2){
			System.out.println("\nError in the pattern of the command.\nThe command has to folllow the pattern below:");
			notationMsg();
			return;
		}

		//Cases where the user can give one argument
		if(args.length == 1) {

			//Good case - User asks for banner
			if(args[0].equals("-b")||args[0].equals("-banner")) {
				System.out.println("\nCm Cross-Assembler Version 4.1 - Developed by Team 2.");
				return;
			}

			//Good case - User asks for help
			if(args[0].equals("-h")||args[0].equals("-help")) {
				helpMsg();
				return;
			}

			//Good case - User only gave ASM file (Checking if it exist)
			if(checkFile(args[0])){
				asmFile = new File(args[0]);
				doExecutable(asmFile);
				return;
			}

			//Bad case - the one argument given wasn't one of the options above
			System.out.println("\nError in the name of option or name of ASM file.");
			notationMsg();
			return;
		}

		if(args.length == 2) {
			//Good case - User asks for verbose
			if(args[0].equals("-v")||args[0].equals("-verbose")){
				if(checkFile(args[1])){
					asmFile = new File(args[1]);
					doVerbose(asmFile);
					return;
				}
			}

			//Good case - User asks for listing
			if((args[0].equals("-l")||args[0].equals("-listing")) && args[1].contains(".asm") && args[1].length() > 4){
				if(checkFile(args[1])){
					asmFile = new File(args[1]);
					doListing(asmFile);
					return;
				}
			}

			//Bad case - one or both arguments given weren't one of the options above
			System.out.println("\nError in the name of option or name of ASM file.");
			notationMsg();
			return;
		}

		asmFile = new File(args[0]);
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
	        CodeGenerator cg = new CodeGenerator(IR, fileName, true); //Set to true for now since we always want the listing file for sprint 2
			cg.generate(); //Generate listing file if enabled and generate the exe
			System.out.println("Done!");
	        
	        
	        


	}

}
