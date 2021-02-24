import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;


public class CMA {

	public static void main(String[] args) {

	        if (args.length != 1) {
	            System.out.println("Usage: CMA File.asm");
	            return;
	        }
	        AssemblySourceReader reader = new AssemblySourceReader(new File(args[0]));
	        FileInputStream inputReader = reader.readAsmFile();
	        
	        Lexer lexer = new Lexer (inputReader);
	        
	        Parser parser = new Parser(lexer);
	        
	        ArrayList<ILineStatement> IR =  parser.parse();

	        CodeGenerator cg = new CodeGenerator(IR, true);
	        
	        
	        


	}

}
