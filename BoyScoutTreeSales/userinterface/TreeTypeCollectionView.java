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
import model.TreeType;
import model.TreeTypeCollection;
import model.TLC;
//==============================================================================
public class TreeTypeCollectionView extends View
{
	protected TableView<TreeTypeTableModel> tableOfTreeTypes;
	protected Button doneButton;
	protected Button updateButton;
	private TreeTypeCollection myTtc;
	protected MessageView statusLog;
	public ResourceBundle r;
	


	//--------------------------------------------------------------------------
	public TreeTypeCollectionView(TreeTypeCollection ttc)
	{
		super(ttc, "TreeTypeCollectionView");
		myTtc = ttc;
		r = ttc.r;
		
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
		
		ObservableList<TreeTypeTableModel> tableData = FXCollections.observableArrayList();
		try
		{
			TreeTypeCollection treeTypeCollection = (TreeTypeCollection)myModel.getState("TreeTypeList");

	 		Vector entryList = (Vector)treeTypeCollection.getState("TreeTypes");
			Enumeration entries = entryList.elements();

			while (entries.hasMoreElements() == true)
			{
				TreeType nextTreeType = (TreeType)entries.nextElement();
				Vector<String> view = nextTreeType.getEntryListView();

				// add this list entry to the list
				TreeTypeTableModel nextTableRowData = new TreeTypeTableModel(view);
				tableData.add(nextTableRowData);
				
			}
			
			tableOfTreeTypes.setItems(tableData);
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

		Text titleText = new Text(" ");
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
        
        Text prompt = new Text(r.getString("selectATreeTypeToUpdate"));
        prompt.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        prompt.setWrappingWidth(350);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

		tableOfTreeTypes = new TableView<TreeTypeTableModel>();
		tableOfTreeTypes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	
		TableColumn IDColumn = new TableColumn("ID") ;
		IDColumn.setMinWidth(50);
		IDColumn.setCellValueFactory(
	                new PropertyValueFactory<TreeTypeTableModel, String>("ID"));
		
		TableColumn TDColumn = new TableColumn(r.getString("typeDescription")) ;
		TDColumn.setMinWidth(250);
		TDColumn.setCellValueFactory(
	                new PropertyValueFactory<TreeTypeTableModel, String>("TypeDescription"));
		
		TableColumn CostColumn = new TableColumn(r.getString("cost")) ;
		CostColumn.setMinWidth(100);
		CostColumn.setCellValueFactory(
	                new PropertyValueFactory<TreeTypeTableModel, String>("Cost"));
		
		TableColumn BCColumn = new TableColumn(r.getString("barcodePrefix")) ;
		BCColumn.setMinWidth(200);
		BCColumn.setCellValueFactory(
	                new PropertyValueFactory<TreeTypeTableModel, String>("BarcodePrefix"));
		
	
		tableOfTreeTypes.getColumns().addAll(IDColumn, TDColumn, 
				CostColumn, BCColumn);

		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(600, 150); //original values 115 150
		
		scrollPane.setContent(tableOfTreeTypes);

		doneButton = new Button(r.getString("back"));
 		doneButton.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		     public void handle(ActionEvent e) {
       		     myTtc.done();
					
            	 }
        	});

 		
		updateButton = new Button(r.getString("updateTreeType"));
 		updateButton.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		     public void handle(ActionEvent e) {
            		TreeTypeTableModel selection = tableOfTreeTypes.getSelectionModel().getSelectedItem();
               		String TreeTypeID = selection.getID();
               		TreeType tt = new TreeType(TreeTypeID);
               		myTtc.createAndShowUpdateTreeTypeView(tt);

            	 }
        	});		
 		
		HBox btnContainer = new HBox(100);
		btnContainer.setAlignment(Pos.CENTER);
		btnContainer.getChildren().add(doneButton);
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

	
}
