import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;


public class CMA {

	public static void main(String[] args) {

	        if (args.length != 1) {
	            System.out.println("Usage: CMA File.asm");
	            return;
	        }
			File asmFile = new File(args[0]);
			int fileExtensionIndex = asmFile.getName().indexOf(".");
			String fileName = asmFile.getName().substring(0,fileExtensionIndex); //Gets name of file minus extension.

	        AssemblySourceReader reader = new AssemblySourceReader(asmFile);
	        FileInputStream inputReader = reader.readAsmFile();
	        
	        Lexer lexer = new Lexer (inputReader);
	        
	        Parser parser = new Parser(lexer);
	        
	        ArrayList<ILineStatement> IR =  parser.parse();

	        CodeGenerator cg = new CodeGenerator(IR, fileName, true);
			System.out.println("Done!");
	        
	        
	        


	}

}
