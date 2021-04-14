/**
 * CodeGenerator class, used to generate the exe file and optionally, the lst file.
 */
public class CodeGenerator implements ICodeGenerator{
	private IIR IR; //The IR passed from the parser
	private String fileName; //Name of file to produce, same as .asm file name
	private boolean generateLST; //Boolean dictating whether to generate listing file

	public CodeGenerator(IIR IR, String fileName, boolean generateLST) {
	this.IR = IR;
	this.fileName = fileName;
	this.generateLST = generateLST;
	}

	public void generate() {
		
		//booleans for lst
		if(generateLST == true) {
			ListingGenerator lg = new ListingGenerator(IR,fileName); //Automatically runs.
		}
		
		//TODO call .exe
		
	}
}
