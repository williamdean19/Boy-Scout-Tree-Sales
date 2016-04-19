package model;

import database.Persistable;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class TreeType extends EntityBase{

	private Properties mySchema;
	private static final String myTableName = "TreeType";
	private String updateStatusMessage = "";
	private TLC myTLC;

	public TreeType()
	{
		super(myTableName);

		//Set these to the schema of the Scout table
		persistentState.setProperty("TypeDescription", "");
		persistentState.setProperty("Cost", "");
		persistentState.setProperty("BarcodePrefix", "");

	}

	public TreeType(Properties props)
	{
		super(myTableName);

		//Set these to the schema of the Scout table
			persistentState.setProperty("TypeDescription", props.getProperty("TypeDescription"));
			persistentState.setProperty("Cost", props.getProperty("Cost"));
			persistentState.setProperty("BarcodePrefix", props.getProperty("BarcodePrefix"));
	}

	public TreeType(String treeTypeId)
	{
		super(myTableName);

		String query = "SELECT * FROM " + myTableName + " WHERE (ID = " + treeTypeId + ")";

		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

		if(allDataRetrieved != null)
		{
			int size = allDataRetrieved.size();

			// There should be EXACTLY one of each Tree Type.  More than one is an error.
			if(size != 1)
			{
				// Display error message
			}
			else
			{
				// Copy all retrieved data into persistent state
				Properties retrievedTreeTypeData = allDataRetrieved.elementAt(0);
				persistentState = new Properties();

				Enumeration allKeys = retrievedTreeTypeData.propertyNames();
				while (allKeys.hasMoreElements() == true)
				{
					String nextKey = (String)allKeys.nextElement();
					String nextValue = retrievedTreeTypeData.getProperty(nextKey);

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
				Integer treeTypeNum = insertAutoIncrementalPersistentState(mySchema, persistentState);
				persistentState.setProperty("ID", "" + treeTypeNum.intValue());
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
	
	public String toString()
	{
		return "TreeType: " + persistentState.getProperty("ID") + ", " +
				"Type Description: " + persistentState.getProperty("TypeDescription") + ", " +
				"Cost: " + persistentState.getProperty("Cost") + ", " +
				"Barcode Prefix: " + persistentState.getProperty("BarcodePrefix") + ". ";
	}

	public String getTreeTypeId()
	{
		return persistentState.getProperty("ID");
	}

	public String getTypeDescription()
	{
		return persistentState.getProperty("TypeDescription");
	}
	public void setTypeDescription(String value)
	{
		persistentState.setProperty("TypeDescription", value);
	}
	
	public String getCost()
	{
		return persistentState.getProperty("Cost");
	}
	public void setCost(String value)
	{
		persistentState.setProperty("Cost", value);
	}
	
	public String getBarcodePrefix()
	{
		return persistentState.getProperty("BarcodePrefix");
	}
	public void setBarcodePrefix(String value)
	{
		persistentState.setProperty("BarcodePrefix", value);
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
	
	/**
	 * This method is needed solely to enable the Book information to be displayable in a table
	 *
	 */
	//--------------------------------------------------------------------------
	public Vector<String> getEntryListView()
	{
		Vector<String> v = new Vector<String>();

		v.addElement(persistentState.getProperty("ID"));
		v.addElement(persistentState.getProperty("TypeDescription"));
		v.addElement(persistentState.getProperty("Cost"));
		v.addElement(persistentState.getProperty("BarcodePrefix"));

		return v;
	}

}