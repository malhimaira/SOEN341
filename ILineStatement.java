public interface ILineStatement {
    IInstruction getInstruction();
    IComment getComment();
    IDirective getDirective();
}
