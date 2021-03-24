public class Position implements IPosition{
    private int row;
    private int column;


    public Position(int row, int column) {
        this.row = row;
        this.column = column;

    }

    @Override
    public String toString() {
        return "Position{" +
                ", row=" + row +
                "column=" + column +
                '}';
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
