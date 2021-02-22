/**
 * Class representing a Line Statement
 */
public class LineStatement implements ILineStatement{
    IInstruction instruction;
    
    public LineStatement(IInstruction instruction) {
        this.instruction = instruction;
    }

    public IInstruction getInstruction() {
        return instruction;
    }
}
