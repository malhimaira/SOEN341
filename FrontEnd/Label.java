package FrontEnd;

public class Label extends Token implements ILabel{

	public Label (String name, Position pos) {
		super(name, TokenType.Label, pos);
	}
	
	@Override
	public String toString() {
		return getName()+ " row:" +getPosition().getRow()+" column:" +getPosition().getColumn();
	}
}
