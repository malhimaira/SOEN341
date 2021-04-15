import java.util.HashMap;
import java.util.ArrayList;

/**
 * Parser class used to parse through the tokens provided by the Lexer
 */
public class Parser implements IParser {
    // IR is a wrapper of an ArrayList of LineStatements
    IIR IR;
    ILexer lexer;
    IErrorReporter errorReporter;
    HashMap<String, Label> labelTable;
    ArrayList<String> usedLabels; //Keeps tracks of labels already assigned on a line

    /**
     * Constructor Method
     */
    public Parser(ILexer lexer, IErrorReporter errorReporter, boolean verboseEnabled) {
        IR = new IR();
        this.lexer = lexer;
        this.errorReporter = errorReporter;
        this.labelTable = lexer.getLabelTable();
        this.usedLabels = new ArrayList<String>();
    }

    /**
     * Parses the .asm file using the lexer. Calls the internal parseTokens method.
     *
     * @return The IR ArrayList of LineStatements
     */
    public IIR parse() {
        parseTokens(lexer);
        return getIR();
    }

    /**
     * Method that gets the IR ArrayList containing the Line Statements
     *
     * @return the IR Array List
     */
    public IIR getIR() {
        return IR;
    }

    /**
     * Method used to Parse the tokens obtained from the Lexer object
     *
     * @param lexer object that contains the Tokens to be read
     * @return boolean value that indicates if the parsing worked properly
     */
    private boolean parseTokens(ILexer lexer) {
        // Initializing values for each line statement
        Token currentToken = null;
        Mnemonic currentMnemonic = null;
        Instruction currentInstruction = null;
        Number currentNumber = null;
        LineStatement currentLineStatement = null;
        Comment currentComment = null;
        Directive currentDirective = null;
        StringOperand currentString = null;
        Label currentLabel = null;
        Label labelOperand = null;

        while ((currentToken = lexer.getNextToken()).getCode() != TokenType.EOF) {
            //Debug System.out.println(currentToken);
            // If token is a mnemonic...
            // System.out.println(currentToken); //For debugging
            if (currentToken.getCode() == TokenType.Mnemonic) {
                // System.out.println("Current token is a Mnemonic!"); //For debugging
                currentMnemonic = (Mnemonic) currentToken;

                if (currentMnemonic.getOpcode() == -1) //Invalid opcode
                {
                    //TODO -- DONE
                    ErrorMsg invalidMnemonicError = new ErrorMsg("Invalid mnemonic: " + currentMnemonic.getName() + "!", currentMnemonic.getPosition());
                    errorReporter.record(invalidMnemonicError);
                    invalidMnemonicError = null; //Set it to null after
                }
                // If token is a Label
            } else if (currentToken.getCode() == TokenType.Label) {
                if (currentToken.getPosition().getColumn() == 1 || currentToken.getPosition().getColumn() == 2) {//If it's the first column, then it's a regular label, otherwise it's a label operand.
                    currentLabel = (Label) currentToken;
                    //We already have a label with this name! Error!
                    if (usedLabels.contains(currentLabel.getName())) {
                        ErrorMsg labelAlreadyUsedError = new ErrorMsg("Current label: \"" + currentLabel.getName() + "\" already defined", currentLabel.getPosition());
                        errorReporter.record(labelAlreadyUsedError);
                        labelAlreadyUsedError = null;
                    } else
                        usedLabels.add(currentLabel.getName()); //We've now used this up
                }else
                    labelOperand = (Label) currentToken;
                // System.out.println("Current token is a Label!"); //For debugging
                // TODO SPRINT 4
            //If the current token is a directive (i.e the .cstring)
            } else if (currentToken.getCode() == TokenType.Directive) {
                currentDirective = (Directive) currentToken;

            //Current token is a string
            } else if (currentToken.getCode() == TokenType.StringOperand) {
                currentString = (StringOperand) currentToken;
                //Check if currentString is of form "...", if not, error!
                if (!(currentString.getName().startsWith("\"") && currentString.getName().endsWith("\""))) {
                    //TODO -- DONE
                    ErrorMsg badStringError = new ErrorMsg("String " + currentString.getName() + " must be of the form \"...\"!", currentString.getPosition());
                    errorReporter.record(badStringError);
                    badStringError = null;
                } else { //We have a good string
                    //No directive but a string, this is a problem!
                    if (currentDirective == null) {
                        //TODO -- DONE
                        ErrorMsg stringAloneError = new ErrorMsg("String " + currentString.getName() + " appears without .cstring!",currentString.getPosition());
                        errorReporter.record(stringAloneError);
                        stringAloneError = null;
                    } else { //We have our directive and string, all is good so we can add the StringOperand as the operand of the directive
                        currentDirective.addStringOperand(currentString);
                    }

                }
            }else if (currentToken.getCode() == TokenType.Number) {
                currentNumber = (Number) currentToken;

                // If token is a comment
            } else if (currentToken.getCode() == TokenType.Comment) {
                currentComment = (Comment) currentToken;
                // If token is EOL
            } else if (currentToken.getCode() == TokenType.EOL) {
                // System.out.println("Current token is a EOL!"); //For debugging
                if (currentMnemonic != null) {
                    if (currentNumber != null) { // We have a number on this line
                        //Number appears before instruction in the line.
                        if (currentNumber.getPosition().getColumn() < currentMnemonic.getPosition().getColumn()) {
                            //TODO -- DONE
                            ErrorMsg orderError = new ErrorMsg("Current Line contains a number: " + currentNumber.getName() + " appearing before the Mnemonic " + currentMnemonic.getName() + "!",currentMnemonic.getPosition());
                            errorReporter.record(orderError);
                            orderError = null;
                        } else {
                            if (!currentMnemonic.needsNumber() && !currentMnemonic.isRelative()) { // Instruction is inherent
                                //TODO -- DONE
                                ErrorMsg operandError = new ErrorMsg("Current instruction: " + currentMnemonic.getName() + " is an inherent instruction and does not require an operand!",currentMnemonic.getPosition());
                                errorReporter.record(operandError);
                                operandError = null;
                            } else { // Everything is ok, add instruction with number operand.
                                currentInstruction = new Instruction(currentMnemonic, currentNumber);
                                //Checking if any errors happened during the creation of the Instruction object, including operand out of bounds
                                if(currentInstruction.errorOccurred()) {
                                    //TODO -- DONE (Over and under)
                                    ErrorMsg creationError = new ErrorMsg(currentInstruction.errorString(), currentMnemonic.getPosition());
                                    errorReporter.record(creationError);
                                    creationError = null;
                                }
                            }
                        }
                    } else if(labelOperand != null ){
                        //labelOperand is not in the labelTable
                        if (!labelTable.containsKey(labelOperand.getName())) {
                            ErrorMsg labelNotFound = new ErrorMsg("Current label operand: \"" + labelOperand.getName() + "\" not found (or defined).", labelOperand.getPosition());
                            errorReporter.record(labelNotFound);
                            labelNotFound = null;
                        }

                        if (labelOperand.getPosition().getColumn() < currentMnemonic.getPosition().getColumn()) {
                            ErrorMsg orderError = new ErrorMsg("Current Line contains a label: " + labelOperand.getName() + " appearing before the Mnemonic " + currentMnemonic.getName() + "!",currentMnemonic.getPosition());
                            errorReporter.record(orderError);
                            orderError = null;
                        } else {
                            if (!currentMnemonic.needsLabel()) { // Instruction is inherent
                                //TODO -- DONE
                                ErrorMsg operandError = new ErrorMsg("Current instruction: " + currentMnemonic.getName() + " does not require a label operand!",currentMnemonic.getPosition());
                                errorReporter.record(operandError);
                                operandError = null;
                            } else {
                                currentInstruction = new Instruction(currentMnemonic,labelOperand); //Set current instruction to taking a labelOperand
                                if(currentInstruction.errorOccurred()) {
                                    //TODO -- DONE (Label found but should not have been there)
                                    ErrorMsg creationError = new ErrorMsg(currentInstruction.errorString(), currentMnemonic.getPosition());
                                    errorReporter.record(creationError);
                                    creationError = null;
                                }

                            }
                        }
                    } else {
                        //No operand
                        currentInstruction = new Instruction(currentMnemonic);
                        if (!currentInstruction.isInherent()) {
                            ErrorMsg needsOperandError = new ErrorMsg("Current instruction " + currentMnemonic.getName() +
                                    " requires a " + ((currentMnemonic.needsNumber()) ? "number " : "label ") + " operand.",currentMnemonic.getPosition());
                            errorReporter.record(needsOperandError);
                            needsOperandError = null;
                        }
                    }
                } else { //No mnemonic on this line, so we should not have an operand!
                    if (currentNumber != null) {
                        // TODO error handler -- DONE
                        ErrorMsg noInstructionError = new ErrorMsg("Current Line contains a number: " + currentNumber.getName() +
                                " without a Mnemonic!", currentNumber.getPosition());
                        errorReporter.record(noInstructionError);
                        noInstructionError = null;
                    }
                }
                // TODO change this based on presence of comments, labels and directives
                if (currentInstruction != null) { // We have an instruction
                    if (currentComment != null) { // We have a comment
                        currentLineStatement = new LineStatement(currentLabel, currentInstruction, currentComment);
                    } else // We don't have a comment
                        currentLineStatement = new LineStatement(currentLabel, currentInstruction,null);
                } else if (currentInstruction == null && currentDirective == null){ // we don't have an instruction or directive

                    if (currentComment != null) { // We have a comment
                        currentLineStatement = new LineStatement(currentLabel,currentInstruction, currentComment); //Null instruction
                    } else // We don't have a comment
                        currentLineStatement = new LineStatement(currentLabel, currentInstruction, null); //Null
                } else if (currentDirective != null) { //We have a directive
                    currentLineStatement = new LineStatement(currentLabel, currentDirective, currentComment); //Works if we have a comment or not, as comment will just be null. Could do same for above cases

                    if (currentDirective.getStringOperand() == null){
                        // The directive does not have a StringOperand assigned to it
                        //TODO -- DONE
                        ErrorMsg emptyDirectiveError = new ErrorMsg("Directive: " + currentDirective.getName() +" appears without a String!", currentDirective.getPosition());
                        errorReporter.record(emptyDirectiveError);
                    }
                }

                // Adding LineStatement to IR ArrayList
                IR.add(currentLineStatement);
                // Reset the values for next line statements
                currentToken = null;
                currentMnemonic = null;
                currentInstruction = null;
                currentNumber = null;
                currentLineStatement = null;
                currentComment = null;
                currentDirective = null;
                currentString = null;
                currentLabel = null;
                labelOperand = null;
            } else { // TODO We add other checks here (labels and directives)
                System.out.println("Current token was not recognized!");
                return false;
            }
            //System.out.println(IR);
        }

        if ((currentToken = lexer.getNextToken()) != null) {//We have another token after the EOF, this is a problem.
            // TODO error handler -- DONE
            ErrorMsg eofError = new ErrorMsg("Additional token detected after the EOF token!", currentToken.getPosition());
            errorReporter.record(eofError);
        }

        return true;
    }

    //TODO Not sure about this
    private void verboseOutputPass1() {
        HashMap<String,Token> symbolTable = lexer.getSymbolTable();
        System.out.println("Symbol Table Pass 1:");
        System.out.println(String.format("%-10s %-10s %-20s","Name","Type","Addr/code"));
        for (Token token : symbolTable.values()) {
            if (token.getCode() == TokenType.Mnemonic)
            {
                Mnemonic mne = (Mnemonic) token;
                System.out.println(String.format("%-10s %-10s %-20s",token.getName(),"Mnemonic",mne.getOpcode()));
            } else if (token.getCode() == TokenType.Label)
                System.out.println(String.format("%-10s %-10s %-20s",token.getName(),"Label",""));
        }

    }

}