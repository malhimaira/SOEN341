import java.util.ArrayList;

public interface IStubIR{

    ArrayList<LineStatement> getListingGenIR();
    ArrayList<ILineStatement> getGivenIR();
    void setGivenIR(ArrayList<ILineStatement> givenIR);
    void setListingGenIR(ArrayList<LineStatement> listingGenIR);

    LineStatement getLineStatement();
    //Instruction getInstruction();
    String getMnemonicName(LineStatement lineStat);
    Byte getMnemonicOpcode(LineStatement lineStat);
    ArrayList<LineStatement> getArrayLineStat();

}
