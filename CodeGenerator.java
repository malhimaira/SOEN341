/**
 * CodeGenerator class, used to generate the exe file and optionally, the lst file.
 */
public class CodeGenerator implements ICodeGenerator{
	private IIR IR; //The IR passed from the parser
	private String fileName; //Name of file to produce, same as .asm file name
	private boolean generateLST; //Boolean dictating whether to generate listing file
	private LabelTable labelTable;

	public CodeGenerator(IIR IR, String fileName, boolean generateLST,LabelTable labelTable) {
	this.IR = IR;
	this.fileName = fileName;
	this.generateLST = generateLST;
	this.labelTable = labelTable;
	}

	public void generate() {
		
		//booleans for lst
		if(generateLST == true) {
			ListingGenerator lg = new ListingGenerator(IR,fileName,labelTable); //Automatically runs.
		}
		
		//TODO call .exe
		
	}
}
