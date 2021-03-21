/**
 * Class representing a Comment Token
 */
public class Comment extends Token{
	Comment(String name, Position pos) {
		super(name, TokenType.Comment, pos);
		// TODO Auto-generated constructor stub
	}
	public TokenType getCode() {
		// TODO Auto-generated method stub
		return TokenType.Comment;
	}

	@Override
	public String toString() {
		return "Comment = "+super.getName()+" row: "+super.getPosition().getRow()+" column: "+super.getPosition().getColumn();
	}
}
