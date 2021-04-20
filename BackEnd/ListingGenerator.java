package BackEnd;

import main.*;
import FrontEnd.*;
import Tables.*;
import TestFiles.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Listing Generator, generates the listing file as the constructor is called.
 */
public class ListingGenerator implements IListingGenerator {

    private String header;
    private String addrString, label, operand, comment, mneName,opcode,offset;
    private int currentAddr, currentLine;
    private PrintWriter pw;
    private int addressArray[];

    /**
     * Generates the listing file
     * @param IR Received from the parser
     * @param fileName The name of the input file, so the list file can have the same name
     */
    public ListingGenerator(IIR IR, String fileName,LabelTable labelTable){
        opcode = addrString = comment = operand = mneName = label = header = offset = "";
        addressArray = new int[IR.getSize()]; //Amount of addresses = number of line statements, aka the size of the IR
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
            calculateAddresses(IR);

            currentAddr = addressArray[i];
        	addrString = String.format("%04X", currentAddr);
            if (temp.getLabel() != null) {
                label = temp.getLabel().getName();
            }
            if (temp.getInstruction() != null) {
                Mnemonic mne = (Mnemonic) temp.getInstruction().getMnemonic();
                mneName = mne.getName();
                opcode = String.format("%02X",mne.getOpcode());
                if (!temp.getInstruction().isInherent()) //If it is not inherent and thus needs an operand.

                    if (mne.needsNumber()) {
                        operand = Integer.toString(temp.getInstruction().getNumberInt());
                    }
                    if (mne.needsNumber() && mne.isRelative())
                        opcode += " " + String.format("%02X", temp.getInstruction().getNumberInt());
                    else if (mne.needsLabel()) {
                        operand = temp.getInstruction().getLabelOperand().getName();
                        int offsetInt = relativeOffset((Label) temp.getInstruction().getLabelOperand(),currentAddr,labelTable);
                        int offsetChars = offset.length();
                        if (temp.getInstruction().getMnemonic().getName().contains(".i8")) {
                            offset = String.format("%02X", offsetInt);
                            offsetChars = 2;
                        } else if (temp.getInstruction().getMnemonic().getName().contains("i16")) {
                            offset = String.format("%04X", offsetInt);
                            offsetChars = 4;
                        }

                        if (!offset.equals(""))
                            opcode += " " + offset.substring(offset.length()-offsetChars);
                    }
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

            if (temp.getInstruction() != null){ //If no instruction on line, we do not increment address!
                //currentAddr+= temp.getInstruction().getSize();
            }
            else if(temp.getDirective() != null)
                {
                    //currentAddr += temp.getDirective().getSize(); //Increment by size of trimmed string + 1 for the null char (# of bytes in string)
                }

            currentLine++;
            opcode = addrString = comment = operand = mneName = label = header = offset = "";
        }


        pw.close(); //Close the PrintWriter
        System.out.println("Successfully generated " + fileName + ".lst");

    }

    private int relativeOffset(Label labelOperand, int currentAddr, LabelTable labelTable) {
        Label labelToFind = labelTable.getLabelTable().get(labelOperand.getName());
        int index = labelToFind.getPosition().getRow() -1;
        int labelAddr = addressArray[index];
        int offset = labelAddr - currentAddr;
        return offset;
    }

    private void calculateAddresses(IIR IR) {

        addressArray[0] = 0;
        LineStatement ls = (LineStatement) IR.getLineStatement(0);
        addressArray[1] = ls.getSize();
        for (int i = 1; i < addressArray.length-1; i++)
        {
        
            ls = (LineStatement) IR.getLineStatement(i);
            int lsSize = ls.getSize();
            addressArray[i+1] = addressArray[i] + lsSize;
        }


    }

}
