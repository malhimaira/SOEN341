import java.util.TreeMap;
/**
 * Instruction Class
 * Takes in mnemonic string and maps it to a corresponding integer opcode (-1 if invalid). Can return a string hex representation of that opcode as well.
 * 
 */
public class Instruction {
    private int opcode; //Integer format opcode
    private TreeMap<String,Integer> mapping; //Using a TreeMap for mapping as it is very efficient for searching.
    Instruction() {
        opcode = -1;
        mapping = new TreeMap<String,Integer>();
        mapping.put("halt",0);
        mapping.put("pop",1);
        mapping.put("dup",2);
        mapping.put("exit",3);
        mapping.put("ret",4);
        mapping.put("not",12);
        mapping.put("and",13);
        mapping.put("or",14);
        mapping.put("xor",15);
        mapping.put("neg",16);
        mapping.put("inc",17);
        mapping.put("dec",18);
        mapping.put("add",19);
        mapping.put("sub",20);
        mapping.put("mul",21);
        mapping.put("div",22);
        mapping.put("rem",23);
        mapping.put("shl",24);
        mapping.put("shr",25);
        mapping.put("teq",26);
        mapping.put("tne",27);
        mapping.put("tlt",28);
        mapping.put("tgt",29);
        mapping.put("tle",30);
        mapping.put("tge",31);
    }
    /**
     * Sets opcode for the instruction.
     * @param mnemonic
     */
    Instruction(String mnemonic) {
        this();
        opcode = findOpcode(mnemonic); //Set opcode to opcode found using helper method
        

    }

    /**
     * Helper method which checks if the mnemonic is a valid operation
     * @param mnemonic String which represents the mnemonic
     * @return Boolean value which indicates the validity of the instruction 
     */
    public boolean isValidOperation(String mnemonic) {
        if (mapping.containsKey(mnemonic))
            return true;
        else
            return false;
    }

    /**
     * Helper method which finds the opcode related the the instruction, returns -1 if the instruction is invalid
     * @param mnemonic String mnemonic value to be tested
     * @return Integer representing the opcode
     */
    private int findOpcode(String mnemonic) {
        int testOpcode = -1; //Default to -1
        if(isValidOperation(mnemonic)) //if the mapping contains the mnemonic, set testOpcode to that opcode, otherwise opcode is set to -1
            testOpcode = mapping.get(mnemonic);
        
        opcode = testOpcode;
        return opcode;
    }

    /**
     * Gets the integer opcode
     * @return integer opcode
     */
    public int getOpcode() {
        return opcode;
    }

    /**
     * toString() returns hex representation of the opcode.
     */
    public String toString() {
        return Integer.toHexString(opcode).toUpperCase();
    }
}
