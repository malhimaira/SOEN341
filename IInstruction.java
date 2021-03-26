/**
 * Interface for the Instruction Class
 */
public interface IInstruction {

    IMnemonic getMnemonic();
    int getNumberInt();
    boolean isInherent();
}