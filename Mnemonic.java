/**
 * Class representing a Mnemonic Token, used to specify Mnemonic type
 */
import java.util.TreeMap;

public class Mnemonic extends Token implements IMnemonic {
	private String mName;
	private String mNameMap;
	private int opCode;
	private boolean needNumberToken;
	
    private TreeMap<String,Integer> mapping; //Using a TreeMap for mapping as it is very efficient for searching.

    /**
	 * Constructor used to specify the type of the mnemonic given
	 */
	public Mnemonic(String mnemonic, boolean needNumberToken, Position pos) {
        super(mnemonic,TokenType.Mnemonic, pos);
        
        this.needNumberToken = needNumberToken;
       // if(needNumberToken) {
        	//this.mNameMap = mnemonic.substring(0,mnemonic.indexOf('.'));
       // }
       // else {
        	this.mNameMap = mnemonic;
       // }
        
        
        opCode = -1;
        mapping = new TreeMap<String,Integer>();
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
        this.mName = mnemonic;
        opCode = findOpcode(); //Set opcode to opcode found using helper method
       
    }
	
    /**
     * Helper method which checks if the mnemonic is a valid operation
     * @param
     * @return Boolean value which indicates the validity of the instruction 
     */
    public boolean isValidOperation() {
        if (mapping.containsKey(mNameMap))
            return true;
        else
            return false;
    }

    /**
     * Helper method which finds the opcode related the the instruction, returns -1 if the instruction is invalid
     * @param
     * @return Integer representing the opcode
     */
    private int findOpcode() {
        int testOpcode = -1; //Default to -1
        if(isValidOperation()) //if the mapping contains the mnemonic, set testOpcode to that opcode, otherwise opcode is set to -1
            testOpcode = mapping.get(mNameMap);
        
        opCode = testOpcode;
        return opCode;
    }

    /**
     * Gets the integer opcode
     * @return integer opcode
     */
    public int getOpcode() {
        return opCode;
    }

    public void incrementOpcode (byte increment) {
        //System.out.println(opCode);
        //System.out.println(increment);
        opCode = opCode + increment;
        //System.out.println(opCode);
    }

    /**
     * Used when we cannot simply increment the opcode. Ex: enter.u5
     * @param newOpcode
     */
    public void setOpcode (int newOpcode) {
        this.opCode = newOpcode;
    }

    public boolean needsNumber() 
    {
        return needNumberToken;
    }

    /**
     * toString() returns hex representation of the opcode.
     */
    public String toString() {
        return mName + " " + Integer.toHexString(opCode).toUpperCase() + " row:" +getPosition().getRow()+" column:" +getPosition().getColumn();
    }
	

	/**
	 * Method used to set the name of the Mnemonic
	 * @param name
	 */
	public void setName(String name) {
		this.mName = name;
	}
	// added 

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return mName;
	}
//	public void setColLength(int col){
//	    super.setColumn(col - this.mName.length()-1);
//	    return;
//    }

	@Override
	public TokenType getCode() {
		// TODO Auto-generated method stub
		return TokenType.Mnemonic;
	}
	
}
