package model;

import database.Persistable;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class Transaction extends EntityBase{

	private Properties mySchema;
	private static final String myTableName = "Transaction";
	private String updateStatusMessage = "";
	private TLC myTLC;

	public Transaction()
	{
		super(myTableName);

		//Set these to the schema of the Transaction table
		persistentState.setProperty("SessionID", "");
		persistentState.setProperty("TransactionType", "");
		persistentState.setProperty("Barcode", "");
		persistentState.setProperty("TransactionAmount", "");
		persistentState.setProperty("PaymentMethod", "");
		persistentState.setProperty("CustomerName", "");
		persistentState.setProperty("CustomerPhone", "");
		persistentState.setProperty("CustomerEmail", "");
		persistentState.setProperty("TransactionDate", "");
		persistentState.setProperty("TransactionTime", "");
		persistentState.setProperty("DateStatusUpdated", "");
	}

	public Transaction(Properties props)
	{
		super(myTableName);

		//Set these to the schema of the Transaction table
		persistentState.setProperty("SessionID", props.getProperty("SessionID"));
		persistentState.setProperty("TransactionType", props.getProperty("TransactionType"));
		persistentState.setProperty("Barcode", props.getProperty("Barcode"));
		persistentState.setProperty("TransactionAmount", props.getProperty("TransactionAmount"));
		persistentState.setProperty("PaymentMethod", props.getProperty("PaymentMethod"));
		persistentState.setProperty("CustomerName", props.getProperty("CustomerName"));
		persistentState.setProperty("CustomerPhone", props.getProperty("CustomerPhone"));
		persistentState.setProperty("CustomerEmail", props.getProperty("CustomerEmail"));
		persistentState.setProperty("TransactionDate", props.getProperty("TransactionDate"));
		persistentState.setProperty("TransactionTime", props.getProperty("TransactionTime"));
		persistentState.setProperty("DateStatusUpdated", props.getProperty("DateStatusUpdated"));

	}

	public Transaction(String transactionId)
	{
		super(myTableName);

		String query = "SELECT * FROM " + myTableName + " WHERE (ID = " + transactionId + ")";

		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

		if(allDataRetrieved != null)
		{
			int size = allDataRetrieved.size();

			// There should be EXACTLY one transaction.  More than one is an error.
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
			// If no transaction found for this transactionId,
			// display message stating no transaction found
		}
	}

	public void save()
	{
		if(persistentState.getProperty("ID") == null)
		{
			try
			{
				Integer transactionNum = insertAutoIncrementalPersistentState(mySchema, persistentState);
				persistentState.setProperty("ID", "" + transactionNum.intValue());
				// Display message confirming transaction successfully saved to database
				System.out.println("Transaction successfully saved to database.");
			}
			catch (SQLException ex)
			{
				// Display message saying there was an error saving transaction info to database
			}
		}
		else
		{
			try
			{
				Properties whereClause = new Properties();
				whereClause.setProperty("ID",  persistentState.getProperty("ID"));
				updatePersistentState(mySchema, persistentState, whereClause);
				// Display message confirming transaction data successfully updated

			}
			catch (SQLException ex)
			{
				// Display message saying there was an error updating scout info in database
			}
		}
	}
/*	public void delete()
	{
		try
		{
			//persistentState.setProperty("Status", "Inactive");
			Properties whereClause = new Properties();
			whereClause.setProperty("ID",  persistentState.getProperty("ID"));
			updatePersistentState(mySchema, persistentState, whereClause);
			// Display message confirming transaction data successfully deactivated

		}
		catch (SQLException ex)
		{
			// Display message saying there was an error updating transaction info in database
		}
		
		/*
		Properties whereClause = new Properties();
		whereClause.setProperty("ID",  persistentState.getProperty("ID"));
		try
		{
			deletePersistentState(mySchema, whereClause);
			// Display message confirming transaction data successfully deleted
		}
		catch (SQLException ex) 
		{
			// Display message saying there was an error deleting the transaction
		}
		
	}
*/
	public String toString()
	{
		return "Transaction: " + persistentState.getProperty("ID") + ", " +
				"Session ID: " + persistentState.getProperty("SessionID") + ", " +
				"Transaction Type: " + persistentState.getProperty("TransactionType") + ", " +
				"Barcode: " + persistentState.getProperty("Barcode") + ", " +
				"Transaction Amount: " + persistentState.getProperty("TransactionAmount") + ", " +
				"Payment Method: " + persistentState.getProperty("PaymentMethod") + ", " +
				"Customer Name: " + persistentState.getProperty("CustomerName") + ", " +
				"Customer Phone: " + persistentState.getProperty("CustomerPhone") + ", " +
				"Customer Email: " + persistentState.getProperty("CustomerEmail") + ", " +
				"Transaction Date: " + persistentState.getProperty("TransactionDate") + ", " +
				"Transaction Time: " + persistentState.getProperty("TransactionTime") + ", " +
				"Date Status Updated: " + persistentState.getProperty("DateStatusUpdated") + ".";
	}
	

	public String getTransactionId()
	{
		return persistentState.getProperty("ID");
	}
	
	public String getSessionID()
	{
		return persistentState.getProperty("SessionID");
	}
	
	public void setSessionID(String value)
	{
		persistentState.setProperty("SessionID", value);
	}
	
	public String getTransactionType()
	{
		return persistentState.getProperty("TransactionType");
	}
	
	public void setTransactionType(String value)
	{
		persistentState.setProperty("TransactionType", value);
	}
	
	public String getBarcode()
	{
		return persistentState.getProperty("Barcode");
	}
	public void setBarcode(String value)
	{
		persistentState.setProperty("Barcode", value);
	}
	
	public String getTransactionAmount()
	{
		return persistentState.getProperty("TransactionAmount");
	}
	public void setTransactionAmount(String value)
	{
		persistentState.setProperty("TransactionAmount", value);
	}
	
	public String getPaymentMethod()
	{
		return persistentState.getProperty("PaymentMethod");
	}
	public void setPaymentMethod(String value)
	{
		persistentState.setProperty("PaymentMethod", value);
	}
	
	public String getCustomerName()
	{
		return persistentState.getProperty("CustomerName");
	}
	public void setCustomerName(String value)
	{
		persistentState.setProperty("CustomerName", value);
	}
	
	public String getCustomerPhone()
	{
		return persistentState.getProperty("CustomerPhone");
	}
	public void setCustomerPhone(String value)
	{
		persistentState.setProperty("CustomerPhone", value);
	}
	
	public String getCustomerEmail()
	{
		return persistentState.getProperty("CustomerEmail");
	}
	public void setCustomerEmail(String value)
	{
		persistentState.setProperty("CustomerEmail", value);
	}
	
	public String getTransactionDate()
	{
		return persistentState.getProperty("TransactionDate");
	}
	public void setTransactionDate(String value)
	{
		persistentState.setProperty("TransactionDate", value);
	}
	
	public String getTransactionTime()
	{
		return persistentState.getProperty("TransactionTime");
	}

	public void setTransactionTime(String value)
	{
		persistentState.setProperty("TransactionTime", value);
	}
	
	public String getDateStatusUpdated()
	{
		return persistentState.getProperty("DateStatusUpdated");
	}
	public void setDateStatusUpdated(String value)
	{
		persistentState.setProperty("DateStatusUpdated", value);
	}
	
	public String getTroopId()
	{
		return persistentState.getProperty("TroopID");
	}

	public void setTroopId(String value)
	{
		persistentState.setProperty("TroopId", value);
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
	 * This method is needed solely to enable the Transaction information to be displayable in a table
	 *
	 */
	//--------------------------------------------------------------------------
	public Vector<String> getEntryListView()
	{
		Vector<String> v = new Vector<String>();

		v.addElement(persistentState.getProperty("ID"));
		v.addElement(persistentState.getProperty("SessionID"));
		v.addElement(persistentState.getProperty("TransactionType"));
		v.addElement(persistentState.getProperty("Barcode"));
		v.addElement(persistentState.getProperty("TransactionAmount"));
		v.addElement(persistentState.getProperty("PaymentMethod"));
		v.addElement(persistentState.getProperty("CustomerName"));
		v.addElement(persistentState.getProperty("CustomerPhone"));
		v.addElement(persistentState.getProperty("CustomerEmail"));
		v.addElement(persistentState.getProperty("TransactionDate"));
		v.addElement(persistentState.getProperty("TransactionTime"));
		v.addElement(persistentState.getProperty("DateStatusUpdated"));
		return v;
	}

}
