/**
 * Class representing a Number Token
 */
public class Number extends Token{
	Number(String name, Position pos) {
		super(name, TokenType.Number, pos);
		// TODO Auto-generated constructor stub
	}
	public TokenType getCode() {
		// TODO Auto-generated method stub
		return TokenType.Number;
	}

	@Override
	public String toString() {
		return getName()+ " row:" +getPosition().getRow()+" column:" +getPosition().getColumn();
	}
}
