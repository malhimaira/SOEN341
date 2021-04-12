import java.util.TreeMap;

/**
 * Tests ListingGenerator using stubbed (hardcoded) IR. Generates a lst file called StubbedIR.lst
 */
public class TestListingGenerator {

    public static void main(String[] args) {
    Instruction nullInstruction = null; //Keep compiler happy
    
    
  //Mapping for mnemonic
	TreeMap<String,Integer> mapping = new TreeMap<String,Integer>();
    mapping.put("halt",0x00);
    mapping.put("pop",0x01);
    mapping.put("dup",0x02);
    mapping.put("exit",0x03);
    mapping.put("ret",0x04);
    mapping.put("not",0x0C);
    mapping.put("and",0x0D);
    mapping.put("or",0x0E);
    mapping.put("xor",0x0F);
    mapping.put("neg",0x10);
    mapping.put("inc",0x11);
    mapping.put("dec",0x12);
    mapping.put("add",0x13);
    mapping.put("sub",0x14);
    mapping.put("mul",0x15);
    mapping.put("div",0x16);
    mapping.put("rem",0x17);
    mapping.put("shl",0x18);
    mapping.put("shr",0x19);
    mapping.put("teq",0x1A);
    mapping.put("tne",0x1B);
    mapping.put("tlt",0x1C);
    mapping.put("tgt",0x1D);
    mapping.put("tle",0x1E);
    mapping.put("tge",0x1F);

    //Immediate instructions (opcode given here is starting value)
   mapping.put("enter.u5",0x70);
   mapping.put("ldc.i3",0x90);
   mapping.put("addv.u3",0x98);
   mapping.put("ldv.u3",0xA0);
   mapping.put("stv.u3",0xA8);
   
 //Relative Instructions
   mapping.put("br.i8", 0xE0);
   mapping.put("brf.i8", 0xE3);
   mapping.put("ldc.i8", 0xD9);
   mapping.put("ldv.u8", 0xB1);
   mapping.put("stv.u8", 0xB2);
   mapping.put("lda.i16", 0xDA);

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

    
    IListingGenerator slg = new ListingGenerator(stubIR,"StubbedIR");
}
}//end of StubIR Interface
