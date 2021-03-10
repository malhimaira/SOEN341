/**
 * CodeGenerator class, used to generate the exe file and optionally, the lst file.
 */
public class CodeGenerator implements ICodeGenerator{
	
	public CodeGenerator(IIR IR, String fileName, boolean generateLST) {
	
		//booleans for lst
		//TODO add the same name of file (arg[0[]) without extension to generate ___.exe and ___.lst
		if(generateLST == true) {
			ListingGenerator lg = new ListingGenerator(IR,fileName);
		}
		
		//TODO call .exe
		
	}
}
