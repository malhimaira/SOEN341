package FrontEnd;

import main.*;
import BackEnd.*;
import Tables.*;
import TestFiles.*;
/**
 * Interface for the Parser Class
 */
public interface IParser {
    IIR parse(); //Parse tokens and return the IR, which is an ArrayList of LineStatements
    
}