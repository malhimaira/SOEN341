import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * DummyLexer Class used to test the Parser class with an arraylist of hardcoded Token objects
 */
public class BrokenDummyLexer extends Lexer{
    //integer to determine the position needed in the array
    private int positionCounter = 0;
    ArrayList<Token> tokenList;
    HashMap<String, Label> labelTable;
    //Constructor used in the TestParser class
    public BrokenDummyLexer(){
        // Initializing an ArrayList of Tokens to use as a Hardcoded Test case
        tokenList = new ArrayList<Token>();
        this.labelTable = new HashMap<String, Label>();
        labelTable.put("testLabel",new Label("testLabel",new Position(9,1)));

        
        //Mapping for mnemonics
        TreeMap<String,Integer> mapping = new TreeMap<String,Integer>();
        new Mapper(mapping);


        //Adding Tokens to the ArrayList
        tokenList.add(new Mnemonic("popp",false,new Position(1,1), mapping)); // Case where Opcode is -1
        tokenList.add(new EOL("EOL", new Position(1,2)));

        tokenList.add(new Directive(new Position (2,1))); // Case where Directive without a String is caught
        tokenList.add(new StringOperand("ABC\"",new Position (2,2))); // Case where string does not start with \"
        tokenList.add(new StringOperand("\"ABC",new Position (2,3))); // Case where string does not end with \"
        tokenList.add(new EOL("EOL", new Position(2,4)));

        tokenList.add(new StringOperand("\"ABC\"",new Position (3,1))); // Case where string does not have a Directive
        tokenList.add(new EOL("EOL",new Position(3,2)));

        tokenList.add(new Number("-3",new Position (4,1))); // Case where number is caught before a Mnemonic
        tokenList.add(new Mnemonic("ldc.i3", true, new Position (4,2), mapping));
        tokenList.add(new EOL("EOL",new Position(4,3)));

        tokenList.add(new Number("31",new Position (5,1))); //Case where line has a number but not a Mnemonic
        tokenList.add(new EOL("EOL", new Position (5,2)));

        tokenList.add(new Mnemonic("enter.u5", true, new Position (6,1), mapping));
        tokenList.add(new Number("32",new Position (6,2))); // Case where number is too small for .i3
        tokenList.add(new EOL("EOL", new Position (6,3)));

        tokenList.add(new Mnemonic("ldc.i3", true, new Position (7,1), mapping));
        tokenList.add(new Number("-5",new Position (7,2))); // Case where number is too small for .i3
        tokenList.add(new EOL("EOL", new Position (7,3)));

        tokenList.add(new Mnemonic("ldc.i3", true, new Position (8,1), mapping));
        tokenList.add(new Number("4",new Position (8,2))); // Case where number is too big for .i3
        tokenList.add(new EOL("EOL", new Position (8,3)));


        tokenList.add(new Label("testLabel", new Position (9,1)));
        tokenList.add(new Mnemonic("br.i8", true, new Position (9,2), mapping)); //Missing label operand
        tokenList.add(new EOL("EOL", new Position (9,3)));

        tokenList.add(new Label("testLabel", new Position (10,1))); //2 labels of same name
        tokenList.add(new Mnemonic("lda.i16", true, new Position (10,2), mapping));
        tokenList.add(new Label("badlabel", new Position (10,3))); //Bad label that doesn't exist
        tokenList.add(new EOL("EOL", new Position (10,4)));

        tokenList.add(new Mnemonic("ldc.i3", true, new Position (11,2), mapping));
        tokenList.add(new Label("testLabel",new Position (11,3))); // Case where number is is actually a label
        tokenList.add(new EOL("EOL", new Position (11,4)));

        tokenList.add(new Mnemonic("lda.i16", true, new Position (12,2), mapping));
        tokenList.add(new Number("4", new Position (12,3))); //Case where label operand is actually a number.
        tokenList.add(new EOL("EOL", new Position (12,4)));

        tokenList.add(new Mnemonic("stv.u8", true, new Position (13,2), mapping));
        tokenList.add(new Number("256", new Position (13,3))); //Case where number is out of range
        tokenList.add(new EOL("EOL", new Position (13,4)));

        tokenList.add(new EOF("EOF",new Position(14,1)));
        tokenList.add(new Comment(";Test Comment",new Position(9,2))); //Case where there is a token after the EOF
        tokenList.add(null);
    }

    public HashMap<String, Label> getLabelTable() {
        return labelTable;
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