import java.util.ArrayList;
/**
 * DummyLexer Class used to test the Parser class with an arraylist of hardcoded Token objects
 */
public class DummyLexer extends Lexer{
    //integer to determine the position needed in the array
    private int positionCounter = 0;

    // Arraylist of hardcoded testcases (Token objects)
    private ArrayList<Token> tokenList = new ArrayList<Token>();

    //Constructor used in the TestParser class
    public DummyLexer(ArrayList<Token> tokens){
        tokenList = tokens;
    }

    public DummyLexer() {
        super();
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
