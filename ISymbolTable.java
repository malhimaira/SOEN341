public interface ISymbolTable {
    void put(String key, Token value); //Put a key and value in the symbol table
    IToken get(String key); //Get the value associated with the key
    //int findOpcode(String mnemonicName); //Uses mapping of SymbolTable to find opcode for mnemonic, -1 if not in the mapping
}
