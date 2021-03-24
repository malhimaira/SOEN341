/**
 * Class representing a Number Token, DIRECTIVE
 */
public class Number extends Token{
 int numberInt;

	Number(String name, Position pos) {
		super(name, TokenType.Number, pos);
		try {
			numberInt = Integer.parseInt(name); //Convert number string to integer
		} catch (Exception e) {
			//TODO: handle exception
		}
		
		// TODO Auto-generated constructor stub
	}
	public TokenType getCode() {
		// TODO Auto-generated method stub
		return TokenType.Number;
	}

	public int getNumberInt() {
		return numberInt;
	}


	@Override
	public String toString() {
		return getName()+ " row:" +getPosition().getRow()+" column:" +getPosition().getColumn();
	}
}
