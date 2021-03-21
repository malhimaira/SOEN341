/**
 * TestLineStatement
 */
public class TestLineStatement {

    public static void main(String[] args) {
        LineStatement lineStatement = new LineStatement(new Instruction(new Mnemonic("halt", false, new Position(0,0))));
        System.out.println("Test LineStatement"); //Test name

        System.out.println("LineStatement: halt 0");
        System.out.println("LineStatement: " + lineStatement.getInstruction().getMnemonic().getName() + " " + lineStatement.getInstruction().getMnemonic().getOpcode());

    }
    
}