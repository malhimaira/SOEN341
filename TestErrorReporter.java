/*ErrorReporter test class*/
public class TestErrorReporter{
      public static void main(String args[]) {
            IErrorReporter errorReporter = new ErrorReporter();    
            errorReporter.record(new ErrorMsg("Error! This should be reported!", new Position(1,0)));  
            errorReporter.record(new ErrorMsg("Error! This should be also reported!", new Position(2,0)));  
            errorReporter.record(new ErrorMsg("Error! This should be also reported too!", new Position(3,0)));
            System.out.println("Test ErrorReporter");
            // System.out.println("File.asm:line 1, column 0: Error! This should be reported!");
            // System.out.println("File.asm:line 2, column 0: Error! This should be also reported!");
            // System.out.println("File.asm:line 3, column 0: Error! This should be also reported too!");
            // System.out.println();
            // System.out.println();
            // System.out.println();
            // System.out.println("3 errors found.");

            errorReporter.report();


    }//end of main

}//end of class
