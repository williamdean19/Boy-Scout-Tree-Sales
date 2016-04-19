package model;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JFrame;

import database.Persistable;
import exception.InvalidPrimaryKeyException;

public class Tree extends EntityBase
{
	private Properties mySchema;
	private static final String myTableName = "Tree";
	private String updateStatusMessage = "";
	private TLC myTLC;
	protected Properties dependencies;

	public Tree()
	{
		super(myTableName);

		persistentState.setProperty("Barcode", "");
		persistentState.setProperty("TreeType", "");
		persistentState.setProperty("Notes", "");
		persistentState.setProperty("Status", "");
		persistentState.setProperty("DateStatusUpdated", "");

	}	

	public Tree(String barcode)
	{
		super(myTableName);
		
		String query = "SELECT * FROM " + myTableName + "WHERE (Barcode = " + barcode + ")";
		
		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);
		
		if(allDataRetrieved !=null)
		{
			int size = allDataRetrieved.size();
			
			if(size !=1)
			{
				//throw new InvalidPrimaryKeyException("Multiple trees matching barcode : " + barcode + " found.");
				
			}
			else
			{
				Properties retrievedTreeData = allDataRetrieved.elementAt(0);
				persistentState = new Properties();
				
				Enumeration allKeys = retrievedTreeData.propertyNames();
				while (allKeys.hasMoreElements() == true)
				{
					String nextKey = (String)allKeys.nextElement();
					String nextValue = retrievedTreeData.getProperty(nextKey);
					
					if(nextValue!=null)
					{
						persistentState.setProperty(nextKey, nextValue);
					}
				}
			}
		}
		else
		{
			//throw new InvalidPrimaryKeyExcepion("No tree matching id :" + barcode + " found.");
		}
	}
	
	public Tree(Properties props)
	{
		super(myTableName);

		persistentState.setProperty("Barcode", props.getProperty("Barcode"));
		persistentState.setProperty("TreeType", props.getProperty("TreeType"));
		persistentState.setProperty("Notes", props.getProperty("TreeType"));
		persistentState.setProperty("Status", props.getProperty("Status"));
		persistentState.setProperty("DateStatusUpdated", props.getProperty("Status"));
	}
	
	public void updateState(String key, Object value)
	{
		stateChangeRequest(key, value);
	}
	
	public void update()
	{
		save();
	}
	
	public void delete()
	{
		Properties whereClause = new Properties();
		whereClause.setProperty("ID", persistentState.getProperty("ID"));
		try
		{
			deletePersistentState(mySchema, whereClause);
			updateStatusMessage = "The tree has been deleted successfully!";
		}
		catch (SQLException ex)
		{
			updateStatusMessage = "Error when updating tree info in the database!";
		}
	}
	
	private void save()
	{
		if(persistentState.getProperty("Barcode")!=null)
		{
			try
			{
				Properties whereClause = new Properties();
				whereClause.setProperty("Barcode", persistentState.getProperty("Barcode"));
				updatePersistentState(mySchema, persistentState, whereClause);
				updateStatusMessage = "Tree data for barcode : " + persistentState.getProperty("Barcode") + "installed successfully in database!";
			}
			catch(SQLException ex)
			{
				updateStatusMessage = "Error in installing tree data in database!";
			}
		}
		else
		{
			try
			{
				Properties whereClause = new Properties();
				whereClause.setProperty("Barcode", persistentState.getProperty("Barcode"));
				updatePersistentState(mySchema, persistentState, whereClause);
			}
			catch (SQLException ex)
			{
				updateStatusMessage = "Error in installing tree data in database!";
			}
		}
	}
	
	public String toString()
	{
		return "Tree: " + persistentState.getProperty("barcode") + ", " +
				"Tree Type: " + persistentState.getProperty("TreeType") + ", " +
				"Notes: " + persistentState.getProperty("Notes") + ", " +
				"Status: " + persistentState.getProperty("Status") + ", " +
				"Date Status Updated: " + persistentState.getProperty("DateStatusUpdated") + ".";
	}
	
	public String getBarcode()
	{
		return persistentState.getProperty("Barcode");
	}

	public void setBarcode(String str)
	{
		persistentState.setProperty("Barcode", str);
	}
	
	public String getTreeType()
	{
		return persistentState.getProperty("TreeType");
	}

	public void setTreeType(String str)
	{
		persistentState.setProperty("TreeType", str);
	}
	
	public String getNotes()
	{
		return persistentState.getProperty("Notes");
	}

	public void setNotes(String str)
	{
		persistentState.setProperty("Notes", str);
	}
	
	public String getStatus()
	{
		return persistentState.getProperty("Status");
	}

	public void setStatus(String str)
	{
		persistentState.setProperty("Status", str);
	}
	
	public String getDateStatusUpdated()
	{
		return persistentState.getProperty("DateStatusUpdated");
	}

	public void setDateStatusUpdated(String str)
	{
		persistentState.setProperty("DateStatusUpdated", str);
	}
	
	public Vector<String> getEntryListView()
	{
		Vector<String> v = new Vector<String>();
		
		v.addElement(persistentState.getProperty("Barcode"));
		v.addElement(persistentState.getProperty("TreeType"));
		v.addElement(persistentState.getProperty("Notes"));
		v.addElement(persistentState.getProperty("Status"));
		v.addElement(persistentState.getProperty("DateStatusUpdated"));
		
		return v;
	}

	@Override
	public Object getState(String key)
	{
		//TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stateChangeRequest(String key, Object value)
	{
		//TODO Auto-generated method stub
	}
	
	protected void initializeSchema(String tableName)
	{
		if(mySchema==null)
		{
			mySchema = getSchemaInfo(tableName);
		}
	}

}