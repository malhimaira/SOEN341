/**
 * Token Class used to specify Token Type of each Object
 */
public class Token implements IToken{
	//private Position  pos;
	private TokenType code;
	private String    name;

	Token(String name, TokenType code) { //Position later
		this.name = name;
		if(code == TokenType.Mnemonic) {
			this.code = TokenType.Mnemonic;
		}
		else if(code == TokenType.Label) {
			this.code = TokenType.Label;
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
		
		//comment,EOL,EOF, etc.

	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public  TokenType getCode()      { 
		return code; 
	}
	public  String    toString()     {
		return "["+getName()+"="+code+"]"; 
	}


}
