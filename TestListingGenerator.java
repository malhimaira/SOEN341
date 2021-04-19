import java.util.TreeMap;

/**
 * Tests ListingGenerator using stubbed (hardcoded) IR. Generates a lst file called StubbedIR.lst
 */
public class TestListingGenerator {

    public static void main(String[] args) {
    Instruction nullInstruction = null; //Keep compiler happy
    
    
  //Mapping for mnemonic
	TreeMap<String,Integer> mapping = new TreeMap<String,Integer>();
    Mapper mapper = new Mapper(mapping);
    LabelTable lt = new LabelTable(); //Keep compiler happy.
  
    //Stub of the IR, hardcoded values.
    IIR stubIR = new IR();
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("halt",false,new Position(0,0), mapping))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("exit",false,new Position(1,0), mapping))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("and",false,new Position(2,0), mapping))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("enter.u5",true,new Position(2,0), mapping), new Number("31",new Position(2,1)))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("ldc.i3",true,new Position(3,0), mapping), new Number("-4",new Position(3,1))),new Comment(";Test Comment", new Position(3,2))));
    stubIR.add(new LineStatement(nullInstruction,new Comment(";Test Comment No Instruction", new Position(4,0))));
    stubIR.add(new LineStatement(nullInstruction));
    stubIR.add(new LineStatement(new Directive(new StringOperand("\"ABC\"",new Position (5,1)),new Position(5,0)),new Comment(";Test Comment 2", new Position(5,2))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("or",false,new Position(6,0), mapping))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("xor",false,new Position(7,0), mapping))));
    stubIR.add(new LineStatement(new Instruction(new Mnemonic("neg",false,new Position(8,0), mapping))));

    
    IListingGenerator slg = new ListingGenerator(stubIR,"StubbedIR",lt);
}
}//end of StubIR Interface
