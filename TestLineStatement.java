/**
 * TestLineStatement
 */
public class TestLineStatement {

    public static void main(String[] args) {
        LineStatement lineStatement = new LineStatement(new Instruction(new Mnemonic("halt")));
        System.out.println("Test LineStatement"); //Test name

        System.out.println("LineStatement: halt 0");
        System.out.println("LineStatement: " + lineStatement);

    }
    
}