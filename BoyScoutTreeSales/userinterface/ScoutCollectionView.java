package userinterface;

// system imports
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ResourceBundle;
import java.util.Vector;
import java.util.Enumeration;



// project imports
import impresario.IModel;
import model.Scout;
import model.ScoutCollection;
import model.TLC;
//==============================================================================
public class ScoutCollectionView extends View
{
	protected TableView<ScoutTableModel> tableOfScouts;
	protected Button doneButton;
	protected Button deleteButton;
	protected Button updateButton;
	private ScoutCollection mySc;
	protected MessageView statusLog;
	ResourceBundle r;


	//--------------------------------------------------------------------------
	public ScoutCollectionView(ScoutCollection sc)
	{
		super(sc, "ScoutCollectionView");
		mySc = sc;
		r = sc.r;
		
		// create a container for showing the contents
		VBox container = new VBox(10);
		container.setPadding(new Insets(15, 5, 5, 5));
		

		// create our GUI components, add them to this panel
		container.getChildren().add(createTitle());
		container.getChildren().add(createFormContent());

		// Error message area
		container.getChildren().add(createStatusLog("                                            "));

		getChildren().add(container);
		
		populateFields();
	}

	//--------------------------------------------------------------------------
	protected void populateFields()
	{
		getEntryTableModelValues();
	}

	//--------------------------------------------------------------------------
	protected void getEntryTableModelValues()
	{
		
		ObservableList<ScoutTableModel> tableData = FXCollections.observableArrayList();
		try
		{
			ScoutCollection scoutCollection = (ScoutCollection)myModel.getState("ScoutList");

	 		Vector entryList = (Vector)scoutCollection.getState("Scouts");
			Enumeration entries = entryList.elements();

			while (entries.hasMoreElements() == true)
			{
				Scout nextScout = (Scout)entries.nextElement();
				Vector<String> view = nextScout.getEntryListView();

				// add this list entry to the list
				ScoutTableModel nextTableRowData = new ScoutTableModel(view);
				tableData.add(nextTableRowData);
				
			}
			
			tableOfScouts.setItems(tableData);
		}
		catch (Exception e) {//SQLException e) {
			// Need to handle this exception
		}
	}

	// Create the title container
	//-------------------------------------------------------------
	private Node createTitle()
	{
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);	

