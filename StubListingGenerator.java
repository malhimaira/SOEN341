
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class StubListingGenerator implements IListingGenerator{

    private String header;
    private String addrString, label, operand, comment, mneName,opcode;
    private int currentAddr, currentLine;
    private PrintWriter pw;

    public StubListingGenerator(ArrayList<StubLineStatement> IR,String fileName){
        addrString = comment = operand = mneName = label = header = "";
        currentAddr = 0;
        currentLine = 1;

        try {
            pw = new PrintWriter(new File(fileName + ".lst"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        header = String.format("%-5s%-5s%-8s%-10s%-8s%-8s%-10s","Line","Addr","Code","Label","Mne","Operand","Comments");
        pw.println(header);
        for(StubLineStatement temp : IR) {
        	addrString = String.format("%04X", currentAddr);

            StubMnemonic mne = temp.instruction.mnemonic;
            mneName = mne.name;
            opcode = String.format("%02X",mne.opcode);
            //TODO comments, operands, labels
            pw.println(String.format("%-4d %-4s %-7s %-9s %-7s %-7s %-9s",currentLine,addrString,opcode,label,mneName,operand,comment));

            currentAddr++;
            currentLine++;
            addrString = comment = operand = mneName = label = header = "";
        }


        pw.close();
        System.out.println("Successfully generated " + fileName + ".lst"); //TODO This is temporary.

    }

}