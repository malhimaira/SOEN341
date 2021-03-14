/**
 * Token Class used to specify Token Type of each Object
 */
public class Token implements IToken{
	//private Position  pos;
	private TokenType code;
	private String    name;
	private int column;
	private int row;

	
	Token(String name, TokenType code, int column, int row) { //Position later
		this.name = name;
		this.column= column;
		this.row = row;
		
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

	public int getColumn() {
		return this.column;
	}

	public int getRow() {
		return this.row;
	}

	public  TokenType getCode()      {
		return code; 
	}
	public  String    toString()     {
		return "["+getName()+"="+code+"]"; 
	}


	protected void setColumn(int column) {
		this.column = column;
	}
}
