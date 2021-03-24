public class Directive extends Token{

    public Directive(Position pos) {
        super(".cstring",TokenType.Directive, pos);

    }

    public String toString() {
        return ".cString row: "+super.getPosition().getRow()+" column: "+super.getPosition().getColumn();
    }
}