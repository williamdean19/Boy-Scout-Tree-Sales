package model;

import database.Persistable;

import java.util.Properties;
import java.util.Vector;

public class ShiftCollection extends EntityBase {

	private Properties mySchema;
	private Properties persistentState;
	private static final String myTableName = "Shift";
	private Vector<Shift> shiftCollection;
	private TLC myTLC;
	
	public ShiftCollection()
	{
		super(myTableName);
		shiftCollection = new Vector<Shift>();
	}
	
	public ShiftCollection(TLC my_TLC)
	{
		super(myTableName);
		myTLC = my_TLC;
		shiftCollection = new Vector<Shift>();
	}
	
	public void findOpenShifts()
	{
		String query = "SELECT * FROM " + myTableName + " WHERE ( EndTime = ' ')";
		
		Vector <Properties>allDataRetrieved = getSelectQueryResult(query);

		if (allDataRetrieved != null)
		{
			for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++)
			{
				Properties nextShiftData = (Properties)allDataRetrieved.elementAt(cnt);

				Shift s = new Shift(nextShiftData.getProperty("ID"));

				if (s != null)
				{
					shiftCollection.add(s);				
				}
			}
		}
		else
		{
			System.out.println("No matches found in database for given name");
		}
	}
	
	public Vector<Shift> getShiftCollectionVector()
	{
		return shiftCollection;
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
