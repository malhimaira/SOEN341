import java.util.ArrayList;

public interface IParser {
    boolean parseTokens(ILexer lexer);
    ArrayList<ILineStatement> getIR();
    
}