import java.io.File;

/**
 * CodeGenerator class, used to generate the exe file and optionally, the lst file.
 */
public class CodeGenerator implements ICodeGenerator{
	private IIR IR; //The IR passed from the parser
	private LabelTable labelTable;
	private SymbolTable symbolTable;
	private String fileName; //Name of file to produce, same as .asm file name
	private boolean generateLST; //Boolean dictating whether to generate listing file
	private boolean generateEXE; //Boolean dictating whether to generate exe file

	public CodeGenerator(IIR IR, String fileName, SymbolTable st, LabelTable lt, boolean generateLST) {
	this.IR = IR;
	this.symbolTable = st;
	this.labelTable = lt;
	this.fileName = fileName;
	this.generateLST = generateLST;

	}

	public void generate() {
		
		//booleans for lst
		if(generateLST == true) {
			ListingGenerator lg = new ListingGenerator(IR,fileName, labelTable); //Automatically runs.
		}
			//ExeGenerator eg = new ExeGenerator(IR, fileName, symbolTable, labelTable); //Automatically runs. TODO fix this 
		}
}
