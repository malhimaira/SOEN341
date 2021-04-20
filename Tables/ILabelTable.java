package Tables;

import FrontEnd.*;
import BackEnd.*;
import main.*;
import TestFiles.*;

import java.util.HashMap;

/**
 * 
 * Interface for Label Table
 *
 */
public interface ILabelTable {
	public void put(String key, Label value);
	
    public Label get(String key);
    
    HashMap<String, Label> getLabelTable();
}
