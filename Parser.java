import java.util.ArrayList;

/**
 * Parser class used to parse through the tokens provided by the Lexer
 */
public class Parser implements IParser{
    //IR is an ArrayList of LineStatements
    ArrayList<ILineStatement> IR;

    /**
     * Constructor Method
     */
    public Parser() {
        IR = new ArrayList<ILineStatement>();
    }

    /**
     * Method that gets the IR ArrayList containing the Line Statements
     * @return the IR Array List
     */
    public ArrayList<ILineStatement> getIR() {
        return IR;
    }

    //TODO May need to change input type to lexer (DONE). I am not sure if it is possible to do it this way since eventually we need to get labels and operands...
    /**
     * Method used to Parse the tokens obtained from the Lexer object
     * @param lexer object that contains the Tokens to be read
     * @return boolean value that indicates if the parsing worked properly
     */
    public boolean parseTokens(ILexer lexer) {
        // Initializing values for each line statement
        
        //TODO Change names cuz teacher mean
        Token currentToken = null;
        Mnemonic monica = null;
        Instruction instructor = null;
        IOperand opera = null;
        LineStatement linux = null;

        while ((currentToken = lexer.getNextToken()).getCode() != TokenType.EOF) {
            //If token is a mnemonic...
            //System.out.println(currentToken); //For debugging
            if (currentToken.getCode() == TokenType.Mnemonic) {
                //System.out.println("Current token is a Mnemonic!");
                monica = (Mnemonic) currentToken;
            //If token is a Label
            } else if (currentToken.getCode() == TokenType.Label) {
                //System.out.println("Current token is a Label!");
                // TODO SPRINT 3
            //If token is EOL
            } else if (currentToken.getCode() == TokenType.EOL) {
                //System.out.println("Current token is a EOL!");
                // TODO SPRINT 2
                if (monica != null) {
                    if (opera != null) {
                        //TODO : instructor = new Instruction(monica, opera);
                    }else {
                        instructor = new Instruction(monica);
                    }
                }
                //TODO change this based on presence of comments, labels and directives
                linux = new LineStatement(instructor);
                // Adding LineStatement to IR ArrayList
                IR.add(linux);

                //Reset the values for next line statements
                currentToken = null;
                monica = null;
                instructor = null;
                opera = null;
                linux = null;

            } else { //TODO We add other checks here (comments, labels and directives)
                System.out.println("Current token was not recognized!");
                return false;
            }
        }
        return true;
    }
}