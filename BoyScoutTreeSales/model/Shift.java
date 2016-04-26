package model;

import database.Persistable;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import java.util.Vector;

public class Shift extends EntityBase{

	private Properties mySchema;
	private static final String myTableName = "Shift";
	private String updateStatusMessage = "";
	private TLC myTLC;

	public Shift()
	{
		super(myTableName);

		//Set these to the schema of the Scout table
		persistentState.setProperty("SessionID", "");
		persistentState.setProperty("ScoutID", "");
		persistentState.setProperty("CompanionName", "");
		persistentState.setProperty("StartTime", "");
		persistentState.setProperty("EndTime", "");
		persistentState.setProperty("CompanionHours", "");
	}

	public Shift(Properties props)
	{
		super(myTableName);

		persistentState.setProperty("SessionID", props.getProperty("SessionID"));
		persistentState.setProperty("ScoutID", props.getProperty("ScoutID"));
		persistentState.setProperty("CompanionName", props.getProperty("CompanionName"));
		persistentState.setProperty("StartTime", props.getProperty("StartTime"));
		persistentState.setProperty("EndTime", props.getProperty("EndTime"));
		persistentState.setProperty("CompanionHours", props.getProperty("CompanionHours"));
	}

	public Shift(String shiftId)
	{
		super(myTableName);

		String query = "SELECT * FROM " + myTableName + " WHERE (ID = " + shiftId + ")";

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
				Properties retrievedSessionData = allDataRetrieved.elementAt(0);
				persistentState = new Properties();

				Enumeration allKeys = retrievedSessionData.propertyNames();
				while (allKeys.hasMoreElements() == true)
				{
					String nextKey = (String)allKeys.nextElement();
					String nextValue = retrievedSessionData.getProperty(nextKey);

					if(nextValue != null)
					{
						persistentState.setProperty(nextKey, nextValue);
					}
				}

			}
		}

		else
		{
			// If no account found for this scoutId,
			// display message stating no scout found
		}
	}

	public void save()
	{
		if(persistentState.getProperty("ID") == null)
		{
			try
			{
				Integer shiftNum = insertAutoIncrementalPersistentState(mySchema, persistentState);
				persistentState.setProperty("ID", "" + shiftNum.intValue());
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
	public String getShiftId()
	{
		return persistentState.getProperty("ID");
	}

	public String getSessionId()
	{
		return persistentState.getProperty("SessionID");
	}
	public void setSessionId(String value)
	{
		persistentState.setProperty("SessionID", value);
	}
	
	public String getScoutId()
	{
		return persistentState.getProperty("ScoutID");
	}
	public void setScoutId(String value)
	{
		persistentState.setProperty("ScoutID", value);
	}
	
	public String getCompanionName()
	{
		return persistentState.getProperty("CompanionName");
	}
	public void setCompanionName(String value)
	{
		persistentState.setProperty("CompanionName", value);
	}
	
	public String getStartTime()
	{
		return persistentState.getProperty("StartTime");
	}
	public void setStartTime(String value)
	{
		persistentState.setProperty("StartTime", value);
	}
	
	public String getEndTime()
	{
		return persistentState.getProperty("EndTime");
	}
	public void setEndTime(String value)
	{
		persistentState.setProperty("EndTime", value);
	}
	
	public String getCompanionHours()
	{
		return persistentState.getProperty("CompanionHours");
	}
	public void setCompanionHours(String value)
	{
		persistentState.setProperty("CompanionHours", value);
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
