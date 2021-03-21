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

        //Immediate instructions (opcode given here is starting value)
       mapping.put("enter.u5",112);
       mapping.put("ldc.i3",144);
       mapping.put("addv.u3",152);
       mapping.put("ldv.u3",160);
       mapping.put("stv.u3",168);
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
