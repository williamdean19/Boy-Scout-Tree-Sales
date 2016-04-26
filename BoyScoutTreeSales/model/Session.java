package model;

import database.Persistable;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import java.util.Vector;

public class Session extends EntityBase{

	private Properties mySchema;
	private static final String myTableName = "Session";
	private String updateStatusMessage = "";
	private TLC myTLC;

	public Session()
	{
		super(myTableName);

		//Set these to the schema of the Scout table
		persistentState.setProperty("StartDate", "");
		persistentState.setProperty("StartTime", "");
		persistentState.setProperty("EndTime", "");
		persistentState.setProperty("StartingCash", "");
		persistentState.setProperty("EndingCash", "");
		persistentState.setProperty("TotalCheckTransactionAmount", "");
		persistentState.setProperty("Notes", "");
	}

	public Session(Properties props)
	{
		super(myTableName);

		persistentState.setProperty("StartDate", props.getProperty("StartDate") );
		persistentState.setProperty("StartTime", props.getProperty("StartTime"));
		persistentState.setProperty("EndTime", props.getProperty("EndTime"));
		persistentState.setProperty("StartingCash", props.getProperty("StartingCash"));
		persistentState.setProperty("EndingCash", props.getProperty("EndingCash"));
		persistentState.setProperty("TotalCheckTransactionAmount", props.getProperty("TotalCheckTransactionAmount"));
		persistentState.setProperty("Notes", props.getProperty("Notes"));
	}

	public Session(String SessionId)
	{
		super(myTableName);

		String query = "SELECT * FROM " + myTableName + " WHERE (ID = " + SessionId + ")";

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
				Integer sessionNum = insertAutoIncrementalPersistentState(mySchema, persistentState);
				persistentState.setProperty("ID", "" + sessionNum.intValue());
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
	public String getSessionId()
	{
		return persistentState.getProperty("ID");
	}

	public String getStartDate()
	{
		return persistentState.getProperty("StartDate");
	}
	public void setStartDate(String value)
	{
		persistentState.setProperty("StartDate", value);
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
	
	public String getStartingCash()
	{
		return persistentState.getProperty("StartingCash");
	}
	public void setStartingCash(String value)
	{
		persistentState.setProperty("StartingCash", value);
	}
	
	public String getEndingCash()
	{
		return persistentState.getProperty("EndingCash");
	}
	public void setEndingCash(String value)
	{
		persistentState.setProperty("EndingCash", value);
	}
	
	public String getTotalCheckTransactionAmount()
	{
		return persistentState.getProperty("TotalCheckTransactionAmount");
	}
	public void setTotalCheckTransactionAmount(String value)
	{
		persistentState.setProperty("TotalCheckTransactionAmount", value);
	}
	
	public String getNotes()
	{
		return persistentState.getProperty("Notes");
	}
	public void setNotes(String value)
	{
		persistentState.setProperty("Notes", value);
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
