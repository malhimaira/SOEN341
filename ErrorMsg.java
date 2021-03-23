/**
 * Class representing a Error Message from a Error Token
 */
public class ErrorMsg {
	
	private Token errorToken;
	private String errorMessage;
	private Position errorPosition;
	
	public ErrorMsg(Token errorToken, String errorMessage) {
		this.errorPosition = errorToken.getPosition();
		this.errorToken = errorToken;
		this.errorMessage = errorMessage;
		
	}

	public Token getErrorToken() {
		return errorToken;
	}
	
	public Position getErrorPosition() {
		return errorPosition;
	}

	public String getErrorMessage() {
		return errorMessage;
	}	
	
}
