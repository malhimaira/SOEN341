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
        System.out.println("\nError in the name of option or name of ASM file.");
        System.out.println("Usage: cma [ Options ] <file>.asm");
        System.out.println("If you need more detail use the help option:\t-h \tOR \t-help");
        return;
    }

    public void helpMsg(){
        System.out.println("\nUsage: cma [ Options ] <file>.asm\n");
        System.out.printf("%-15s%-15s%-15s\n","Short version","Long version","Meaning\n");
        System.out.printf("%-15s%-15s%-15s\n","-h","-help","Print the usage of the program.");
        System.out.printf("%-15s%-15s%-15s\n","-v","-verbose","Verbose during the execution of the program.");
        System.out.printf("%-15s%-15s%-15s\n","-b","-banner","Print the banner of the program.");
        System.out.printf("%-15s%-15s%-15s\n","-l","-listing","Generate a listing of the assembly file.");

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
        //System.out.println("\nCall exe"); Hiding this for now
    }

    public void doVerbose(){
        setVerboseStatus(true);
        System.out.println("\nVerbose is activated");
    }

    public void doListing(){
        setListingStatus(true);
        System.out.println("\nListing is activated");
    }

    public void userInput(String[] args) {
        //Checking User inputs
        File asmFile;
        //Bad Case - User had put nothing past CMA or put too many arguments
        if (args.length < 1 || args.length > 5) {
            notationMsg();
        }

        //Cases depends on what is the first argument and continue if there's more than one
        boolean doneB,doneH,doneL,doneV,didRun;
        didRun=doneB=doneH=doneL=doneV=false;

        for(int index = 0; index < args.length; index++) {

            //Good case - User asks for banner
            if ((args[index].equals("-b") || args[index].equals("-banner")) && doneB == false) {
                System.out.println("\nCm Cross-Assembler Version 4.1 - Developed by Team 2.");
                doneB = true;
                didRun = true;
            }

            //Good case - User asks for help
            else if ((args[index].equals("-h") || args[index].equals("-help")) && doneH == false) {
                doneH = true;
                didRun = true;
                helpMsg();
            }

            //Good case - User asks for verbose
            else if ((args[index].equals("-v") || args[index].equals("-verbose")) && doneV == false) {
                if (checkFile(args[args.length-1])) {
                    doVerbose();
                    didRun = true;
                    doneV = true;
                }else{notationMsg();}
            }

            //Good case - User asks for listing
            else if ((args[index].equals("-l") || args[index].equals("-listing")) && doneL == false) {
                if (checkFile(args[args.length-1])) {
                    doListing();
                    didRun = true;
                    doneL = true;
                }else{notationMsg();}
            }

            //Good case - User only gave ASM file (Checking if it exist)
            else if (checkFile(args[index]) && index == (args.length - 1)) {
                asmFile = new File(args[index]);
                didRun = true;
                doExecutable(asmFile);
            }

            //Bad case - the one argument given wasn't one of the options above
            else if(doneB == false && doneH == false && doneL == false && doneV == false && didRun == false){
                notationMsg();
            }
            didRun = false;
        }
    }

}