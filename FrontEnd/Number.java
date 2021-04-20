package FrontEnd;
/**
 * Class representing a Number Token
 */
public class Number extends Token implements INumber{
 int numberInt;

	public Number(String name, Position pos) {
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
