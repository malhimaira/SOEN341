
import java.util.ArrayList;
class StubMnemonic{
    String name;
    byte opcode;
    StubMnemonic(String n, int code){
      name = n;
      opcode = (byte)code;
    }

}
class StubInstruction{
    StubMnemonic mnemonic;
    StubInstruction(StubMnemonic mne){
      mnemonic = mne;
    }
}

class StubLineStatement{
    StubInstruction instruction;
    StubLineStatement(StubInstruction instr){
      instruction = instr;
    }

}

public class StubIR implements IStubIR{

    public static void main(String[] args) {
    ArrayList<StubLineStatement> StubIR = new ArrayList<>(){};

    StubMnemonic[] mneForIR = {new StubMnemonic("halt",0),new StubMnemonic("pop",1), new StubMnemonic("dup",2),new StubMnemonic("exit",3),
            new StubMnemonic("ret",4),new StubMnemonic("not",5),new StubMnemonic("and",6),new StubMnemonic("or",7),
            new StubMnemonic("xor",8),new StubMnemonic("neg",9),new StubMnemonic("inc",10),new StubMnemonic("dec",11),
            new StubMnemonic("add",12),new StubMnemonic("sub",13),new StubMnemonic("mul",14),new StubMnemonic("div",15)};


    for(int i = 0; i < mneForIR.length; i++){
        StubLineStatement temp = new StubLineStatement(new StubInstruction(mneForIR[i]));
        StubIR.add(i, temp);
    }

    StubListingGenerator slg = new StubListingGenerator(StubIR,"StubSolutionFile");
}
}//end of StubIR Interface
