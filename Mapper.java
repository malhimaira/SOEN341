import java.util.TreeMap;
/**
 * Helper class, sole purpose is to put the opcode mappings for the mnemonics as to reduce clutter.
 */
public class Mapper {
    public Mapper(TreeMap<String,Integer> mapping) {
        //Inherent Instructions
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
       mapping.put("lda.i16", 0xD5);
    }
}