		Text titleText = new Text("");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setWrappingWidth(300);
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.BLACK);
		container.getChildren().add(titleText);
		
		return container;
	}

	// Create the main form content
	//-------------------------------------------------------------
	private VBox createFormContent()
	{
		VBox vbox = new VBox(10);

		GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
       	grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text prompt = new Text(r.getString("selectAScoutToUpdate"));
        prompt.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        prompt.setWrappingWidth(350);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

		tableOfScouts = new TableView<ScoutTableModel>();
		tableOfScouts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	
		TableColumn IDColumn = new TableColumn("ID") ;
		IDColumn.setMinWidth(30);
		IDColumn.setCellValueFactory(
	                new PropertyValueFactory<ScoutTableModel, String>("ID"));
		
		TableColumn LNColumn = new TableColumn(r.getString("lastName")) ;
		LNColumn.setMinWidth(75);
		LNColumn.setCellValueFactory(
	                new PropertyValueFactory<ScoutTableModel, String>("LastName"));
		
		TableColumn FNColumn = new TableColumn(r.getString("firstName")) ;
		FNColumn.setMinWidth(75);
		FNColumn.setCellValueFactory(
	                new PropertyValueFactory<ScoutTableModel, String>("FirstName"));
		
		TableColumn MNColumn = new TableColumn(r.getString("middleInitial")) ;
		MNColumn.setMinWidth(25);
		MNColumn.setCellValueFactory(
	                new PropertyValueFactory<ScoutTableModel, String>("MiddleName"));
		
		TableColumn DOBColumn = new TableColumn(r.getString("DOB")) ;
		DOBColumn.setMinWidth(75);
		DOBColumn.setCellValueFactory(
	                new PropertyValueFactory<ScoutTableModel, String>("DateOfBirth"));
		
		TableColumn PhoneColumn = new TableColumn(r.getString("phoneNumber")) ;
		PhoneColumn.setMinWidth(85);
		PhoneColumn.setCellValueFactory(
	                new PropertyValueFactory<ScoutTableModel, String>("PhoneNumber"));
		
		TableColumn EmailColumn = new TableColumn(r.getString("email")) ;
		EmailColumn.setMinWidth(100);
		EmailColumn.setCellValueFactory(
	                new PropertyValueFactory<ScoutTableModel, String>("Email"));
		
		TableColumn TroopIDColumn = new TableColumn(r.getString("troopID")) ;
		TroopIDColumn.setMinWidth(25);
		TroopIDColumn.setCellValueFactory(
	                new PropertyValueFactory<ScoutTableModel, String>("TroopID"));
		
		TableColumn StatusColumn = new TableColumn(r.getString("status")) ;
		StatusColumn.setMinWidth(50);
		StatusColumn.setCellValueFactory(
	                new PropertyValueFactory<ScoutTableModel, String>("Status"));
		
		TableColumn DSUColumn = new TableColumn(r.getString("updated")) ;
		DSUColumn.setMinWidth(75);
		DSUColumn.setCellValueFactory(
	                new PropertyValueFactory<ScoutTableModel, String>("DateStatusUpdated"));

		tableOfScouts.getColumns().addAll(IDColumn, LNColumn, 
				FNColumn, MNColumn, DOBColumn, PhoneColumn, EmailColumn,
				TroopIDColumn, StatusColumn, DSUColumn);

		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(600, 150); //original values 115 150
		
		scrollPane.setContent(tableOfScouts);

		doneButton = new Button(r.getString("back"));
 		doneButton.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		     public void handle(ActionEvent e) {
       		     mySc.done();
					
            	 }
        	});

		

		deleteButton = new Button(r.getString("delete"));
 		deleteButton.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		     public void handle(ActionEvent e) {
       		     //Here we get the selected row as the a table model
       		     //And can get 
       		    ScoutTableModel selection = tableOfScouts.getSelectionModel().getSelectedItem();
       		    String ScoutID = selection.getID();
       		    Scout S = new Scout(ScoutID);
       		    S.delete();
       		    displayMessage("Scout Marked Inactive");
       		    
            	 }
        	});
 		
		updateButton = new Button(r.getString("updateScout"));
 		updateButton.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		     public void handle(ActionEvent e) {
            		ScoutTableModel selection = tableOfScouts.getSelectionModel().getSelectedItem();
               		String ScoutID = selection.getID();
               		Scout S = new Scout(ScoutID);
               		mySc.createAndShowUpdateScoutView(S);

            	 }
        	});		
 		
		HBox btnContainer = new HBox(100);
		btnContainer.setAlignment(Pos.CENTER);
		btnContainer.getChildren().add(doneButton);
		btnContainer.getChildren().add(deleteButton);
		btnContainer.getChildren().add(updateButton);
		
		
		vbox.getChildren().add(grid);
		vbox.getChildren().add(scrollPane);
		vbox.getChildren().add(btnContainer);
	
		return vbox;
	}

	

	//--------------------------------------------------------------------------
	public void updateState(String key, Object value)
	{
	}

	//--------------------------------------------------------------------------
	

	//--------------------------------------------------------------------------
	protected MessageView createStatusLog(String initialMessage)
	{
		statusLog = new MessageView(initialMessage);

		return statusLog;
	}


	/**
	 * Display info message
	 */
	//----------------------------------------------------------
	public void displayMessage(String message)
	{
		statusLog.displayMessage(message);
	}

	/**
	 * Clear error message
	 */
	//----------------------------------------------------------
	public void clearErrorMessage()
	{
		statusLog.clearErrorMessage();
	}
	/*
	//--------------------------------------------------------------------------
	public void mouseClicked(MouseEvent click)
	{
		if(click.getClickCount() >= 2)
		{
			processAccountSelected();
		}
	}
   */
	
}
