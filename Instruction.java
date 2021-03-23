/**
 * Class representing an Instruction
 */
public class Instruction implements IInstruction {
    Mnemonic mnemonic;
    Number number;
    boolean errorOccured;
    String errorString;
    int maxNumber; //Maximum number operand can have
    int minNumber; //Minimun number operand can have

public Instruction(Mnemonic mnemonic) {
    this.mnemonic = mnemonic;
    errorOccured = false; //Used to let parser know if something went wrong
    errorString = ""; //Used to give String describing error to the parser.
    maxNumber = -1;
    minNumber = -1;
}


public Instruction(Mnemonic mnemonic,Number number) {
    this(mnemonic);
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
            errorOccured = true;
            errorString = "Invalid mnemonic";
    }


}
/**
 * Checks if an error occured while processing the instruction
 * @return errorOccured
 */
public boolean errorOccured() {
    return errorOccured;
}
/**
 * Returns the String describing the error that occured.
 * @return errorString
 */
public String errorString() {
    return errorString;
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
    // if (!isSigned && number < 0) {
    //     errorOccured = true;
    //     numberIsInBounds(isSigned, bitSize, number); //Using this to find the max and min number size to put in our string
    //     errorString = "The immediate instruction " + "'" + mnemonic.getName() + "' must have a " + bitSize + "-bit unsigned operand ranging from " ;

    // }
    
    if (numberIsInBounds(isSigned, bitSize, number)) { //Checks if the number is in bounds and sets the integers maxNumber and minNumber to the bounds.
        String binaryNumberFullSized = String.format("%32s", Integer.toBinaryString(number)).replace(' ', '0'); //Full sized, we need to trim this to the proper number of bits
        String binaryNumber = binaryNumberFullSized.substring(binaryNumberFullSized.length()-bitSize); //Gives us a properly trimmed binary representation.

        opcodeInc = Integer.parseInt(binaryNumber,2); //Convert binary number to an integer again, this takes care of 2's complement if the number is negative.
    } else { 
        errorOccured = true;
        String sign = (isSigned) ? "signed": "unsigned";
        errorString = "The immediate instruction " + "'" + mnemonic.getName() + "' must have a " + bitSize + "-bit " + sign + " operand ranging from " + minNumber + " to " + maxNumber;
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
    this.maxNumber = maxNumber; //Set our max and minimum numbers to the ones found in this function.
    this.minNumber = minNumber;
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
