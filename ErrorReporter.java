/*Error Reporter Class*/

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorReporter{

<<<<<<< HEAD
  private String errorMsg;
  private Position errorPosition;
  private static int counter;
  private static ArrayList<ErrorReporter> parserError;
  private static ArrayList<ErrorReporter> scannerError;


  public ErrorReporter(){
    System.out.println("Activate Error Reporter");
    parserError = new ArrayList<>();
    scannerError = new ArrayList<>();
    errorMsg = "";
    errorPosition = new Position(0,0);
  }

  public ErrorReporter(Position position, String msg)
  {
    errorPosition = position;
    errorMsg = msg;
  }

  public void errorReporterScanner(Position position, String msg) {
    ErrorReporter temp = new ErrorReporter(position, msg);
    scannerError.add(temp);
    counter++;
    System.out.println(temp.toString());
  }

  public void errorReporterParser(Position position, String msg) {
    ErrorReporter temp = new ErrorReporter(position, msg);
    parserError.add(temp);
    counter++;
    System.out.println(temp.toString());
  }

  public String toString()
  {
    return "Error at "+ errorPosition + " : "+ errorMsg;
  }
  public void endErrorReporter(){
    Iterator it;
    System.out.println("A total of " + counter + " errors were found within the run-time of the program.");
    System.out.println("ErrorReporter is deactivated. CrossAssembler will be now terminated.");
    //I am not sure if the error reporter should be the last thing in the program running
    //and terminate it properly.
    System.exit(0);
  }
=======
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
>>>>>>> parent of 40dc9e9 (Try not to fix :))

}
