/**
 * Interface for the Directive
 */
public interface IDirective extends IToken{
    void addStringOperand(IStringOperand stringOperand); //Add a string operand
    String getTrimmedString(); //get the string without the quotation marks
    IStringOperand getStringOperand(); // Get the StringOperand object
    int getSize();

}
