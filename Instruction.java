/**
 * Class representing an Instruction
 */
public class Instruction implements IInstruction {
    Mnemonic mnemonic;
    Number number;
    //Operand operand;

public Instruction(Mnemonic mnemonic) {
    this.mnemonic = mnemonic;
   // this.operand = null; //Implementation comes later
}


public Instruction(Mnemonic mnemonic,Number number) {
    this.mnemonic = mnemonic;
    this.number = number;
    incrementOpcode();
}

public IMnemonic getMnemonic() {
    return mnemonic;
}

public boolean isInherent() {
 return !(mnemonic.needsNumber()); //If it is inherent, it does not need a number
}
public int getNumberInt() {
    return number.getNumberInt();
}

private void incrementOpcode() {
    String mnemonicName = mnemonic.getName();
    String instructionFormat = mnemonicName.substring(mnemonicName.indexOf(".")+1); //u5, i3, etc...

    switch(instructionFormat) {
        case "u3":
            mnemonic.incrementOpcode(returnIncrementValue(false,3));
        break;
        case "u5":
            mnemonic.incrementOpcode(returnIncrementValue(false,5));
        break;
        case "i3":
            mnemonic.incrementOpcode(returnIncrementValue(true,3));
        break;
        default: //Matches none of the ones we want!
        //TODO Report to error reporter!!!
    }


}
/**
 * Returns the value to increment the opcode of the instruction by
 * @param isSigned If the instruction requires a signed integer
 * @param bitSize 
 * @return
 */
private byte returnIncrementValue(boolean isSigned, int bitSize) {
   int number = this.number.getNumberInt();
   int opcodeInc = 0;
    //If the number is signed when we expect it to be unsigned
    if (!isSigned && number < 0) {
        //TODO Error reporter!!!
    }
    
    if (numberIsInBounds(isSigned, bitSize, number)) {
        String binaryNumberFullSized = String.format("%16s", Integer.toBinaryString(number)).replace(' ', '0'); //Full sized, we need to trim this to the proper number of bits
        String binaryNumber = binaryNumberFullSized.substring(binaryNumberFullSized.length()-bitSize); //Gives us a properly trimmed binary representation.

        opcodeInc = Integer.parseInt(binaryNumber,2); //Convert binary number to an integer again, this takes care of 2's complement if the number is negative.
    } else { 
        //TODO error reporter!!!
    }
    //System.out.println("DEBUG: " + (byte) opcodeInc);
    return (byte) opcodeInc; //Amount opcode should be incremented by.
}
/**
 * Check if a number is in bounds based on the number of bits assigned to the instruction in the mnemonic and if it is signed
 * @param bitSize
 * @param isSigned
 * @return
 */
private boolean numberIsInBounds(boolean isSigned, int bitSize, int number) {
    int maxNumber;
    int minNumber;

    if (isSigned) {
        String binaryNumber = "0"; //Should start with 0 since it is the max positive number
        for (int i = 0; i < bitSize-1; i++) //We only have bitSize - 1 left to work with after the 0!
            binaryNumber = binaryNumber + "1";

        maxNumber = Integer.parseInt(binaryNumber,2);
        
        //Determining minimum.
        binaryNumber = "";
        for (int i = 0; i < bitSize; i++) //For negative, most significant bit is 1. This is going to give us a magnitude.
            binaryNumber = binaryNumber + "1";
        
        minNumber = maxNumber - Integer.parseInt(binaryNumber); //Parsing the binary number gives us the maximum as an unsigned for bitSize, subtracting from maxNumber gives minNumber.
    } else { //Unsigned now
        minNumber = 0;
        String binaryNumber = ""; 
        for (int i = 0; i < bitSize; i++) //Gives us a string filled with 1's with the number equaling the number of bits
            binaryNumber = binaryNumber + "1";

        maxNumber = Integer.parseInt(binaryNumber,2);
    }

    //If number is in bounds, return true, else return false.
    if (number <= maxNumber && number >= minNumber) {
        return true;
    } else {
        return false;
    }

}


    @Override
    public String toString() {
        return mnemonic.toString();
    }
}
