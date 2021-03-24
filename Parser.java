/**
 * Parser class used to parse through the tokens provided by the Lexer
 */
public class Parser implements IParser {
    // IR is a wrapper of an ArrayList of LineStatements
    IIR IR;
    ILexer lexer;
    ErrorReporter errorReporter;

    /**
     * Constructor Method
     */
    public Parser(ILexer lexer, ErrorReporter errorReporter) {
        IR = new IR();
        this.lexer = lexer;
        this.errorReporter = errorReporter;
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

        while ((currentToken = lexer.getNextToken()).getCode() != TokenType.EOF) {
            //Debug System.out.println(currentToken);
            // If token is a mnemonic...
            // System.out.println(currentToken); //For debugging
            if (currentToken.getCode() == TokenType.Mnemonic) {
                // System.out.println("Current token is a Mnemonic!"); //For debugging
                currentMnemonic = (Mnemonic) currentToken;

                if (currentMnemonic.getOpcode() == -1) //Invalid opcode
                {
                    // TODO error handler
                    ErrorMsg opcodeError = new ErrorMsg("Current Line's Mnemonic: " + currentMnemonic.getName() + " contains an invalid opcode of: " + currentMnemonic.getOpcode() + "!", currentMnemonic.getPosition());
                }
                // If token is a Label
            } else if (currentToken.getCode() == TokenType.Label) {
                // System.out.println("Current token is a Label!"); //For debugging
                // TODO SPRINT 4

            } else if (currentToken.getCode() == TokenType.Number) {
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
                            // TODO error handler
                            ErrorMsg orderError = new ErrorMsg("Current Line contains a number: " + currentNumber.getName() + " appearing before the Mnemonic" + currentMnemonic.getName() + "!",currentMnemonic.getPosition());
                        } else {
                            if (currentMnemonic.needsNumber() == false) { // Instruction is inherent
                                // TODO error handler
                                ErrorMsg operandError = new ErrorMsg("Current Mnemonic: " + currentMnemonic.getName() + " is an Inherent Instruction and does not require an operand!",currentMnemonic.getPosition());
                            } else { // Everything is ok, add instruction with number operand.
                                currentInstruction = new Instruction(currentMnemonic, currentNumber);
                                //Checking if any errors happened during the creation of the Instruction object
                                if(currentInstruction.errorOccurred()) {
                                    // TODO error handler
                                    ErrorMsg creationError = new ErrorMsg(currentInstruction.errorString(), currentMnemonic.getPosition());
                                }
                            }
                        }
                    } else {
                        currentInstruction = new Instruction(currentMnemonic);
                    }
                } else { //No mnemonic on this line, so we should not have an operand!
                    if (currentNumber != null) {
                        // TODO error handler
                        ErrorMsg noInstructionError = new ErrorMsg("Current Line contains a number: " + currentNumber.getName() + " without a Mnemonic!", currentMnemonic.getPosition());
                    }
                }
                // TODO change this based on presence of comments, labels and directives
                if (currentInstruction != null) { // We have an instruction
                    if (currentComment != null) { // We have a comment
                        currentLineStatement = new LineStatement(currentInstruction, currentComment);
                    } else // We don't have a comment
                        currentLineStatement = new LineStatement(currentInstruction);
                } else { // we don't have an instruction

                    if (currentComment != null) { // We have a comment
                        currentLineStatement = new LineStatement(null, currentComment);
                    } else // We don't have a comment
                        currentLineStatement = new LineStatement(null);
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
            } else { // TODO We add other checks here (labels and directives)
                System.out.println("Current token was not recognized!");
                return false;
            }
            //System.out.println(IR);
        }

        if ((currentToken = lexer.getNextToken()) != null) {//We have another token after the EOF, this is a problem.
            // TODO error handler
            ErrorMsg eolError = new ErrorMsg("Additional token detected after the EOF token!", currentToken.getPosition());
        }

        return true;
    }
}