package FrontEnd;
public interface ILineStatement {
    IInstruction getInstruction();
    IComment getComment();
    int getSize();
    ILabel getLabel();
    IDirective getDirective();
}
