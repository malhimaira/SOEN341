/**
 * Tests ListingGenerator using stubbed (hardcoded) IR. Generates a lst file called StubbedIR.lst
 */
public class TestListingGenerator {

    public static void main(String[] args) {
    //Stub of the IR, hardcoded values.
    IIR stubIR = new IR();
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("halt",false,new Position(0,0)))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("exit",false,new Position(1,0)))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("and",false,new Position(2,0)))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("or",false,new Position(3,0)))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("xor",false,new Position(4,0)))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("neg",false,new Position(5,0)))));

    
    IListingGenerator slg = new ListingGenerator(stubIR,"StubbedIR");
}
}//end of StubIR Interface
