import java.util.ArrayList;

public interface IListingGenerator {

    void setStub(ArrayList<ILineStatement> arrayILineStat);
    StubIR getStub();
    String toString(LineStatement lineStat);

}