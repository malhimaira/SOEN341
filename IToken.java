/**
 * Interface for the Token Class
 */
public interface IToken {
    //Tells us the type of the token via an enum
    String getName();
    TokenType getCode();
}
