import java.util.ArrayList;

/**
 * Interface for the Parser Class
 */
public interface IParser {
    boolean parseTokens(ILexer lexer);
    ArrayList<ILineStatement> getIR();
    
}