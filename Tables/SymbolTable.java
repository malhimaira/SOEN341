package Tables;

import FrontEnd.*;
import BackEnd.*;
import main.*;
import TestFiles.*;


import java.util.HashMap;
/**
 * 
 * Symbol Table Class 
 *
 */
public class SymbolTable implements ISymbolTable{
	
	private HashMap<String, Token> st;
	
	public SymbolTable() {
		this.st = new HashMap<String, Token>();
	}
	
	public void put(String key, Token value) {//Put a key and value in the symbol table
		
		this.st.put(key,value);
	} 
	
    public Token get(String key) {//Get the value associated with the key
    	
    	return this.st.get(key);
    }
    
    public HashMap<String, Token> getSymbolTable(){ //PACKAGE PRIVATE METHOD TO GET SYMBOL TABLE
    	return st;
    }
    
//    public int findOpcode(String mnemonicName) {
//    	
//    } //Uses mapping of SymbolTable to find opcode for mnemonic, -1 if not in the mapping
}
