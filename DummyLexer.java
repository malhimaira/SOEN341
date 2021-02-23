import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DummyLexer extends Lexer{

    private HashMap<String, Token> SymbolTable = new HashMap<String, Token>();
    private Iterator<Map.Entry<String, Token>> iter;
    private int positionCounter = 0;

    private ArrayList<Token> tokenList = new ArrayList<Token>();

    public DummyLexer(ArrayList<Token> tokens){
        tokenList = tokens;
    }

    public DummyLexer() {
        super();
    }

    public Token getNextToken() {
        Token t = null;
        if(tokenList.get(positionCounter) != null) {
            t = tokenList.get(positionCounter++);
        }
        return t; //Team 4 please handle the case where the Token is null, if it is then just don't process it.
    }
}
