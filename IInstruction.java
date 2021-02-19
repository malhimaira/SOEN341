/**
 * Interface for the Instruction Class
 */
public interface IInstruction {

    IMnemonic getMnemonic();
    IOperand getOperand();
}