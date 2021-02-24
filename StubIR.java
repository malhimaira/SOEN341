
import java.util.ArrayList;

public class StubIR implements IStubIR{

      //Added thing for test done for Team 1 part (Generation of Listing)
      private static ArrayList<LineStatement> listingGenIR;
      private static ArrayList<ILineStatement> givenIR;
      private LineStatement lineStat;


      //default constructor
      public StubIR(){
            listingGenIR = null;
            givenIR = null;
            lineStat = null;
      }

      public StubIR(ArrayList<ILineStatement> arrayILineStat){
            setGivenIR(arrayILineStat);
            setListingGenIR(getArrayLineStat());
      }

      //Getter
      @Override
      public ArrayList<LineStatement> getListingGenIR() {
            return listingGenIR;
      }


      @Override
      public ArrayList<ILineStatement> getGivenIR() {
            return givenIR;
      }


      //Setter
      @Override
      public void setGivenIR(ArrayList<ILineStatement> givenIR) {
            StubIR.givenIR = givenIR;
      }
      @Override
      public void setListingGenIR(ArrayList<LineStatement> listingGenIR) {
            StubIR.listingGenIR = listingGenIR;
      }


      @Override
      public LineStatement getLineStatement() {
            return lineStat;
      }

      @Override
      public String getMnemonicName(LineStatement lineStat) {
            return lineStat.getInstruction().getMnemonic().getName();
      }

      @Override
      public Byte getMnemonicOpcode(LineStatement lineStat) {
            return lineStat.getInstruction().getMnemonic().getOpcode();
      }

      public ArrayList<LineStatement> getArrayLineStat(){
           ArrayList<LineStatement> tempArrayLineStat = new ArrayList<>();
            for(int i = 0; i < givenIR.size(); i++){
                  LineStatement tempLineStat = (LineStatement) givenIR.get(i);
                  tempArrayLineStat.add(i,tempLineStat);
            }
            return tempArrayLineStat;
      }

}//end of StubIR Interface
