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
    public Lexer(FileInputStream fileStream) {
        int metaChar;       // May contained eof or a character.
        int eofMarker = -1;
        String word = "";
        TokenSequence = new ArrayList<String>(10); //TODO Maira
        cntString = 0;//TODO Maira
        int colLex = 1;
        int rowLex = 1;
        boolean prevIsSpace = true; //account for mulitple spaces in a row

        int cntTLS = 0; //count tokens in a line statement


        boolean firstIter = true;
        try {
            while ((metaChar = fileStream.read()) != eofMarker) {// read is defined in the final class
                colLex += 1;
                if (metaChar != eofMarker & metaChar == ' ' & !prevIsSpace) {

                    prevIsSpace = true;

//                    System.out.println();
//                    System.out.println(word);
//                    System.out.println();

                    cntTLS += 1;

                    if (cntTLS == 1) { //Case for a mnemonic token
                        if (word.contains(".")) { //if mnemonic needs a number token
                            Mnemonic mnem = new Mnemonic(word, true, colLex, rowLex);
                            mnem.setColLength(colLex);
                            if (word.length() != 0) {
                                SymbolTable.put(mnem.getName(), mnem);
                                TokenSequence.add(mnem.getName());
                            }
                            word = "";
                        } else if (!word.contains(".")) { //if mnemonic does not need a number token
                            Mnemonic mnem = new Mnemonic(word, false, colLex, rowLex);
                            mnem.setColLength(colLex);
                            if (word.length() != 0) {
                                SymbolTable.put(mnem.getName(), mnem);
                                TokenSequence.add(mnem.getName());
                            }
                            word = "";
                            cntTLS = 0;
                        } else {
                            System.out.print("mnemonic creation error");
                        }

                    } else if (cntTLS == 2) { //Case for a number token
                        Number num = new Number(word, colLex - 2, rowLex);
                        SymbolTable.put(num.getName(), num);
                        TokenSequence.add(num.getName());
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
                    Comment comm = new Comment(word, colLex, rowLex);
                    SymbolTable.put(comm.getName(), comm);
                    TokenSequence.add(comm.getName());

                    word = "";
                }
                if ((char) metaChar == '\n') {
                    prevIsSpace = false;
                    EOL eol = new EOL("EOL", colLex, rowLex);
                    SymbolTable.put(eol.getName(), eol);
                    TokenSequence.add(eol.getName());
                    word = "";
                    colLex = 1;
                    rowLex += 1;

                }


            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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