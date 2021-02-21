import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class Lexer implements ILexer{

	private HashMap<String, Token> SymbolTable = new HashMap<String, Token>();	
	private Iterator iter;


	public Lexer() {
		super();
	}
	//test
	/** 
	 * 
	 * @param reader
	 */
	public void execute(FileInputStream fileStream) {
		int metaChar;       // May contained eof or a character.
		int eofMarker = -1 ;
		String word ="";
		boolean firstIter = true;
		try {
			while ((metaChar = fileStream.read()) != eofMarker) {// read is defined in the final class
				if((char)metaChar == '\n') { // check how EOL actually gets read
					EOL e1 = new EOL("EOL");
					SymbolTable.put(e1.getName(), e1);
					continue;
				}
				if ((char)metaChar == ' ' && !firstIter ) { //just to account the space at the beginning, space char included for now as thats what is in the asm
					Mnemonic m1 = new Mnemonic(word); //needs to be more generalized more after
					SymbolTable.put(m1.getName(), m1);
					word="";
				}
				else {
					firstIter = false;
					word += (char)metaChar;
				}
			}
			Mnemonic endCase = new Mnemonic(word); //needs to be more generalized more after
			SymbolTable.put(endCase.getName(), endCase);
			word="";

			EOF eof = new EOF("EOF");
			SymbolTable.put(eof.getName(), eof);
			//System.out.println("<eof>");
			iter = SymbolTable.entrySet().iterator();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * @param key
	 * @return
	 */
	public Token getNextToken() {
		Token t = null;
		if(iter.hasNext()) {
						HashMap.Entry<String, Token> me = (HashMap.Entry<String, Token>) iter.next();
			if(me.getValue().getClass().equals("Token")) {
				t = (Token) me.getValue();
				return t;
			}
			else if(me.getValue().getClass().equals("Mnemonic")) {
				t = (Mnemonic) me.getValue();
				return t;
			}
			else if(me.getValue().getClass().equals("EOL")) {
				t = (EOL) me.getValue();
				return t;
			}
			else if(me.getValue().getClass().equals("EOF")) {
				t = (EOF) me.getValue();
				return t;
			}
			return t; 
		}
		else {
			return t; //Team 4 please handle the case where the Token is null, if it is then just don't process it.
		}	
	}

}