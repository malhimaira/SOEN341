
//Tests to check outputs of the class Option

public class TestOption {

    public static void main(String[] args) {
        String[] t1 = {"-b","fileDontExist.asm"};
        String[] t2 = {"-b","-banner"};
        String[] t3 = {"-help"};
        String[] t4 = {"-v","TestImmediate.asm"};
        String[] t5 = {"-l"};
        String[] t6 = {"-v","-b","-l","-h"};
        String[] t7 = {"-v","-b","-l","-h","TestImmediate.asm"};

        Option op = new Option();
        System.out.println("Test t1");
        op.userInput(t1);
        System.out.println("Test t2");
        op.userInput(t2);
        System.out.println("Test t3");
        op.userInput(t3);
        System.out.println("Test t4");
        op.userInput(t4);
        System.out.println("Test t5");
        op.userInput(t5);
        System.out.println("Test t6");
        op.userInput(t6);
        System.out.println("Test t7");
        op.userInput(t7);

    }
}