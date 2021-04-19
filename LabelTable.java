import java.util.HashMap;

public class LabelTable implements ILabelTable{

	private HashMap<String, Label> labTab;
	
	public LabelTable() {
		this.labTab = new HashMap<String, Label>();
	}
	public void put(String key, Label value) {//Put a key and value in the symbol table
		
		this.labTab.put(key,value);
	} 
	
    public Label get(String key) {//Get the value associated with the key
    	
    	return this.labTab .get(key);
    }
    
    public HashMap<String, Label> getLabelTable(){ //PACKAGE PRIVATE METHOD TO GET SYMBOL TABLE
    	return labTab;
    }
	
}
