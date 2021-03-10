
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Listing Generator, generates the listing file as the constructor is called.
 */
public class ListingGenerator implements IListingGenerator {

    private String header;
    private String addrString, label, operand, comment, mneName,opcode;
    private int currentAddr, currentLine;
    private PrintWriter pw;

    /**
     * Generates the listing file
     * @param IR Received from the parser
     * @param fileName The name of the input file, so the list file can have the same name
     */
    public ListingGenerator(IIR IR, String fileName){
        addrString = comment = operand = mneName = label = header = "";
        currentAddr = 0;
        currentLine = 1;
        
        try {
            pw = new PrintWriter(new File(fileName + ".lst"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        //Header of the document, using String.format/
        header = String.format("%-5s%-5s%-8s%-10s%-8s%-12s%-10s","Line","Addr","Code","Label","Mne","Operand","Comments");
        pw.println(header); //Add header to document
        //Loop through all line statements, adding the information to the list file.
        for(int i = 0; i <IR.getSize(); i++) {
            ILineStatement temp = IR.getLineStatement(i);

        	addrString = String.format("%04X", currentAddr);

            Mnemonic mne = temp.getInstruction().getMnemonic();
            mneName = mne.getName();
            opcode = String.format("%02X",mne.getOpcode());
            //TODO comments, operands, labels
            pw.println(String.format("%-4d %-4s %-7s %-9s %-7s %-7s %-9s",currentLine,addrString,opcode,label,mneName,operand,comment));

            currentAddr++;
            currentLine++;
            addrString = comment = operand = mneName = label = header = "";
        }


        pw.close(); //Close the PrintWriter
        System.out.println("Successfully generated " + fileName + ".lst"); //TODO This is temporary.

    }

}
