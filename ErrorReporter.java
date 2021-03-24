/*Error Reporter Class*/

import java.util.ArrayList;

public class ErrorReporter {

  private ArrayList<ErrorMsg> ErrMsg;
  private String fileName;

  public ErrorReporter() {
    System.out.println("Activate Error Reporter");
    ErrMsg = new ArrayList<ErrorMsg>();
    fileName = "File.asm";
  }

  public ErrorReporter(String fileName) {
    System.out.println("Activate Error Reporter");
    ErrMsg = new ArrayList<ErrorMsg>();
    this.fileName = fileName;
  }

  // Method to check the size of the ArrayList
  public boolean hasErrors() {
    if (ErrMsg.size() > 0) {
      return true;
    } else {
      return false;
    }
  }

  public void record(ErrorMsg msg) {
    ErrMsg.add(msg);
  }

  public void report() {
    for (int i = 0; i < ErrMsg.size(); i++) {
      System.out.println(fileName + ":line " + ErrMsg.get(i).getErrorPosition().getRow() + ", column "
          + ErrMsg.get(i).getErrorPosition().getColumn() + ": " + ErrMsg.get(i).getErrorMessage());
    }
  }

  public String toString() {
    String Error = "";
    for (int i = 0; i < ErrMsg.size(); i++) {
      Error = fileName + ":line " + ErrMsg.get(i).getErrorPosition().getRow() + ", column "
          + ErrMsg.get(i).getErrorPosition().getColumn() + ": " + ErrMsg.get(i).getErrorMessage();
    }
    return Error;
  }

  public void endErrorReporter() {
    System.out.println("A total of " + ErrMsg.size() + " errors were found within the run-time of the program.");
    System.out.println("ErrorReporter is deactivated. CrossAssembler will be now terminated.");
    // I am not sure if the error reporter should be the last thing in the program
    // running
    // and terminate it properly.
    System.exit(0);
  }

}