
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
        opcode = addrString = comment = operand = mneName = label = header = "";
        currentAddr = 0;
        currentLine = 1;
        
        try {
            pw = new PrintWriter(new File(fileName + ".lst"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        //Header of the document, using String.format/
        header = String.format("%-5s%-5s%-21s%-10s%-11s%-16s%-16s","Line","Addr","Machine Code","Label","Mne","Operand","Comments");
        pw.println(header); //Add header to document
        //Loop through all line statements, adding the information to the list file.
        for(int i = 0; i <IR.getSize(); i++) {
            ILineStatement temp = IR.getLineStatement(i);

        	addrString = String.format("%04X", currentAddr);
            
            if (temp.getInstruction() != null) {
                Mnemonic mne = (Mnemonic) temp.getInstruction().getMnemonic();
                mneName = mne.getName();
                opcode = String.format("%02X",mne.getOpcode());
                if (!temp.getInstruction().isInherent()) //If it is not inherent and thus needs an operand.
                    operand = Integer.toString(temp.getInstruction().getNumberInt());
            } else if (temp.getDirective() != null) { //we have a directive aka the .cstring
                Directive cstring = (Directive) temp.getDirective();
                mneName = cstring.getName(); //Always cstring but generalization is good.
                operand = "\"" + cstring.getTrimmedString() + "\"";
                int asciiNumbersShown = -1;
                //Show max 4 ascii hex representations!
                if (cstring.getTrimmedString().length() <= 4)
                    asciiNumbersShown = cstring.getTrimmedString().length();
                else 
                    asciiNumbersShown = 4;
                for (int j = 0; j < asciiNumbersShown; j++){
                    opcode += String.format("%02X ", (int) cstring.getTrimmedString().charAt(j));
                }
                if (cstring.getTrimmedString().length() < 4)
                    opcode +="00"; 
            }
            if (temp.getComment() != null)
                comment = temp.getComment().getName();
            //TODO comments, operands, labels
            pw.println(String.format("%-4d %-4s %-20s %-9s %-10s %-16s %-15s",currentLine,addrString,opcode,label,mneName,operand,comment));

            if (temp.getInstruction() != null) //If no instruction on line, we do not increment address!
                currentAddr++;
            else if(temp.getDirective() != null)
                {
                    currentAddr += temp.getDirective().getTrimmedString().length() + 1; //Increment by size of trimmed string + 1 for the null char (# of bytes in string)
                }

            currentLine++;
            opcode = addrString = comment = operand = mneName = label = header = "";
        }


        pw.close(); //Close the PrintWriter
        System.out.println("Successfully generated " + fileName + ".lst");

    }

}
