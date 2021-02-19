public class LineStatement {
    IInstruction instruction;
    
    public LineStatement(IInstruction instruction) {
        this.instruction = instruction;
    }

    public IInstruction getInstruction() {
        return instruction;
    }
}
