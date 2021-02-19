public class Instruction implements IInstruction {
    IMnemonic mnemonic;
    IOperand operand;

public Instruction(IMnemonic mnemonic) {
    this.mnemonic = mnemonic;
    this.operand = null; //Implementation comes later
}

/* For Sprint 3
public Instruction(IMnemonic mnemonic,IOperand operand) {

}*/

public IMnemonic getMnemonic() {
    return mnemonic;
}

public IOperand getOperand() {
    return operand;
}

}
