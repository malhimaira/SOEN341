import java.util.ArrayList;

/**
 * Testing Class used for aunit testing on the Parser class by using a DummyLexer class
 */
public class TestParser {

    public static void main(String[] args) {

        Parser parzival = new Parser();

        ArrayList<Token> array = new ArrayList<Token>();

        array.add(new Mnemonic("halt"));
        array.add(new EOL("EOL"));

        array.add(new Mnemonic("and"));
        array.add(new EOL("EOL"));

        array.add(new Mnemonic("shl"));
        array.add(new EOL("EOL"));

        array.add(new Mnemonic("tgt"));
        array.add(new EOL("EOL"));

        array.add(new Mnemonic("exit"));
        array.add(new EOL("EOL"));

        array.add(new EOF("EOF"));

        DummyLexer dlex = new DummyLexer(array);


        System.out.println("Test Parser");
        System.out.println("[halt 0, and D, shl 18, tgt 1D, exit 3]");

        parzival.parseTokens(dlex);
        //System.out.println("Actual Output: ");
        System.out.println(parzival.getIR());
    }
}
