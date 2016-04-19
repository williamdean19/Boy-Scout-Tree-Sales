package model;

import database.Persistable;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.scene.Scene;
import userinterface.ScoutCollectionView;
import userinterface.TreeTypeCollectionView;
import userinterface.View;
import userinterface.ViewFactory;

public class TreeTypeCollection extends EntityBase {

	private Properties mySchema;
	private Properties persistentState;
	private static final String myTableName = "TreeType";
	private Vector<TreeType> treeTypeCollection;
	private TLC myTLC;
	public ResourceBundle r;

	public TreeTypeCollection()
	{
		super(myTableName);
		treeTypeCollection = new Vector<TreeType>();
	}
	
	public TreeTypeCollection(TLC my_TLC)
	{
		super(myTableName);
		myTLC = my_TLC;
		r = my_TLC.r;
		treeTypeCollection = new Vector<TreeType>();
	}
	
//-----FOR TREE TYPE COLLECTION VIEW----AL	
	public Object getState(String key)
	{
		if (key.equals("TreeTypes"))
			return treeTypeCollection;
		else
		if (key.equals("TreeTypeList"))
			return this;
		return null;
	}
	
	public void done()
	{
		myTLC.transactionDone();
		
	}
	
	public void findAllTreeTypes()
	{
		String query = "SELECT * FROM " + myTableName;


		Vector <Properties>allDataRetrieved = getSelectQueryResult(query);

		if (allDataRetrieved != null)
		{
			for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++)
			{
				Properties nextTreeTypeData = (Properties)allDataRetrieved.elementAt(cnt);

				TreeType tt = new TreeType(nextTreeTypeData.getProperty("ID"));

				if (tt != null)
				{
					treeTypeCollection.add(tt);				
				}
			}
		}
		else
		{
			System.out.println("No matches found in database for given name");
		}
	}
	
	public Vector getTreeTypeCollectionVector()
	{
		return treeTypeCollection;
	}

	protected void initializeSchema(String tableName) {
		if (mySchema == null) {
			mySchema = getSchemaInfo(tableName);
		}
	}

	public void display()
	{
		for (int cnt = 0; cnt < treeTypeCollection.size(); cnt++)
		{
			System.out.println(treeTypeCollection.get(cnt));
		}
	}



	@Override
	public void stateChangeRequest(String key, Object value) {
		// TODO Auto-generated method stub
		
	}
	
	protected void createAndShowView()
	{

		
		Scene currentScene = (Scene)myViews.get("TreeTypeCollectionView");

		if (currentScene == null)
		{
			// create our initial view
			View newView = new TreeTypeCollectionView(this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("TreeTypeCollectionView", currentScene);
		}
				
		swapToView(currentScene);
		
	}
	

	public void createAndShowUpdateTreeTypeView(TreeType tt)
	{
		try {
			myTLC.createAndShowUpdateTreeTypeView(tt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

