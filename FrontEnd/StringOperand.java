package FrontEnd;

public class StringOperand extends Token implements IStringOperand{
	String stringOperand;

	public StringOperand(String name, Position pos) {
		super(name, TokenType.StringOperand, pos);
		this.stringOperand = name;
		
		// TODO Auto-generated constructor stub
	}
	public TokenType getCode() {
		// TODO Auto-generated method stub
		return TokenType.StringOperand;
	}

	public String getStringOperand() {
		return stringOperand;
	}


	@Override
	public String toString() {
		return getName()+ " row:" +getPosition().getRow()+" column:" +getPosition().getColumn();
	}
}
