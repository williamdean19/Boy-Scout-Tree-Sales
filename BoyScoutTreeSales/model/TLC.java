
//specify the package
package model;

//system imports
import java.util.Hashtable;


import java.util.Properties;

import javafx.stage.Stage;
import javafx.scene.Scene;

//project imports
import impresario.IModel;
import impresario.IView;
import impresario.ModelRegistry;
import event.Event;
import userinterface.MainStageContainer;
import userinterface.View;
import userinterface.WindowPosition;
import userinterface.TLCView;


/** The class containing the Tree Lot Coordinator(TLC)
 *   for the Boy Scout application */
//==============================================================
public class TLC implements IView,IModel
//This class implements all these interfaces (and does NOT extend 'EntityBase')
//because it does NOT play the role of accessing the back-end database tables.
//It only plays a front-end role. 'EntityBase' objects play both roles.
{
	// For Impresario
	private Properties dependencies;
	private ModelRegistry myRegistry;

	//private AccountHolder myAccountHolder;

	// GUI Components
	private Hashtable<String, Scene> myViews;
	private Stage	  	myStage;

	private String loginErrorMessage = "";
	private String transactionErrorMessage = "";

	// constructor for this class
	//----------------------------------------------------------
	public TLC()
	{
		myStage = MainStageContainer.getInstance();
		myViews = new Hashtable<String, Scene>();

		// STEP 3.1: Create the Registry object - if you inherit from
		// EntityBase, this is done for you. Otherwise, you do it yourself
		myRegistry = new ModelRegistry("TLC");
		if(myRegistry == null)
		{
			new Event(Event.getLeafLevelClassName(this), "TLC",
				"Could not instantiate Registry", Event.ERROR);
		}

		// STEP 3.2: Be sure to set the dependencies correctly
		setDependencies();

		// Set up the initial view
		createAndShowTLCView();
	}

	//-----------------------------------------------------------------------------------
	private void setDependencies()
	{
	
	}

	@Override
	public void stateChangeRequest(String key, Object value)
	{
		

		myRegistry.updateSubscribers(key, this);
	}


	//----------------------------------------------------------
		@Override
		public Object getState(String key)
		{
			return null;
		}

	/** Called via the IView relationship */
	//----------------------------------------------------------
	@Override
	public void updateState(String key, Object value)
	{
		// DEBUG System.out.println("Teller.updateState: key: " + key);

		stateChangeRequest(key, value);
	}



	/*TLC METHODS
	//------------------------------------------------------------
	public void createNewBook()
	{
		Book b = new Book(this);
		b.createAndShowBookView();
	}
	
	public void createNewPatron()
	{
		Patron p = new Patron(this);
		p.createAndShowPatronView();
	}
	
	public void searchBooks(String searchEntered)
	{
		BookCollection bc = new BookCollection(this);
		bc.findBooksWithTitleLike(searchEntered);
		bc.createAndShowBookCollectionView();
	}
	*/
	
	//CREATE AND SHOW TREE LOT COORDINATOR VIEW----------------------------
	private void createAndShowTLCView()
	{
		Scene currentScene = myViews.get("TLC");

		if (currentScene == null)
		{
			// create our initial view
			View newView = new TLCView(this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("TLCView", currentScene);
		}
				
		swapToView(currentScene);
		
	}
	
	/*
	public void createAndShowSearchView()
	{
		Scene currentScene = (Scene)myViews.get("SearchView");

		if (currentScene == null)
		{
			// create our initial view
			View newView = new SearchView(this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("BookCollectionView", currentScene);
		}
				
		swapToView(currentScene);
		
	}
	*/
	//---------------------------------------------------------
	public void transactionDone()
	{
		createAndShowTLCView();
	}

	/** Register objects to receive state updates. */
	//----------------------------------------------------------
	@Override
	public void subscribe(String key, IView subscriber)
	{
		// DEBUG: System.out.println("Cager[" + myTableName + "].subscribe");
		// forward to our registry
		myRegistry.subscribe(key, subscriber);
	}

	/** Unregister previously registered objects. */
	//----------------------------------------------------------
	@Override
	public void unSubscribe(String key, IView subscriber)
	{
		// DEBUG: System.out.println("Cager.unSubscribe");
		// forward to our registry
		myRegistry.unSubscribe(key, subscriber);
	}



	//-----------------------------------------------------------------------------
	public void swapToView(Scene newScene)
	{

		
		if (newScene == null)
		{
			System.out.println("Librarian.swapToView(): Missing view for display");
			new Event(Event.getLeafLevelClassName(this), "swapToView",
				"Missing view for display ", Event.ERROR);
			return;
		}

		myStage.setScene(newScene);
		myStage.sizeToScene();
		
			
		//Place in center
		WindowPosition.placeCenter(myStage);

	}

}

