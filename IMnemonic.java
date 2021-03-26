/**
 * Interface for the Mnemonic Class.
 */
public interface IMnemonic extends IToken{

    int getOpcode();
    boolean isValidOperation();
    String getName();
    void setName(String name);
    TokenType getCode();
}
