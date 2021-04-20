package FrontEnd;
/**
 * Token Class used to specify Token Type of each Object
 */
public class Token implements IToken{
	//private Position  pos;
	private TokenType code;
	private String    name;
	private Position position;

	
	Token(String name, TokenType code,Position pos) { //Position later
		this.name = name;
		this.position = new Position(pos.getRow(),pos.getColumn());
		
		if(code == TokenType.Mnemonic) {
			this.code = TokenType.Mnemonic;
		}
		else if(code == TokenType.Label) {
			this.code = TokenType.Label;
		}
		else if(code == TokenType.Directive) {
			this.code = TokenType.Directive;
		}
		else if(code == TokenType.LabelOperand) {
			this.code = TokenType.LabelOperand;
		}
		else if(code == TokenType.LabelOperand) {
			this.code = TokenType.LabelOperand;
		}
		else if(code == TokenType.EOL) {
			this.code = TokenType.EOL;
		}
		else if(code == TokenType.EOF) {
			this.code = TokenType.EOF;
		}
		else if(code == TokenType.Number) {
			this.code = TokenType.Number;
		}
		else if(code == TokenType.Comment) {
			this.code = TokenType.Comment;
		}
		//comment,EOL,EOF, etc.

	}
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public Position getPosition() {
		return position;
	}

	public  TokenType getCode()      {
		return code; 
	}
	public  String    toString()     {
		return "["+getName()+"="+code+"]"; 
	}
	
	public boolean getIsRelative () {
		
		return false;
	}


}
