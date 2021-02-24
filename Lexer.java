import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.Map;

/**
 * Lexer Class used to create Token Objects from a given fileStream to send to the Parser
 */
public class Lexer implements ILexer{

	private HashMap<String, Token> SymbolTable = new HashMap<String, Token>();
	private Iterator<Map.Entry<String, Token>> iter;
	private ArrayList<String> TokenSequence;
	private int cntString;

	//Default not necessary for the class, only need 
	public Lexer() {}

	/**
	 *Method used to start the Lexer
	 * @param fileStream
	 */
	public Lexer(FileInputStream fileStream) {
		int metaChar;       // May contained eof or a character.
		int eofMarker = -1 ;
		String word ="";
		TokenSequence = new ArrayList<String>(10); //TODO Maira
		cntString = 0;//TODO Maira
		
		
		boolean firstIter = true;
		try {
			while ((metaChar = fileStream.read()) != eofMarker) {// read is defined in the final class
				if((char)metaChar == '\n') { // check how EOL actually gets read

					if (!word.trim().equals("")) { //TODO Brandon, without this check you end up missing a whole word sometimes!
						Mnemonic m1 = new Mnemonic(word.trim()); //needs to be more generalized more after //TODO Brandon added trim
					SymbolTable.put(m1.getName(), m1);
					TokenSequence.add(m1.getName()); //TODO Maira
					System.out.println(m1); //TODO Brandon
					word="";
					firstIter = true; //TODO Brandon
					}

					EOL e1 = new EOL("EOL");
					SymbolTable.put(e1.getName(), e1);
					TokenSequence.add(e1.getName()); //TODO Maira
					System.out.println(e1); //TODO Brandon
					continue;
				}
				if ((char)metaChar == ' ' && !firstIter && !word.trim().equals("")) { //just to account the space at the beginning, space char included for now as thats what is in the asm
					Mnemonic m1 = new Mnemonic(word.trim()); //needs to be more generalized more after //TODO Brandon added trim 
					SymbolTable.put(m1.getName(), m1);
					TokenSequence.add(m1.getName()); //TODO Maira
					System.out.println(m1); //TODO Brandon
					word="";
					firstIter = true; //TODO Brandon
				}
				else {
					firstIter = false;
					word += (char)metaChar;
					System.out.println(word);
				}
			}
			//CHECK FOR LAST ITERATION IN CASE 
			/*
			Mnemonic endCase = new Mnemonic(word.trim()); //needs to be more generalized more after 
			System.out.println(endCase);
			SymbolTable.put(endCase.getName(), endCase);
			word="";*/

			EOF eof = new EOF("EOF");
			SymbolTable.put(eof.getName(), eof);
			TokenSequence.add(eof.getName());  //TODO Maira
			//System.out.println("<eof>");
			iter = SymbolTable.entrySet().iterator();
			System.out.println(SymbolTable);
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Trial to have a parameterized get token method- case where parser asks for a type of token
	 */
	public Token getNextToken(String tokentype) {
		
		//Stack and request all types in Parser and and give each possible key *** NEED POSITION!!!
		//will remain unordered and the position would need to handle this.
		if(SymbolTable.containsKey(tokentype)) {
			return SymbolTable.get(tokentype);
		}
		
		// ask for a token type (mnemonics, EOL or EOF)
		//Search the  HashMap for the next one of thus and keep track?
		if(SymbolTable.containsKey(tokentype)) {
			
		}
		return null;
	}
	
	
	
	/**
	 * OLD
	 * @return Token
	 */
	public Token getNextToken() {
		Token t = null;
		if(iter.hasNext()) {
						HashMap.Entry<String, Token> me = (HashMap.Entry<String, Token>) iter.next();
			if(me.getValue().getClass() == Token.class ) {
				t = (Token) me.getValue();
				return t;
			}
			else if(me.getValue().getClass() == Mnemonic.class) {
				t = (Mnemonic) me.getValue();
				return t;
			}
			else if(me.getValue().getClass() == EOL.class) {
				t = (EOL) me.getValue();
				return t;
			}
			else if(me.getValue().getClass() == EOF.class) {
				t = (EOF) me.getValue();
				return t;
			}
			return t; 
		}
		else {
			return t; //Team 4 please handle the case where the Token is null, if it is then just don't process it.
		}	
	}
	
	public Token getNextTokenArray() {
		Token t = null;
		
		if (SymbolTable.get(TokenSequence.get(cntString))!= null) {
			t = SymbolTable.get(TokenSequence.get(cntString));
			cntString++;
			return t; 
		}
		else {
			return t; //Team 4 please handle the case where the Token is null, if it is then just don't process it.
		}	
	}

}