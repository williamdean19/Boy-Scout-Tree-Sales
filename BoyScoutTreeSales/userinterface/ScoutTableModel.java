package userinterface;


import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;

//==============================================================================
public class ScoutTableModel
{
	private final SimpleStringProperty ID;
	private final SimpleStringProperty LastName;
	private final SimpleStringProperty FirstName;
	private final SimpleStringProperty MiddleName;
	private final SimpleStringProperty DateOfBirth;
	private final SimpleStringProperty PhoneNumber;
	private final SimpleStringProperty Email;
	private final SimpleStringProperty TroopID;
	private final SimpleStringProperty Status;
	private final SimpleStringProperty DateStatusUpdated;


	//----------------------------------------------------------------------------
	public ScoutTableModel(Vector<String> scoutData)
	{
		ID =  new SimpleStringProperty(scoutData.elementAt(0));
		LastName =  new SimpleStringProperty(scoutData.elementAt(1));
		FirstName =  new SimpleStringProperty(scoutData.elementAt(2));
		MiddleName =  new SimpleStringProperty(scoutData.elementAt(3));
		DateOfBirth =  new SimpleStringProperty(scoutData.elementAt(4));
		PhoneNumber =  new SimpleStringProperty(scoutData.elementAt(5));
		Email =  new SimpleStringProperty(scoutData.elementAt(6));
		TroopID =  new SimpleStringProperty(scoutData.elementAt(7));
		Status =  new SimpleStringProperty(scoutData.elementAt(8));
		DateStatusUpdated =  new SimpleStringProperty(scoutData.elementAt(9));	
		
		
		
	}


	public String getID() {
		return ID.get();
	}

    public void setID(String number) {
        ID.set(number);
    }

	public String getLastName() {
		return LastName.get();
	}
    public void setLastName(String name) {
        LastName.set(name);
    }
    
	public String getFirstName() {
		return FirstName.get();
	}
    public void setFirstName(String name) {
        FirstName.set(name);
    }
    

	public String getMiddleName() {
		return MiddleName.get();
	}
    public void setMiddleName(String name) {
        MiddleName.set(name);
    }
    

	public String getDateOfBirth() {
		return DateOfBirth.get();
	}
    public void setDateOfBirth(String dob) {
        DateOfBirth.set(dob);
    }
    


	public String getPhoneNumber() {
		return PhoneNumber.get();
	}
    public void setPhoneNumber(String number) {
        PhoneNumber.set(number);
    }
    


	public String getEmail() {
		return Email.get();
	}
    public void setEmail(String email) {
        Email.set(email);
    }
    

	public String getTroopID() {
		return TroopID.get();
	}
    public void setTroopID(String troopId) {
        TroopID.set(troopId);
    }
    

	public String getStatus() {
		return Status.get();
	}
    public void setStatus(String status) {
        Status.set(status);
    }
    


	public String getDateStatusUpdated() {
		return DateStatusUpdated.get();
	}
    public void setDateStatusUpdated(String date) {
       DateStatusUpdated.set(date);
    }
    


    
}
