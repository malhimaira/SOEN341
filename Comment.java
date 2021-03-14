/**
 * Class representing a Comment Token
 */
public class Comment extends Token{
	Comment(String name, int column, int row) {
		super(name, TokenType.Comment, column, row);
		// TODO Auto-generated constructor stub
	}
	public TokenType getCode() {
		// TODO Auto-generated method stub
		return TokenType.Comment;
	}
}
