import java.io.FileInputStream;

public class testMain {
    public static void main(String args[]) {
        try {
            FileInputStream reader = new FileInputStream("C:\\Users\\chanj\\Downloads\\TestInherentMnemonics.asm");
            Lexer lex =  new Lexer(reader);
            System.out.println(lex.getNextToken());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
