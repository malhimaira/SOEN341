package FrontEnd;
/**
 * Class representing a EOL Token
 */
public class EOL extends Token {

	public EOL(String name, Position pos) {
		super(name, TokenType.EOL, pos);
		// TODO Auto-generated constructor stub
	}
	public TokenType getCode() {
		// TODO Auto-generated method stub
		return TokenType.EOL;
	}
	public String toString() {
		return "EOL row: "+super.getPosition().getRow()+" column: "+super.getPosition().getColumn();
	}
}
