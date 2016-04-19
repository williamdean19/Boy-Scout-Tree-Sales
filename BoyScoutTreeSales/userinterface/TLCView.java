
//specify the package
package userinterface;

import javafx.event.Event;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.TLC;

/** The class containing the TLCView */
//==============================================================
public class TLCView extends View
{

	private Stage myStage = MainStageContainer.getInstance();

	private Button sellTreeButton;
	private Button manageScoutsButton;
	private Button manageInventoryButton;
	private Button manageShiftButton;
	private Button englishButton;
	private Button frenchButton;
	private Button doneButton;
	private Text titleText;

	private TLC myTLC;
	ResourceBundle r;
	
	// For showing error message
	private MessageView statusLog;
	
	


	// constructor for this class -- takes a model object
	//----------------------------------------------------------
	public TLCView( TLC tlc)
	{
		
		super (tlc, "TLCView");
		
		myTLC = tlc;
		r = tlc.r;
		// create a container for showing the contents
		VBox container = new VBox(10);

		container.setPadding(new Insets(15, 5, 5, 5));
		container.setPrefWidth(800);
		container.setAlignment(Pos.CENTER);

		titleText = (Text) createTitle();
		
		// create a Node (Text) for showing the title
		container.getChildren().add(titleText);

		// create a Node (GridPane) for showing data entry fields
		container.getChildren().add(createFormContents());

		// Error message area
		container.getChildren().add(createStatusLog("                          "));

		getChildren().add(container);


		// STEP 0: Be sure you tell your model what keys you are interested in
		myModel.subscribe("LoginError", this);
	}

	// Create the label (Text) for the title of the screen
	//-------------------------------------------------------------
	private Node createTitle()
	{
		
		Text titleText = new Text(r.getString("title"));
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKGREEN);
		
	
		return titleText;
	}

	// Create the main form contents
	//-------------------------------------------------------------
	private GridPane createFormContents()
	{
		GridPane grid = new GridPane();
     	grid.setAlignment(Pos.CENTER);
    	grid.setHgap(10);
     	grid.setVgap(10);
     	grid.setPadding(new Insets(25, 25, 25, 25));

		// data entry fields
    	sellTreeButton = new Button(r.getString("sellTree"));
    	sellTreeButton.setOnAction(new EventHandler<ActionEvent>() {

        		     @Override
        		     public void handle(ActionEvent e) {
        		     	//myTLC.sellTree();
        		    	 
             	     }
         	});
    	grid.add(sellTreeButton, 0, 0);

    	manageScoutsButton = new Button(r.getString("manageScouts"));
    	manageScoutsButton.setOnAction(new EventHandler<ActionEvent>() {

        		     @Override
        		     public void handle(ActionEvent e) {
        		    	 
        		    	 try {
							myTLC.addScout();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        		     	   
             	     }
         	});
    	grid.add(manageScoutsButton, 0, 1);
    	

    	manageInventoryButton = new Button(r.getString("manageInventory"));
    	manageInventoryButton.setOnAction(new EventHandler<ActionEvent>() {

        		     @Override
        		     public void handle(ActionEvent e) {
        		    	 myTLC.createAndShowInventoryView();    
             	     }
         	});
    	grid.add(manageInventoryButton, 0, 2);
    	
    	manageShiftButton = new Button(r.getString("manageShift"));
    	manageShiftButton.setOnAction(new EventHandler<ActionEvent>() {

        		     @Override
        		     public void handle(ActionEvent e) {
        		     	//myLibrarian.createAndShowSearchView();   
             	     }
         	});
    	grid.add(manageShiftButton, 0, 3);
    	
    	
    	doneButton = new Button(r.getString("done"));
    	doneButton.setOnAction(new EventHandler<ActionEvent>() {

        		     @Override
        		     public void handle(ActionEvent e) {
        		     	processAction(e);    
             	     }
         	});
    	grid.add(doneButton, 0, 4);
    	
    	englishButton = new Button("EN");
    	englishButton.setOnAction(new EventHandler<ActionEvent>() {

        		     @Override
        		     public void handle(ActionEvent e) {
        		     	processAction(e);    
             	     }
         	});
    	grid.add(englishButton, 0, 5);
    	
    	frenchButton = new Button("FR");
    	frenchButton.setOnAction(new EventHandler<ActionEvent>() {

        		     @Override
        		     public void handle(ActionEvent e) {
        		     	processAction(e);    
             	     }
         	});
    	grid.add(frenchButton, 0, 6);


		return grid;
	}







	

	// Create the status log field
	//-------------------------------------------------------------
	private MessageView createStatusLog(String initialMessage)
	{

		statusLog = new MessageView(initialMessage);

		return statusLog;
	}




	// This method processes events generated from our GUI components.
	// Make the ActionListeners delegate to this method
	//THIS METHOD IS A HELPER ACTION
	//-------------------------------------------------------------
	public void processAction(Event evt)
	{
		// DEBUG: System.out.println("TellerView.actionPerformed()");
		if (evt.getSource() == doneButton)
		{
			myStage.close();
		}
		
		if (evt.getSource() == frenchButton)
		{
			Locale l = new Locale("fr", "FR");
			ResourceBundle r = ResourceBundle.getBundle("resources/Bundle", l);
			setTLCText(l, r);
		}
		
		if (evt.getSource() == englishButton)
		{
			Locale l = new Locale("en", "US");
			ResourceBundle r = ResourceBundle.getBundle("resources/Bundle", l);
			setTLCText(l, r);
		}
	}
	
	public void setTLCText(Locale lc, ResourceBundle rb)
	{
		titleText.setText(rb.getString("title"));
		sellTreeButton.setText(rb.getString("sellTree"));
		manageScoutsButton.setText(rb.getString("manageScouts"));
		manageInventoryButton.setText(rb.getString("manageInventory"));
		manageShiftButton.setText(rb.getString("manageShift"));
		doneButton.setText(rb.getString("done"));
	}

	/**
	 * Process userid and pwd supplied when Submit button is hit.
	 * Action is to pass this info on to the teller object
	 */
	//----------------------------------------------------------

	//---------------------------------------------------------
	@Override
	public void updateState(String key, Object value)
	{
		// STEP 6: Be sure to finish the end of the 'perturbation'
		// by indicating how the view state gets updated.
		if (key.equals("LoginError") == true)
		{
			// display the passed text
			displayErrorMessage((String)value);
		}

	}

	/**
	 * Display error message
	 */
	//----------------------------------------------------------
	public void displayErrorMessage(String message)
	{
		statusLog.displayErrorMessage(message);
	}

	/**
	 * Clear error message
	 */
	//----------------------------------------------------------
	public void clearErrorMessage()
	{
		statusLog.clearErrorMessage();
	}

	//-LIBRARIAN VIEW METHODS-----------------------------------

}

