package model;

import database.Persistable;

import java.util.Properties;
import java.util.Vector;

public class SessionCollection extends EntityBase {

	private Properties mySchema;
	private Properties persistentState;
	private static final String myTableName = "Session";
	private Vector<Session> sessionCollection;
	private TLC myTLC;
	
	public SessionCollection()
	{
		super(myTableName);
		sessionCollection = new Vector<Session>();
	}
	
	public SessionCollection(TLC my_TLC)
	{
		super(myTableName);
		myTLC = my_TLC;
		sessionCollection = new Vector<Session>();
	}
	
	public void findOpenSessions()
	{
		String query = "SELECT * FROM " + myTableName + " WHERE ( EndTime = ' ')";
		
		Vector <Properties>allDataRetrieved = getSelectQueryResult(query);

		if (allDataRetrieved != null)
		{
			for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++)
			{
				Properties nextSessionData = (Properties)allDataRetrieved.elementAt(cnt);

				Session s = new Session(nextSessionData.getProperty("ID"));

				if (s != null)
				{
					sessionCollection.add(s);				
				}
			}
		}
		else
		{
			System.out.println("No matches found in database for given name");
		}
	}
	
	public Vector<Session> getSessionCollectionVector()
	{
		return sessionCollection;
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
