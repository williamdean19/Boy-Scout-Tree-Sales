package model;

import database.Persistable;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import java.util.Vector;

public class Scout extends EntityBase{
	
	private Properties mySchema;
	private static final String myTableName = "Scout";
	private String updateStatusMessage = "";
	private TLC myTLC;
	
	public Scout()
	{
		super(myTableName);
		
		//Set these to the schema of the Scout table
		persistentState.setProperty("DateOfBirth", "");
		persistentState.setProperty("DateStatusUpdated", "");
		persistentState.setProperty("Email", "");
		persistentState.setProperty("FirstName", "");
		persistentState.setProperty("LastName", "");
		persistentState.setProperty("MiddleName", "");
		persistentState.setProperty("PhoneNumber", "");
		persistentState.setProperty("Status", "");
		persistentState.setProperty("TroopID", "");
		
		
	}
	
	public Scout(Properties props)
	{
		super(myTableName);
		
		//Set these to the schema of the Scout table
		persistentState.setProperty("DateOfBirth", props.getProperty("DateOfBirth"));
		persistentState.setProperty("DateStatusUpdated", props.getProperty("DateStatusUpdated"));
		persistentState.setProperty("Email", props.getProperty("Email"));
		persistentState.setProperty("FirstName", props.getProperty("FirstName"));
		persistentState.setProperty("ID", props.getProperty("ID"));
		persistentState.setProperty("LastName", props.getProperty("LastName"));
		persistentState.setProperty("MiddleName", props.getProperty("MiddleName"));
		persistentState.setProperty("PhoneNumber", props.getProperty("PhoneNumber"));
		persistentState.setProperty("Status", props.getProperty("Status"));
		persistentState.setProperty("TroopID", props.getProperty("TroopID"));
		
	}
	
	public Scout(String scoutId)
	{
		super(myTableName);
		
		String query = "SELECT * FROM " + myTableName + " WHERE (scoutId = " + scoutId + ")";
		
		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);
		
		if(allDataRetrieved != null)
		{
			int size = allDataRetrieved.size();
			
			// There should be EXACTLY one account.  More than one is an error.
			if(size != 1)
			{
				// Display error message
			}
			else
			{
				// Copy all retrieved data into persistent state
				Properties retrievedScoutData = allDataRetrieved.elementAt(0);
				persistentState = new Properties();
				
				Enumeration allKeys = retrievedScoutData.propertyNames();
				while (allKeys.hasMoreElements() == true)
				{
					String nextKey = (String)allKeys.nextElement();
					String nextValue = retrievedScoutData.getProperty(nextKey);
					
					if(nextValue != null)
					{
						persistentState.setProperty(nextKey, nextValue);
					}
				}
				
			}
		}

		else
		{
			// If not account found for this scoutId,
			// display message stating no scout found
		}
	}
	
	public void save()
	{
		if(persistentState.getProperty("bookId") == null)
		{
			try
			{
				Integer scoutNum = insertAutoIncrementalPersistentState(mySchema, persistentState);
				persistentState.setProperty("ID", "" + scoutNum.intValue());
				// Display message confirming scout successfully saved to database
			}
			catch (SQLException ex)
			{
				// Display message saying there was an error saving scout info to database
			}
		}
		else
		{
			try
			{
				Properties whereClause = new Properties();
				whereClause.setProperty("ID",  persistentState.getProperty("ID"));
				updatePersistentState(mySchema, persistentState, whereClause);
				// Display message confirming scout data successfully updated
				
			}
			catch (SQLException ex)
			{
				// Display message saying there was an error updating scout info in database
			}
		}
	}
	public void delete()
	{
		
	}
	public String toString()
	{
		return "Scout: " + persistentState.getProperty("ID") + ", " +
				"First Name: " + persistentState.getProperty("FirstName") + ", " +
				"Middle Initial: " + persistentState.getProperty("MiddleName") + ", " +
				"Last Name: " + persistentState.getProperty("LastName") + ", " +
				"Email: " + persistentState.getProperty("Email") + ", " +
				"Phone Number: " + persistentState.getProperty("PhoneNumber") + ", " +
				"Date of Birth: " + persistentState.getProperty("DateOfBirth") + ", " +
				"Status: " + persistentState.getProperty("Status") + ", " +
				"Date Status Updated: " + persistentState.getProperty("DateStatusUpdated") + ", " +
				"TroopID: " + persistentState.getProperty("TroopID") + ".";
	}

	public String getScoutId()
	{
		return persistentState.getProperty("ID");
	}

	public String getFirstName()
	{
		return persistentState.getProperty("FirstName");
		
	}
	public String getMiddleName()
	{
		return persistentState.getProperty("MiddleName");
	}
	public String getLastName()
	{
		return persistentState.getProperty("LastName");
	}
	public String getEmail()
	{
		return persistentState.getProperty("Email");
	}
	public String getPhoneNumber()
	{
		return persistentState.getProperty("PhoneNumber");
	}
	public String getDateOfBirth()
	{
		return persistentState.getProperty("DateOfBirth");
	}
	public String getStatus()
	{
		return persistentState.getProperty("Status");
	}
	public String getDateStatusUpdated()
	{
		return persistentState.getProperty("DateStatusUpdated");
	}
	public String getTroopId()
	{
		return persistentState.getProperty("TroopID");
	}
	
	@Override
	public Object getState(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stateChangeRequest(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	protected void initializeSchema(String tableName) {
		if (mySchema == null) {
			mySchema = getSchemaInfo(tableName);
		}
	}
	
}
