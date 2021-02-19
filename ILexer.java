/**
 * Interface for the Lexer class
 */
public interface ILexer {

    IToken getToken();
    IToken getNextToken();
    boolean hasNextToken();

}
