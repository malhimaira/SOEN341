
import java.io.PrintStream;
import java.util.ArrayList;


public class ListingGenerator extends StubIR {

    public static void main(String[] args) throws Exception {

        //Creating the lst file and writing any information passed through System.out.println
        PrintStream outputList = new PrintStream("testList.lst");
        System.setOut(outputList);

        // Question 1, b)
        String header = "Line\tAddr\tCode\t\tLabel\t\tMne\tOperand\t\tComment";
        System.out.println(header);

        String addr = "";
        String code = "00Test";
        String label = "labelTest";
        String mne = "mneTest";
        String operand = "opTest";
        String comment = "commentTest";



        String[] line = new String[26];

        for (int i = 0; i < line.length; i++) {

            if (i < 16) {
                addr = "000" + Integer.toHexString(i).toUpperCase();
            } else {
                addr = "00" + Integer.toHexString(i).toUpperCase();
            }

            line[i] = (i + 1) + "\t" + addr + "\t" + code + "\t" + label + "\t" + mne + "\t" + operand + "\t" + comment;

            System.out.println(line[i]);
        }

        //System.exit(0); - For testing purposes
        // System.out.println(Integer.toHexString(10));

    }


}


//IR hold LineStatment
// LineStatment holds instrucction attribute
// calls mnemonic, addr, ...
