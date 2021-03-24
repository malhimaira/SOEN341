public interface ILineStatement {
    IInstruction getInstruction();
    Comment getComment();
    Directive getDirective();
}
