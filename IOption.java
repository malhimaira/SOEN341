import java.io.File;
//Interface of Option class
public interface IOption {

    boolean getVerboseStatus();
    boolean getListingStatus();
    File getAsmFile();

}