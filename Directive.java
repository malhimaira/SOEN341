public class Directive extends Token implements IDirective{
    IStringOperand stringOperand;
    String trimmedString; //String without quotation marks.

    public Directive(Position pos) {
        super(".cstring",TokenType.Directive, pos);
        stringOperand = null;
        trimmedString = null;

    }

    //Used for test files when we know operand in advance.
    public Directive(IStringOperand stringOperand, Position pos) {
        super(".cstring",TokenType.Directive, pos);
        addStringOperand(stringOperand);

    }

    /**
     * Add a String operand to the directive
     */
    public void addStringOperand(IStringOperand stringOperand) {
        this.stringOperand = stringOperand;

        String stringOperandValue = stringOperand.getName();
        //Assuming parser caught bad strings. Cutting out the quotation marks, which are the first and last index.
        this.trimmedString = stringOperandValue.substring(1,stringOperandValue.length()-1);
    }

    public String getTrimmedString() {
        return trimmedString;
    }

    /**
     * Get the ASCII representation of the first 4 characters (after that prof said don't bother showing them)
     * @return
     */
    public String toString() {
        return ".cString row: "+super.getPosition().getRow()+" column: "+super.getPosition().getColumn();
    }
}