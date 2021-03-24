/**
 * Class representing a Line Statement
 */
public class LineStatement implements ILineStatement{
    IInstruction instruction;
    Comment comment;
    Directive directive;
    
    public LineStatement(IInstruction instruction) {
        this.instruction = instruction;
        this.comment = null;
        this.directive = null;
    }

    public LineStatement(IInstruction instruction, Comment comment) {
        this(instruction);
        this.comment = comment;
    }

    public LineStatement(Directive directive) {
        this.instruction = null;
        this.comment = null;
        this.directive = directive;
    }

    public LineStatement(Directive directive, Comment comment) {
        this(directive);
        this.comment = comment;
    }

    public IInstruction getInstruction() {
        return instruction;
    }

    public Comment getComment() {
        return comment;
    }

    public Directive getDirective() {
        return directive;
    }

    @Override
    public String toString() {
        if (comment != null) {
            if (instruction != null)
                return instruction.toString() + " " + comment.getName();
            else if (directive != null) 
                return directive.toString() + " " + comment.getName();
            else if (instruction == null && directive == null) 
                return comment.getName();

        } else if (comment == null ) {
            if (instruction != null)
                return instruction.toString();
            else if (directive != null) 
                return directive.toString();
            else if (instruction == null && directive == null) 
                return "";
        }
        
        return "";
    }
}
   
