public class Instruction implements IInstruction {
    Mnemonic mnemonic;
    //Operand operand;

public Instruction(Mnemonic mnemonic) {
    this.mnemonic = mnemonic;
   // this.operand = null; //Implementation comes later
}

/* For Sprint 3
public Instruction(IMnemonic mnemonic,IOperand operand) {

}*/

public Mnemonic getMnemonic() {
    return mnemonic;
}

//public IOperand getOperand() {
//    return operand;
//}

}