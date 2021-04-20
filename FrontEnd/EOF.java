package FrontEnd;
/**
 * Class representing a EOF Token
 */
public class EOF extends Token{
	public EOF(String name, Position pos) {
		super(name, TokenType.EOF, pos);
		// TODO Auto-generated constructor stub
	}
	public TokenType getCode() {
		// TODO Auto-generated method stub
		return TokenType.EOF;
	}
	public String toString() {
		return "EOF row: "+super.getPosition().getRow()+" column: "+super.getPosition().getColumn();
	}
}
