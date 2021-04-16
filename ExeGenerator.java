import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;

public class ExeGenerator implements IExeGenerator {

    private HashMap<String, Token> symbolTable;
    private HashMap<String, Label> labelTable;

    private HashMap<Integer, Integer> pseudoAddressTable; // Key = row, Value = current address
    private HashMap<Integer, Integer> lineStatementSizeTable; // Key = row, Value = instruction size
    int currentRow, pseudoAddress, labelOperandAddress, labelTableRow, labelTableAddress, offset, lineStatementSize, currentInstructionSize;

    Token currentToken;
    TokenType currentTokenType;
    Label correspondingLabel;

    private String addrString, label, operand, comment, mneName, opcode;

    public ExeGenerator(IIR IR, String fileName, SymbolTable st, LabelTable lt) {

        opcode = addrString = comment = operand = mneName = label = "";
        pseudoAddressTable = new HashMap<Integer, Integer>();
        lineStatementSizeTable = new HashMap<Integer, Integer>();

        // Traverse the IR and fill in lineStatementSizeTable and pseudoAddressTable

        for (int i = 0; i < IR.getSize(); i++) {

            lineStatementSize = 0;

            ILineStatement temp = IR.getLineStatement(i);
            Instruction instruction = (Instruction) temp.getInstruction();
            Directive directive = (Directive) temp.getDirective();


            if (instruction != null) {
                lineStatementSize = instruction.getSize();
            }
            if (directive != null) {
                lineStatementSize = directive.getSize();
            }
            lineStatementSizeTable.put(i + 1, lineStatementSize); // Rows start at 1

            pseudoAddress += lineStatementSize;
            pseudoAddressTable.put(i + 1, pseudoAddress);  // Rows start at 1

        }

        // Iterate through the symbol table and find relative instruction with label operands

        symbolTable = st.getSymbolTable();
        Set entrySet = symbolTable.entrySet();
        Iterator iterator = entrySet.iterator();

        // Instantiate Label Table
        labelTable = lt.getLabelTable();

        while (iterator.hasNext()) {

            Map.Entry mapEntry = (Map.Entry) iterator.next();
            currentToken = (Token) mapEntry.getValue();
            currentTokenType = currentToken.getCode();

            if (currentTokenType == TokenType.LabelOperand) {

                // Get the address of this label operand
                currentRow = currentToken.getPosition().getRow();
                labelOperandAddress = pseudoAddressTable.get(currentRow);

                // Get current instruction size
                currentInstructionSize = lineStatementSizeTable.get(currentRow);

                // Get address of corresponding label
                correspondingLabel = labelTable.get(currentToken.getName());
                labelTableRow =  correspondingLabel.getPosition().getRow();
                labelTableAddress = pseudoAddressTable.get(labelTableRow);

                // Calculate offset
                offset = labelTableAddress - labelOperandAddress;

                // Resolve offsets
                ILineStatement currentLineStatement = IR.getLineStatement(currentRow - 1);
                Instruction currentInstruction = (Instruction) currentLineStatement.getInstruction();
                currentInstruction.setLabelOperandOffset(offset);
            }

            File exeFile = new File(fileName + ".exe");

            // Writing the data using DataOutputStream
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(exeFile));
                //Loop through all line statements, adding the code to .EXE file.

                for (int i = 0; i < IR.getSize(); i++) {

                    ILineStatement temp = IR.getLineStatement(i);
                    Instruction instruction = (Instruction) temp.getInstruction();
                    Directive directive = (Directive) temp.getDirective();

                    if (instruction != null) {

                        Mnemonic mne = (Mnemonic) instruction.getMnemonic();

                        // Different cases of instructions

                        if (mne.isRelative() && mne.needsNumber()) {
                            dataOutputStream.writeByte(mne.getOpcode());
                            dataOutputStream.writeByte(instruction.getNumberInt());
                        }
                        else if (mne.isRelative() && mne.needsLabel()) {

                            // If it is a 1 byte offset
                            if (!instruction.isLabelOperandResolved()) {
                                // Error, not all offsets are resolved
                                System.out.println("Offset not resolved");

                            } else {
                                // Data offsets are resolved
                                if (mne.getName().contains(".i16")) {
                                    // More than 1 byte
                                    String offsetStr = String.format("%04X", instruction.getLabelOperandOffset());

                                    // To get the last 4
                                    offsetStr = offsetStr.substring(offsetStr.length()-4);
                                    String topByteStr = offsetStr.substring(0,2);
                                    String bottomByteStr = offsetStr.substring(2,4);
                                    int topByteInt = Integer.parseInt(topByteStr);
                                    int bottomByteInt = Integer.parseInt(bottomByteStr);

                                    dataOutputStream.writeByte(instruction.getLabelOperandOffset());
                                    dataOutputStream.writeByte(topByteInt);
                                    dataOutputStream.writeByte(bottomByteInt);
                                }
                                else {
                                    // 1 byte offset
                                    dataOutputStream.writeByte(mne.getOpcode());
                                    dataOutputStream.writeByte(instruction.getLabelOperandOffset());
                                }
                            }
                        }
                        else {
                            // Inherent and immediate instructions
                            dataOutputStream.writeByte(mne.getOpcode());
                        }

                    } else if (directive != null) {
                        int[] directiveArray = directive.getCharValueArray();
                        for (int j = 0; j < directiveArray.length; j++) {

                            dataOutputStream.writeByte(directiveArray[j]);

                        }
                    }
                }
            }
            catch(FileNotFoundException e){
                    System.out.println("Cannot Open the Output File");
                    return;
                } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

