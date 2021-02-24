import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AssemblySourceReader implements IAssemblySourceReader{

	private File inputFile;
	private String[] asmFileArray;
//    public static void main(String[] args) throws IOException {
//        String asmPath = "/Users/marina/AndroidStudioProjects/Soen341/soen341/TestInherentMnemonics.asm";
//        File asmSourceFile = new File(asmPath);
//
//        InputStream inputStream = null;
//        readAsmFile(asmSourceFile);
//
//        inputStream = readAsmFile(asmSourceFile);
//
//    }
	public AssemblySourceReader(File inputFile) {
		this.inputFile = inputFile;
		asmFileArray = null; 
	}

    public FileInputStream readAsmFile() {

        try {
            FileInputStream inputStream = new FileInputStream(inputFile);
            return inputStream;

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Error: file \"" + inputFile + "\" not found. Check spelling or file location!");
            System.exit(0);
            //fileNotFoundException.printStackTrace();
        }
        return null;
    }
}