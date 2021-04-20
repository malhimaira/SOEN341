package FrontEnd;
/**
 * Class representing a Error Message from a Error Token
 */
public class ErrorMsg {
	

	private String errorMessage;
	private Position errorPosition;
	
	public ErrorMsg(String errorMessage, Position errorPosition) {
		this.errorPosition = errorPosition;
		this.errorMessage = errorMessage;
		
	}

	
	public Position getErrorPosition() {
		return errorPosition;
	}

	public String getErrorMessage() {
		return errorMessage;
	}	
	
}
