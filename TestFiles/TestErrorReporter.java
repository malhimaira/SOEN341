package TestFiles;

import FrontEnd.*;
import BackEnd.*;
import main.*;
import Tables.*;

/*ErrorReporter test class*/
public class TestErrorReporter{
      public static void main(String args[]) {
            IErrorReporter errorReporter = new ErrorReporter();    
            errorReporter.record(new ErrorMsg("Error! This should be reported!", new Position(1,0)));  
            errorReporter.record(new ErrorMsg("Error! This should be also reported!", new Position(2,0)));  
            errorReporter.record(new ErrorMsg("Error! This should be also reported too!", new Position(3,0)));
            System.out.println("Test ErrorReporter");

            errorReporter.report();


    }//end of main

}//end of class
