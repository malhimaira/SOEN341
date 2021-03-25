import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;


/**
 * Lexer Class used to create Token Objects from a given fileStream to send to the Parser
 */
public class Lexer implements ILexer {

    private HashMap<String, Token> SymbolTable = new HashMap<String, Token>();
    private ArrayList<String> TokenSequence;
    private int cntString;

    //Default not necessary for the class, only need
    public Lexer() {
    }

    /**
     * Method used to start the Lexer
     *
     * @param fileStream
     */
    public Lexer(FileInputStream fileStream, ErrorReporter errorReporter) {
        int metaChar;       // May contained eof or a character.
        int eofMarker = -1;
        String word = "";
        TokenSequence = new ArrayList<String>(10); //TODO Maira
        cntString = 0;//TODO Maira
        int colLex = 1;
        int rowLex = 1;
        char prevChar;
        boolean prevIsSpace = true; //account for mulitple spaces in a row
        boolean IscString =false;
        boolean EOLCheck = true;
        boolean invalidCharEOLCheck = false;

        int cntTLS = 0; //count tokens in a line statement


        boolean firstIter = true;
        try {
            while ((metaChar = fileStream.read()) != eofMarker) {// read is defined in the final class


            	if(invalidCharEOLCheck == true && (char)metaChar != ' ' && (char)metaChar != '\n' && (char)metaChar != '\r') {
            		continue;
            	}
            	else if(invalidCharEOLCheck == true) {
            		invalidCharEOLCheck = false;
            	}
            	
            	
            	//not alpha numeric
            	if(!(metaChar >=48 && metaChar <= 57) && !(metaChar >=65 && metaChar <= 90) && !(metaChar >=97 && metaChar <= 122)) {
            		//not a valid special char
            		if( metaChar!= 32 && metaChar != 34 && metaChar != 45 && metaChar != 46 && metaChar != 59 && metaChar != 60 && metaChar != 62 && metaChar != 10 && metaChar != 13) {
            			ErrorMsg invalidCharError = new ErrorMsg("Invalid char: '" + (char) metaChar + "'!", new Position(rowLex, colLex));
                         errorReporter.record(invalidCharError);
                         word = "";
                         invalidCharEOLCheck = true;
                	}
            		
            	}
            	
            	
                colLex += 1;

                if (metaChar != eofMarker & (metaChar == ' ' || metaChar == '\n' ||metaChar == '\r' )& !prevIsSpace) {

                    prevIsSpace = true;

//                    System.out.println();
//                    System.out.println(word);
//                    System.out.println();

                    cntTLS += 1;

                    if (cntTLS == 1) { 
                    	//case for directive
                        if(word.equals(".cstring")) {
                        	//create directive
                        	Directive dir = new Directive(new Position(rowLex, colLex-word.length()-1));
                        	IscString = true;
                        	if (word.length() != 0) 
                        	{
                        		SymbolTable.put(dir.toString(), dir);
                                TokenSequence.add(dir.toString());
                        	}
                        	word = "";
                        }
                      //Case for a mnemonic token
                        else if (word.contains(".")) { //if mnemonic should expect a number token next
                            //create the position inside the mnemonic using the word length and current column
                            Mnemonic mnem = new Mnemonic(word, true, new Position(rowLex, colLex-word.length()-1));
                            //mnem.setColLength(colLex);//needs to be redone
                            if (word.length() != 0) {
                                SymbolTable.put(mnem.toString(), mnem);
                                TokenSequence.add(mnem.toString());
                            }
                            word = "";
                        } else if (!word.contains(".")) { //if mnemonic does not need a number token
                            //create the position inside the mnemonic using the word length and current column
                            Mnemonic mnem = new Mnemonic(word, false, new Position(rowLex, colLex-word.length()-1));

                            if (word.length() != 0) {
                                SymbolTable.put(mnem.toString(), mnem);
                                TokenSequence.add(mnem.toString());
                            }
                            word = "";
                            cntTLS = 0;
                        } 
                        else {
                            System.out.print("mnemonic creation error");
                        }

                    } else if (cntTLS == 2) { //Case for a number token
                    	if(IscString == true) {
                    		IscString = false;
                    		StringOperand sp = new StringOperand(word, new Position(rowLex, colLex-2));
                            SymbolTable.put(sp.toString(), sp);
                            TokenSequence.add(sp.toString());
                    	}
                    	else {
                    		Number num = new Number(word, new Position(rowLex, colLex-2));
                            SymbolTable.put(num.toString(), num);
                            TokenSequence.add(num.toString());
                    	}
                        
                        cntTLS = 0;
                        word = "";
                    }
                } else if (metaChar != eofMarker & metaChar != ';' & metaChar != ' ') {
                    word += (char) metaChar;

                    prevIsSpace = false;
                } else if (metaChar != eofMarker & metaChar == ';') { //Handling for a comment token
                    prevIsSpace = false;
                    word += ";";

                    int spaceCount = 0; //allows for a max of 1 space at the end of a comment unless there is an EOL
                    while ((metaChar = fileStream.read()) != (int) '\n' && (metaChar) != (int) '\r') {
                        colLex += 1;


                        if(metaChar== 32){
                            spaceCount++;
                        }
                        else if (metaChar != 32){
                            spaceCount =0;
                        }
                        if(spaceCount==2){
                            spaceCount = 0;
                            break;
                        }
//                        if(prevIsSpace && metaChar == 32){
//                            break;
//                        }

                        //need to flip prevSpace on after first comment
                        word += (char) metaChar;


                    }
                    Comment comm = new Comment(word, new Position(rowLex, colLex-word.length()));
                    SymbolTable.put(comm.toString(), comm);
                    TokenSequence.add(comm.toString());

                    word = "";
                }

                if ((char) metaChar == '\n' || (char) metaChar == '\r' ) {
                    if(EOLCheck == true) {
                    	//process
                    	EOLCheck = false;
                    	prevIsSpace = false;
                        EOL eol = new EOL("EOL", new Position(rowLex,colLex));
                        SymbolTable.put(eol.toString(), eol);
                        TokenSequence.add(eol.toString());
                        word = "";
                        colLex = 1;
                        rowLex += 1;
                    }
                    else {
                    	EOLCheck = true;
                    }
                	

                }


            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        EOF eof = new EOF("EOF", new Position(rowLex, 1));
        SymbolTable.put(eof.toString(), eof);
        TokenSequence.add(eof.toString());
        
    }

    /**
     * Get The Symbol Table (in case of parameter
     *
     * @return
     */
    public HashMap<String, Token> getSymbolTable() {
        return SymbolTable;
    }


    /**
     * Method for returning tokens sequentially
     *
     * @return Token
     */
    public Token getNextToken() {
        Token t = null;

        if (cntString != TokenSequence.size() && SymbolTable.get(TokenSequence.get(cntString)) != null) {
            t = SymbolTable.get(TokenSequence.get(cntString));
            cntString++;
            return t;
        } else {
            return t; //Team 4 please handle the case where the Token is null, if it is then just don't process it.
        }
    }


}