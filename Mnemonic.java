/**
 * Class representing a Mnemonic Token, used to specify Mnemonic type
 */
import java.util.TreeMap;

public class Mnemonic extends Token implements IMnemonic {
	private String mName;
	private String mNameMap;
	private int opCode;
	private boolean needNumberToken;
	private boolean isRelative;
	
    private TreeMap<String,Integer> mapping; //Using a TreeMap for mapping as it is very efficient for searching.

    /**
	 * Constructor used to specify the type of the mnemonic given
	 */
	public Mnemonic(String mnemonic, boolean needNumberToken, Position pos, TreeMap<String,Integer> mapping) {
        super(mnemonic,TokenType.Mnemonic, pos);
        
        this.needNumberToken = needNumberToken;
       
        this.mNameMap = mnemonic;
       
        this.mapping = mapping;
        
        opCode = -1;
        this.mName = mnemonic;
        opCode = findOpcode(); //Set opcode to opcode found using helper method
       
    }
	
    /**
     * Helper method which checks if the mnemonic is a valid operation
     * @param
     * @return Boolean value which indicates the validity of the instruction 
     */
    public boolean isValidOperation() {
        if (mapping.containsKey(mNameMap)) {
        	
        	//RELATIVE INSTRUCTION BOOLEAN
        	if(mNameMap.equals("br.i8") || mNameMap.equals("brf.i8") || mNameMap.equals("ldc.i8")|| 
        	mNameMap.equals("ldv.u8")|| mNameMap.equals("stv.u8")|| mNameMap.equals("lda.i16") ){
        		isRelative = true;	
        	}
        	else {
        		isRelative = false;
        	}
        
            return true;
            
            }
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
	
	@Override
	//Method to get relative boolean
	public boolean getIsRelative () {
		return isRelative;
	}
	
}
