package Tables;

import FrontEnd.*;
import BackEnd.*;
import main.*;
import TestFiles.*;

public interface IIR {
    int getSize(); //return size of IR
    ILineStatement getLineStatement(int index); //Get LineStatement based on size
    void add(ILineStatement lineStatement); //add a LineStatement to the IR
}
