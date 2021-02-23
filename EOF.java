/**
 * Class representing a EOF Token
 */
public class EOF extends Token{
	EOF(String name) {
		super(name, TokenType.EOF);
		// TODO Auto-generated constructor stub
	}
	public TokenType getCode() {
		// TODO Auto-generated method stub
		return TokenType.EOF;
	}
}
