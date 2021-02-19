import java.util.ArrayList;

/**
 * Parser
 */
public class Parser {
    //IR is an ArrayList of LineStatements
    ArrayList<LineStatement> IR;

    public Parser() {
        IR = new ArrayList<LineStatement>();
    }

    public ArrayList<LineStatement> getIR() {
        return IR;
    }

    //TODO May need to change input type to lexer. I am not sure if it is possible to do it this way since eventually we need to get labels and operands...
    public boolean parseToken(IToken token) {
        //If token is a mnemonic...
        if (token instanceof IMnemonic) {
            
    } else { //We add other checks here
        return false;
    }
        return true;
    }
    
}