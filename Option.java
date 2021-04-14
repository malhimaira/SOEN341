import java.io.File;
import java.io.FileInputStream;

//Class option to deal with the user input
public class Option implements IOption{

    //Variables - Status to check if they have to be done
    private boolean verboseStatus;
    private boolean listingStatus;
    private File asmFile;

    //Constructs
    public Option(){
        verboseStatus = false;
        listingStatus = false;
        asmFile = null;
    }

    //Setter
    public void setVerboseStatus(boolean verboseStatus) {
        this.verboseStatus = verboseStatus;
    }

    public void setListingStatus(boolean listingStatus) {
        this.listingStatus = listingStatus;
    }

    public void setASMFile(File file) {
        this.asmFile = file;
    }

    //Getter
    public boolean getVerboseStatus() {
        return verboseStatus;
    }

    public boolean getListingStatus() {
        return listingStatus;
    }

    public File getAsmFile(){
        return asmFile;
    }

    //Methods
    public void notationMsg(){
        System.out.println("Usage: cma [ Options ] <file>.asm");
        System.out.println("If you need more detail use the help option:\t-h \tOR \t-help");
        System.exit(0);
    }

    public void helpMsg(){
        System.out.println("\nUsage: cma [ Options ] <file>.asm\n");
        System.out.printf("%-15s%-15s%-15s\n","Short version","Long version","Meaning\n");
        System.out.printf("%-15s%-15s%-15s\n","-h","-help","Print the usage of the program.");
        System.out.printf("%-15s%-15s%-15s\n","-v","-verbose","Verbose during the execution of the program.");
        System.out.printf("%-15s%-15s%-15s\n","-b","-banner","Print the banner of the program.");
        System.out.printf("%-15s%-15s%-15s\n","-l","-listing","Generate a listing of the assembly file.");
        System.exit(0);
    }


    public boolean checkFile(String fileName){
        if(fileName.contains(".asm") && fileName.length() > 4){
            File asmFile = new File(fileName);
            if(asmFile.exists()) {
                setASMFile(asmFile);
                return true;
            }else{
                System.out.println("\nThis ASM file does not exist in this directory");
                return false;
            }
        }else{
            return false;
        }
    }


    public void doExecutable(File fileIn){
        System.out.println("\nCall exe");
    }

    public void doVerbose(File fileIn){
        setVerboseStatus(true);
        System.out.println("\nVerbose is activated");
        doExecutable(fileIn);
    }

    public void doListing(File fileIn){
        setListingStatus(true);
        System.out.println("\nListing is activated");
        doExecutable(fileIn);
    }

    public void userInput(String[] args){
        //Checking User inputs
        File asmFile;
        //Bad Case - User had put nothing past CMA or put too many arguments
        if(args.length < 1 || args.length > 2){
            System.out.println("\nError in the pattern of the command.\nThe command has to folllow the pattern below:");
            notationMsg();
        }

        //Cases where the user can give one argument
        if(args.length == 1) {

            //Good case - User asks for banner
            if(args[0].equals("-b")||args[0].equals("-banner")) {
                System.out.println("\nCm Cross-Assembler Version 4.1 - Developed by Team 2.");
                System.exit(0);
            }

            //Good case - User asks for help
            else if(args[0].equals("-h")||args[0].equals("-help")) {
                helpMsg();
            }

            //Good case - User only gave ASM file (Checking if it exist)
            else if(checkFile(args[0])){
                asmFile = new File(args[0]);
                doExecutable(asmFile);
            }

            //Bad case - the one argument given wasn't one of the options above
            else{
            System.out.println("\nError in the name of option or name of ASM file.");
            notationMsg();
            }
        }

        //Cases where the user can give one argument
        if(args.length == 2) {
            //Good case - User asks for verbose
            if(args[0].equals("-v")||args[0].equals("-verbose")){
                if(checkFile(args[1])){
                    asmFile = new File(args[1]);
                    doVerbose(asmFile);
                }
            }

            //Good case - User asks for listing
            else if((args[0].equals("-l")||args[0].equals("-listing")) && args[1].contains(".asm") && args[1].length() > 4){
                if(checkFile(args[1])){
                    asmFile = new File(args[1]);
                    doListing(asmFile);
                }
            }
            else{
            //Bad case - one or both arguments given weren't one of the options above
            System.out.println("\nError in the name of option or name of ASM file.");
            notationMsg();
            }
        }
    }

}