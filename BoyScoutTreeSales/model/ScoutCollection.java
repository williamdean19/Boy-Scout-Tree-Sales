package model;

import database.Persistable;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.scene.Scene;
import userinterface.ScoutCollectionView;
import userinterface.View;
import userinterface.ViewFactory;

public class ScoutCollection extends EntityBase {

	private Properties mySchema;
	private Properties persistentState;
	private static final String myTableName = "Scout";
	private Vector<Scout> scoutCollection;
	private TLC myTLC;
	public ResourceBundle r;

	public ScoutCollection()
	{
		super(myTableName);
		scoutCollection = new Vector<Scout>();
	}
	
	public ScoutCollection(TLC my_TLC)
	{
		super(myTableName);
		myTLC = my_TLC;
		r = my_TLC.r;
		scoutCollection = new Vector<Scout>();
	}
	
//-----FOR SCOUT COLLECTION VIEW----AL	
	public Object getState(String key)
	{
		if (key.equals("Scouts"))
			return scoutCollection;
		else
		if (key.equals("ScoutList"))
			return this;
		return null;
	}
	
	public void done()
	{
		myTLC.transactionDone();
		
	}
	
	public void findScoutsMatchingFirstNameLastName(String fn, String ln)
	{
		String query = "SELECT * FROM " + myTableName + " WHERE (FirstName = '" + fn + "' AND LastName = '" + ln + "')";


		Vector <Properties>allDataRetrieved = getSelectQueryResult(query);

		if (allDataRetrieved != null)
		{
			for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++)
			{
				Properties nextScoutData = (Properties)allDataRetrieved.elementAt(cnt);

				Scout s = new Scout(nextScoutData.getProperty("ID"));

				if (s != null)
				{
					scoutCollection.add(s);				
				}
			}
		}
		else
		{
			System.out.println("No matches found in database for given name");
		}
	}
	
	public Vector getScoutCollectionVector()
	{
		return scoutCollection;
	}

	protected void initializeSchema(String tableName) {
		if (mySchema == null) {
			mySchema = getSchemaInfo(tableName);
		}
	}

	public void display()
	{
		for (int cnt = 0; cnt < scoutCollection.size(); cnt++)
		{
			System.out.println(scoutCollection.get(cnt));
		}
	}



	@Override
	public void stateChangeRequest(String key, Object value) {
		// TODO Auto-generated method stub
		
	}
	
	protected void createAndShowView()
	{

		
		Scene currentScene = (Scene)myViews.get("ScoutCollectionVView");

		if (currentScene == null)
		{
			// create our initial view
			View newView = new ScoutCollectionView(this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("ScoutCollectionView", currentScene);
		}
				
		swapToView(currentScene);
		
	}
	
	public void createAndShowUpdateScoutView(Scout S)
	{
		try {
			myTLC.createAndShowUpdateScoutView(S);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

