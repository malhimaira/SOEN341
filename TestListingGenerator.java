
import java.util.ArrayList;

/**
 * Tests ListingGenerator using stubbed (hardcoded) IR. Generates a lst file called StubbedIR.lst
 */
public class TestListingGenerator {

    public static void main(String[] args) {
    ArrayList<ILineStatement> stubIR = new ArrayList<ILineStatement>() ;
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("halt"))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("exit"))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("and"))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("or"))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("xor"))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("neg"))));

    
    IListingGenerator slg = new ListingGenerator(stubIR,"StubbedIR");
}
}//end of StubIR Interface
