import java.util.TreeMap;

public class TestInstructionSize {

    public static void main(String[] args) {
        TreeMap<String,Integer> mapping = new TreeMap<String,Integer>();

        new Mapper(mapping); //Set up the tree map properly with opcodes for the mnemonics;

        Instruction i1 = new Instruction(new Mnemonic("halt", false, new Position(1,1), mapping)); //Inherent 1 byte
        Instruction i2 = new Instruction(new Mnemonic("ldc.i3", true, new Position(2,1), mapping)); //Immediate 1 byte
        Instruction i3 = new Instruction(new Mnemonic("enter.u5", true, new Position(3,1), mapping)); //Immediate 1 byte
        Instruction i4 = new Instruction(new Mnemonic("br.i8", false, new Position(4,1), mapping)); //Relative 2 bytes
        Instruction i5 = new Instruction(new Mnemonic("lda.i16", false, new Position(5,1), mapping)); //Relative 3 bytes

        System.out.println("Test Instruction Size");
        System.out.println("halt: 1 byte, ldc.i3: 1 byte, enter.u5: 1 byte, br.i8: 2 bytes, lda.i16: 3 bytes");
        
        String output = "";
        output += i1.getMnemonic().getName() + ": " + i1.getSize() + ((i1.getSize() < 2) ? " byte, " : " bytes, ");
        output += i2.getMnemonic().getName() + ": " + i2.getSize() + ((i2.getSize() < 2) ? " byte, " : " bytes, ");
        output += i3.getMnemonic().getName() + ": " + i3.getSize() + ((i3.getSize() < 2) ? " byte, " : " bytes, ");
        output += i4.getMnemonic().getName() + ": " + i4.getSize() + ((i4.getSize() < 2) ? " byte, " : " bytes, ");
        output += i5.getMnemonic().getName() + ": " + i5.getSize() + ((i5.getSize() < 2) ? " byte" : " bytes");

        System.out.println(output);


    }

} 

