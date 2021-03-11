/**
 * Class representing an Instruction
 */
public class Instruction implements IInstruction {
    Mnemonic mnemonic;
    //Operand operand;

public Instruction(Mnemonic mnemonic) {
    this.mnemonic = mnemonic;
   // this.operand = null; //Implementation comes later
}

/* TODO: For Sprint 3
public Instruction(IMnemonic mnemonic,IOperand operand) {

}*/

public IMnemonic getMnemonic() {
    return mnemonic;
}

//public IOperand getOperand() {
//    return operand;
//}


    @Override
    public String toString() {
        return mnemonic.toString();
    }
}
