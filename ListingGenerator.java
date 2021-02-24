
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


        	addr = String.format("%04X", arrayStubIR);
//            if ( wrongAddr < 16) {
//                addr = "000" + Integer.toHexString(wrongAddr).toUpperCase();
//            } else {
//                addr = "00" + Integer.toHexString(wrongAddr).toUpperCase();
//            }

            setCode(stub.getMnemonicOpcode(temp));
            setMne(stub.getMnemonicName(temp));
            label=operand=comment="";
            

        }

    }

    //setter
    @Override
    public void setStub(ArrayList<ILineStatement> arrayILineStat) {
        StubIR tempStub = new StubIR(arrayILineStat);
        stub = tempStub;
    }

    @Override
    public StubIR getStub(){
        return stub;
    }

//    public void setAddr(byte byteAddr){
//        byte intAddr = byteAddr;
//
//        addr = String.format("%04X", arrayStubIR);
//        if ( intAddr < 16) {
//            addr = "000" + Integer.toHexString(intAddr).toUpperCase();
//        } else {
//            addr = "00" + Integer.toHexString(intAddr).toUpperCase();
//        }
//
//    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public void setMne(String mne) {
        this.mne = mne;
    }

    @Override
    public String toString(LineStatement lineStat){

        String result = "";

        result = "%-10s" + addr + "%-10s" + code + "%-10s%-10s" + label + "%-10s%-10s" + mne + "%-10s" + operand + "%-10s" + comment;

        return result;
    }
    
    public void generateLst() {
    	
    }

//     public static void main(String[] args) throws Exception {
//
//         //Creating the lst file and writing any information passed through System.out.println
//
//
//
//
//         // Question 1, b)
//         //String header = "Line\tAddr\tCode\t\tLabel\t\tMne\tOperand\t\tComment";
//         System.out.println(header);
//
//         String addr = "";
//         String code = "00Test";
//         String label = "labelTest";
//         String mne = "mneTest";
//         String operand = "opTest";
//         String comment = "commentTest";
//
//
//
//        // String[] line = new String[26];
//
//        /* for (int i = 0; i < line.length; i++) {
//
//             if (i < 16) {
//                 addr = "000" + Integer.toHexString(i).toUpperCase();
//             } else {
//                 addr = "00" + Integer.toHexString(i).toUpperCase();
//             }
//
//             line[i] = (i + 1) + "\t" + addr + "\t" + code + "\t" + label + "\t" + mne + "\t" + operand + "\t" + comment;
//
//             System.out.println(line[i]);
//         }*/
//
//
//
//         //System.exit(0); - For testing purposes
//         // System.out.println(Integer.toHexString(10));
//
//     }
//
//
 }


//IR hold LineStatment
// LineStatment holds instrucction attribute
// calls mnemonic, addr, ...
