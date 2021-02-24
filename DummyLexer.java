import java.util.ArrayList;
/**
 * DummyLexer Class used to test the Parser class with an arraylist of hardcoded Token objects
 */
public class DummyLexer extends Lexer{
    //integer to determine the position needed in the array
    private int positionCounter = 0;
    ArrayList<Token> tokenList;

    //Constructor used in the TestParser class
    public DummyLexer(){
        // Initializing an ArrayList of Tokens to use as a Hardcoded Test case
        tokenList = new ArrayList<Token>();

        //Adding Tokens to the ArrayList
        tokenList.add(new Mnemonic("halt"));
        tokenList.add(new EOL("EOL"));
 
        tokenList.add(new Mnemonic("and"));
        tokenList.add(new EOL("EOL"));
 
        tokenList.add(new Mnemonic("shl"));
        tokenList.add(new EOL("EOL"));
 
        tokenList.add(new Mnemonic("tgt"));
        tokenList.add(new EOL("EOL"));
 
        tokenList.add(new Mnemonic("exit"));
        tokenList.add(new EOL("EOL"));
 
        tokenList.add(new EOF("EOF"));
    }

    /**
     * Method to get the next token from the arraylist
     * @return Token
     */
    public Token getNextToken() {
        Token t = null;
        if(tokenList.get(positionCounter) != null) {
            t = tokenList.get(positionCounter++);
        }
        return t;
    }
}
