
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import jdk.jfr.StackTrace;


public class ListingGenerator implements IListingGenerator {

    private static ArrayList<ILineStatement> IR;
    private static StubIR stub;
    public String header = "Line Addr Code Label Mne Operand Comment";
    private String addr, label, mne, operand, comment;
    private Byte code;
    String fileName;
    PrintWriter pw;

    //Error with Address it won't print
    //Constructors
//    public ListingGenerator(){
//        arrayStubIR = null;
//        stub = new StubIR();
//        addr = label = mne = operand= comment = "";
//        code = 0;
//    }

    public ListingGenerator(ArrayList<ILineStatement> IR, String fileName){
       // setStub(arrayILineStat);
        this.IR = IR;

        for(ILineStatement temp : IR) {

            //setAddr(stub.getMnemonicOpcode(temp));

        for(LineStatement temp : arrayIR) {

            //setAddr(stub.getMnemonicOpcode(temp));
        	setAddr(arr);
            setCode(stub.getMnemonicOpcode(temp));
            setMne(stub.getMnemonicName(temp));
            label=operand=comment="";

        }

    }



  public void setAddr(byte byteAddr){

        addr = String.format("%04X", arrayStubIR);

    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public void setMne(String mne) {
        this.mne = mne;
    }
    public void setArrayIR(ArrayList<ILineStatement> arrayILineStat){
        for(int i = 0; i < arrayILineStat.size(); i++){
            arrayIR.add(i,arrayILineStat.get(i));
        }
    }

    @Override
    public String toString(LineStatement lineStat){

        String result = "";

        result = addr +"/t"+ code +"/t" + label + "/t"+ mne +"/t" + "/t"+operand + "/t" + comment;

        out.write(result);
        return result;
    }

    public void generateLst() {

    }

 }


//IR hold LineStatment
// LineStatment holds instrucction attribute
// calls mnemonic, addr, ...
