package main;

import FrontEnd.*;
import BackEnd.*;
import Tables.*;
import TestFiles.*;

import java.io.File;
//Interface of Option class
public interface IOption {

    boolean getVerboseStatus();
    boolean getListingStatus();
    File getAsmFile();

}