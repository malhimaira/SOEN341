import java.util.ArrayList;

public class IR implements IIR{
    private ArrayList<ILineStatement> IRArrayList;

    public IR() {
        IRArrayList = new ArrayList<ILineStatement>();
    }

    public ILineStatement getLineStatement(int index) {
        return IRArrayList.get(index);
    }

    public void add(ILineStatement lineStatement) {
        IRArrayList.add(lineStatement);
    }

    public int getSize() {
        return IRArrayList.size();
    }

    public String toString() { //Prints out IR using the ArrayList print format.
        return IRArrayList.toString();
    }

}
