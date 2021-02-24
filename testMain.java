import java.io.FileInputStream;

public class testMain {
    public static void main(String args[]) {
        try {
            FileInputStream reader = new FileInputStream("TestInherentMnemonics.asm");
            Lexer lex =  new Lexer(reader);
            
            Token t = lex.getNextToken();
            while(t != null) {
            	System.out.println(t);
            	t = lex.getNextToken();
            }
           
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
