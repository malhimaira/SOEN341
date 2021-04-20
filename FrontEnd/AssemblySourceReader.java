package FrontEnd;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AssemblySourceReader implements IAssemblySourceReader{

	private File inputFile;
	//private String[] asmFileArray;

	public AssemblySourceReader(File inputFile) {
		this.inputFile = inputFile;
		//asmFileArray = null; 
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