/*ErrorReporter test class*/
import java.io.File;

public class TestErrorReporter{

  	public static void main(String args[]) {

      ErrorReporter er = new ErrorReporter();
      Position p1 = new Position(1,3);
      Position p2 = new Position(4,5);
      Position p3 = new Position(7,2);
      Position p4 = new Position(2,3);
      String erMsgScanner1 = "Error Message from Scanner";
      String erMsgScanner2 = "Another Error Message from Scanner";
      String erMsgParser1 = "Error Message from Parser";
      String erMsgParser2 = "Another Error Message from Parser";


      er.errorReporterScanner(p1, erMsgScanner1);
      er.errorReporterScanner(p2, erMsgScanner2);
      er.errorReporterParser(p3, erMsgParser1);
      er.errorReporterParser(p4, erMsgParser2);
      er.endErrorReporter();
      
    }//end of main

}//end of class
