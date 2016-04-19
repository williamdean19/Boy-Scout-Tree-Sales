package userinterface;


import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;

//==============================================================================
public class TreeTypeTableModel
{
	private final SimpleStringProperty ID;
	private final SimpleStringProperty TypeDescription;
	private final SimpleStringProperty Cost;
	private final SimpleStringProperty BarcodePrefix;



	//----------------------------------------------------------------------------
	public TreeTypeTableModel(Vector<String> treeTypeData)
	{
		ID =  new SimpleStringProperty(treeTypeData.elementAt(0));
		TypeDescription =  new SimpleStringProperty(treeTypeData.elementAt(1));
		Cost =  new SimpleStringProperty(treeTypeData.elementAt(2));
		BarcodePrefix =  new SimpleStringProperty(treeTypeData.elementAt(3));
		
	}


	public String getID() {
		return ID.get();
	}

    public void setID(String number) {
        ID.set(number);
    }

	public String getTypeDescription() {
		return TypeDescription.get();
	}
    public void setTypeDescription(String desc) {
        TypeDescription.set(desc);
    }
    
	public String getCost() {
		return Cost.get();
	}
    public void setCost(String cost) {
        Cost.set(cost);
    }
    

	public String getBarcodePrefix() {
		return BarcodePrefix.get();
	}
    public void setBarcodePrefix(String prefix) {
        BarcodePrefix.set(prefix);
    }
    
    
}