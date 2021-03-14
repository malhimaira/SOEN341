/**
 * Class representing a EOF Token
 */
public class EOF extends Token{
	EOF(String name, int column, int row) {
		super(name, TokenType.EOF, column, row);
		// TODO Auto-generated constructor stub
	}
	public TokenType getCode() {
		// TODO Auto-generated method stub
		return TokenType.EOF;
	}
}
