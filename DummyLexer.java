import java.util.ArrayList;
import java.util.TreeMap;
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

        
        //Mapping for mnemonics
        TreeMap<String,Integer> mapping = new TreeMap<String,Integer>();
        new Mapper(mapping);

        //Adding Tokens to the ArrayList
        tokenList.add(new Mnemonic("halt",false,new Position(1,1), mapping));
        tokenList.add(new EOL("EOL", new Position(1,2)));

        tokenList.add(new Mnemonic("and",false,new Position(2,1), mapping));
        tokenList.add(new EOL("EOL", new Position(2,2)));

        tokenList.add(new Mnemonic("shl",false,new Position(3,1), mapping));
        tokenList.add(new EOL("EOL", new Position(3,2)));

        tokenList.add(new Mnemonic("tgt",false, new Position (4,1), mapping));
        tokenList.add(new EOL("EOL", new Position (4,2)));

        tokenList.add(new Mnemonic("exit",false,new Position(5,1), mapping));
        tokenList.add(new EOL("EOL",new Position(5,2)));

        tokenList.add(new Mnemonic("ldc.i3", true, new Position (6,1), mapping));
        tokenList.add(new Number("-3",new Position (6,2)));
        tokenList.add(new EOL("EOL",new Position(6,3)));
        tokenList.add(new Mnemonic("enter.u5", true, new Position (7,1), mapping));
        tokenList.add(new Number("31",new Position (7,2)));
        tokenList.add(new Comment(";Test Comment",new Position(7,3)));
        tokenList.add(new EOL("EOL", new Position (7,4)));

        tokenList.add(new StringOperand("\"ABC\"",new Position (8,1))); // Testing Cstring
        tokenList.add(new Comment(";Comment and no Instruction",new Position(8,1)));
        tokenList.add(new EOL("EOL", new Position (8,2)));

        tokenList.add(new Label("testLabel", new Position (9,1)));
        tokenList.add(new Mnemonic("br.i8", true, new Position (9,2), mapping));
        tokenList.add(new Label("testLabel", new Position (9,3)));
        tokenList.add(new EOL("EOL", new Position (9,4)));

        tokenList.add(new Mnemonic("lda.i16", true, new Position (10,2), mapping));
        tokenList.add(new Label("testLabel", new Position (10,3)));
        tokenList.add(new EOL("EOL", new Position (10,4)));

        tokenList.add(new EOF("EOF",new Position(11,0)));
        tokenList.add(null);
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