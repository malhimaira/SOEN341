
public class StringOperand extends Token{
	String stringOperand;

	StringOperand(String name, Position pos) {
		super(name, TokenType.StringOperand, pos);
		this.stringOperand = name;
		
		// TODO Auto-generated constructor stub
	}
	public TokenType getCode() {
		// TODO Auto-generated method stub
		return TokenType.Number;
	}

	public String getStringOperand() {
		return stringOperand;
	}


	@Override
	public String toString() {
		return getName()+ " row:" +getPosition().getRow()+" column:" +getPosition().getColumn();
	}
}
