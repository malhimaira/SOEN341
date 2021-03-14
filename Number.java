/**
 * Class representing a Number Token
 */
public class Number extends Token{
	Number(String name, int column, int row) {
		super(name, TokenType.Number, column, row);
		// TODO Auto-generated constructor stub
	}
	public TokenType getCode() {
		// TODO Auto-generated method stub
		return TokenType.Number;
	}

	@Override
	public String toString() {
		return getName()+" " + getColumn() + "" + getRow();
	}
}
