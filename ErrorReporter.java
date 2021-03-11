/*Error Reporter Class*/

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorReporter{

      private PrintWriter pw;

      public ErrorReporter(String fileName){
        System.out.println("ErrorReporter is activated.");
        try {
            pw = new PrintWriter(new File(fileName));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
      }

      public void callErrorReporter(Token brokenToken, String Position) {
        String nameOfToken = brokenToken.getName();
        pw.println("Error: The Token " + nameOfToken + " at position " + Position + " is broken. " );
    }



      public void endErrorReporter(){
        pw.close();
        System.out.println("ErrorReporter is deactivated.");
      }

}
