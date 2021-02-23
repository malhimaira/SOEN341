
import java.util.*;

public class StubIR extends Parser {

      //Added thing for test done for Team 1 part (Generation of Listing)
      ArrayList<ILineStatement> listingIR;
      ArrayList<StubIR> listStringIR;
      String addr;
      String code;
      String label;
      String mne;
      String operand;
      String comment;

      //default constructor
      public StubIR(){
            listingIR = getIR();
            listStringIR = null;
            addr = "";
            code = "";
            label = "";
            mne = "";
            operand = "";
            comment = "";
      }

      public void lineStatmentToString(ArrayList<ILineStatement> toStringIR){
            for(int i = 0; i < toStringIR.size(); i++){
                  StubIR tempStub = new StubIR();
                  ILineStatement tempILine = toStringIR.get(i);
                  tempStub.addr = tempILine.toString();//What info is given when it goes to toString?
                  listStringIR.add(i,tempStub);
            }
      }


}//end of StubIR Interface
