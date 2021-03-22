/**
 * Parser class used to parse through the tokens provided by the Lexer
 */
public class Parser implements IParser {
    // IR is a wrapper of an ArrayList of LineStatements
    IIR IR;
    ILexer lexer;

    /**
     * Constructor Method
     */
    public Parser(ILexer lexer) {
        IR = new IR();
        this.lexer = lexer;

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
                        //TODO Error reporter!!!
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
                            // TODO Report error!
                        } else {
                            if (currentMnemonic.needsNumber() == false) { // Instruction is inherent
                                // TODO Report error! Instruction is inherent and does NOT need an operand!
                            } else { // Everything is ok, add instruction with number operand.
                                currentInstruction = new Instruction(currentMnemonic, currentNumber);
                            }
                        }
                    } else {
                        currentInstruction = new Instruction(currentMnemonic);
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
            } else { // TODO We add other checks here (comments, labels and directives)
                System.out.println("Current token was not recognized!");
                return false;
            }
            //System.out.println(IR);
        }

            if (lexer.getNextToken() != null) {//We have another token after the EOF, this is a problem.
                System.out.println("ERROR!!!");
                //TODO ERROR REPORTER
            }

        return true;
    }
}