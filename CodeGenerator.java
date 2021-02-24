import java.util.ArrayList;


public class CodeGenerator implements ICodeGenerator{
	public CodeGenerator(ArrayList<ILineStatement> IR, String fileName, boolean generateLST) {
	
		//booleans for lst
		//TODO add the same name of file (arg[0[]) without extension to generate ___.exe and ___.lst
		if(generateLST == true) {
			ListingGenerator lg = new ListingGenerator(IR,fileName);
		}
		
		//TODO call .exe
		
	}
}
