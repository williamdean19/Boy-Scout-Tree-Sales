package userinterface;

import java.util.Properties;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Scout;
import model.TLC;
import model.TreeType;

public class UpdateTreeTypeController {
	 
    @FXML
    private Button updateTreeTypeButton;
    @FXML
    private TextField barcodeInput;
    @FXML
    private TextField descriptionInput;
    @FXML
    private TextField costInput;
    
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label costLabel;
    @FXML
    private Label barcodeLabel;
    @FXML
    private Text updateTreeTypeTitle;
    @FXML
    private Label statusLog;
    @FXML
    private Text messageText;
    
    private TLC m_TLC;
    private TreeType myTreeType;
    
    public UpdateTreeTypeController() 
    {
    	
    }
    
    @FXML
    private void initialize() {
    }
    
    public void setTLC(TLC tlc) {
        this.m_TLC = tlc;
    }
    
    //This method passes in the Tree Type to the view
    public void setTreeType(TreeType tt)
    {
    	this.myTreeType = tt;

    }
    
    //This Method Initializes all the fields to the Objects 
    //Current Data
    public void initializeData()
    {
    	
    	descriptionInput.setText(myTreeType.getTypeDescription());
    	costInput.setText(myTreeType.getCost());
    	barcodeInput.setText(myTreeType.getBarcodePrefix());
    }
    
    @FXML
    private void updateTreeTypeButtonOnAction() {
    	String TypeDescription = descriptionInput.getText();
    	String Cost = costInput.getText();
    	String BarcodePrefix = barcodeInput.getText();
    	
    	//Check for Blank Fields
        if(TypeDescription.equals("") || Cost.equals("") || BarcodePrefix.equals(""))
        {
        messageText.setFill(Color.RED);
        messageText.setText("Error in input fields");
        }
        else
        {
        	
        	myTreeType.setTypeDescription(TypeDescription);
        	myTreeType.setCost(Cost);
        	myTreeType.setBarcodePrefix(BarcodePrefix);
         	
        	myTreeType.save(); //Save the TreeType in the database
        	
            messageText.setFill(Color.BLUE);
            messageText.setText("TreeType Updated!");
        }
    	
    }
    
    @FXML
    private void doneButtonOnAction() {
        this.m_TLC.createAndShowInventoryView();
    }

}
