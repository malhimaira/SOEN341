/**
 * Class representing a EOL Token
 */
public class EOL extends Token {

	EOL(String name, int column, int row) {
		super(name, TokenType.EOL, column, row);
		// TODO Auto-generated constructor stub
	}
	public TokenType getCode() {
		// TODO Auto-generated method stub
		return TokenType.EOL;
	}
}
