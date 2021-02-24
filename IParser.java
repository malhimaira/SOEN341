import java.util.ArrayList;

/**
 * Interface for the Parser Class
 */
public interface IParser {
    ArrayList<ILineStatement> parse(); //Parse tokens and return the IR, which is an ArrayList of LineStatements
    
}