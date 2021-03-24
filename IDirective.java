/**
 * Interface for the Directive
 */
public interface IDirective extends IToken{
    void addStringOperand(StringOperand stringOperand); //Add a string operand
    String getTrimmedString(); //get the string without the quotation marks

}
