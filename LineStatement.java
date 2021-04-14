/**
 * Class representing a Line Statement
 */
public class LineStatement implements ILineStatement{
    IInstruction instruction;
    IComment comment;
    IDirective directive;
    ILabel label;
    
    public LineStatement(IInstruction instruction) {
        this.instruction = instruction;
        this.comment = null;
        this.directive = null;
    }

    public LineStatement(IInstruction instruction, IComment comment) {
        this(instruction);
        this.comment = comment;
    }

    public LineStatement(IDirective directive) {
        this.instruction = null;
        this.comment = null;
        this.directive = directive;
    }

    public LineStatement(IDirective directive, IComment comment) {
        this(directive);
        this.comment = comment;
    }

    public LineStatement(ILabel label, IInstruction instruction, IComment comment) {
        this(instruction,comment);
        this.label = label;
    }

    public LineStatement(ILabel label, IDirective directive, IComment comment) {
        this(directive,comment);
        this.label = label;
    }

    public IInstruction getInstruction() {
        return instruction;
    }

    public IComment getComment() {
        return comment;
    }

    public IDirective getDirective() {
        return directive;
    }

    @Override
    public String toString() {
        String output ="";
        if (label != null)
            output += label.getName() + " ";
        if (comment != null) {
            if (instruction != null)
                return output + instruction.toString() + " " + comment.getName();
            else if (directive != null) 
                return output + directive.toString() + " " + comment.getName();
            else if (instruction == null && directive == null) 
                return output + comment.getName();

        } else if (comment == null ) {
            if (instruction != null)
                return output + instruction.toString();
            else if (directive != null) 
                return output + directive.toString();
            else if (instruction == null && directive == null) 
                return output + "";
        }
        
        return "";
    }
}
   
