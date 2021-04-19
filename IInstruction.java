/**
 * Interface for the Instruction Class
 */
public interface IInstruction {

    IMnemonic getMnemonic();
    int getNumberInt();
    ILabel getLabelOperand();
    int getSize();
    boolean isInherent();
}