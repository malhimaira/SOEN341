package FrontEnd;
/*Error Reporter Class*/

import java.util.ArrayList;

public class ErrorReporter implements IErrorReporter{

  private ArrayList<ErrorMsg> errMsg;
  private String fileName;

  public ErrorReporter() {
    //System.out.println("Activate Error Reporter");
    errMsg = new ArrayList<ErrorMsg>();
    fileName = "File.asm";
  }

  public ErrorReporter(String fileName) {
    this();
    this.fileName = fileName;
  }

  // Method to check the size of the ArrayList
  public boolean hasErrors() {
    if (errMsg.size() > 0) {
      return true;
    } else {
      return false;
    }
  }

  public void record(ErrorMsg msg) {
    errMsg.add(msg);
  }

  public void report() {
    for (int i = 0; i < errMsg.size(); i++) {
      System.out.println(fileName + ":line " + errMsg.get(i).getErrorPosition().getRow() + ", column "
          + errMsg.get(i).getErrorPosition().getColumn() + ": " + errMsg.get(i).getErrorMessage());
    }

    //Print number of errors
    System.out.println("\n\n\n" + errMsg.size() + " errors found.");
  }

  public String toString() {
    String Error = "";
    for (int i = 0; i < errMsg.size(); i++) {
      Error += fileName + ":line " + errMsg.get(i).getErrorPosition().getRow() + ", column "
          + errMsg.get(i).getErrorPosition().getColumn() + ": " + errMsg.get(i).getErrorMessage() + "\n";
    }
    return Error;
  }

}