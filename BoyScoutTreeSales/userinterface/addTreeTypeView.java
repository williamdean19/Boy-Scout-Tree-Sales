package userinterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Scout;
import model.TLC;
import model.TreeType;

public class addTreeTypeView {
    
    @FXML
    private Button addTreeTypeButton;
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
    private Text addTreeTypeTitle;
    @FXML
    private Label statusLog;
    @FXML
    private Text messageText;
    
    private TLC m_TLC;
    
    public addTreeTypeView() {
    }
    
    @FXML
    private void initialize() {
    }
    
    public void setTLC(TLC tlc) {
        this.m_TLC = tlc;
    }
    
    @FXML
    private void addTreeTypeButtonOnAction() {
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
        	
        	Properties treeTypeInsert = new Properties();
        	treeTypeInsert.setProperty("TypeDescription", TypeDescription);
        	treeTypeInsert.setProperty("Cost", Cost);
        	treeTypeInsert.setProperty("BarcodePrefix", BarcodePrefix);
        	        	
        	TreeType tt = new TreeType(treeTypeInsert); //Create TT with Properties Obj
        	tt.save(); //Save the scout in the database
        	
            messageText.setFill(Color.BLUE);
            messageText.setText("TreeType Inserted!");
        }
    	
    }
    
    @FXML
    private void doneButtonOnAction() {
        this.m_TLC.createAndShowInventoryView();
    }
}
