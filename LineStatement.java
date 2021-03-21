/**
 * Class representing a Line Statement
 */
public class LineStatement implements ILineStatement{
    IInstruction instruction;
    Comment comment;
    
    public LineStatement(IInstruction instruction) {
        this.instruction = instruction;
        this.comment = null;
    }

    public LineStatement(IInstruction instruction, Comment comment) {
        this.instruction = instruction;
        this.comment = comment;
    }

    public IInstruction getInstruction() {
        return instruction;
    }

    public Comment getComment() {
        return comment;
    }

    @Override
    public String toString() {
        if (comment != null && instruction != null)
            return instruction.toString() + " " + comment.getName();
        else if (comment == null && instruction != null) 
            return instruction.toString();
        else if (comment != null && instruction == null)
            return comment.getName();
        else
            return "";
    }
}
