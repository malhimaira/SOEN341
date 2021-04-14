import java.util.HashMap;

/**
 * Interface for the Lexer Class
 */
public interface ILexer {
	Token getNextToken();
	HashMap<String, Token> getSymbolTable();
	HashMap<String, Label> getLabelTable();

}
