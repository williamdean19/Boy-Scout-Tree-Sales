package userinterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;



import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Scout;
import model.TLC;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class AddScoutController {
	
	private TLC tlc; 
	private MessageView statusLog;
	
	//Labels
	@FXML
	private Label addScoutTitle
	;
	
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label middleInitialLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label dateOfBirthLabel;  
    @FXML
    private Label phoneNumberLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label troopIDLabel;
    
    //Text Fields
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField middleInitialField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField dateOfBirthField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField troopIDField;
    
    @FXML
    private Text messageText;
    
    //Buttons

    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;

    
    public AddScoutController()
    {
    	
    }
    
    @FXML
    private void intialize()
    {
    	
    }
    
    public void setTlc(TLC tlc)
    {
    	this.tlc = tlc;
    }
    
	private MessageView createStatusLog(String initialMessage)
	{

		statusLog = new MessageView(initialMessage);

		return statusLog;
	}
    
    public void getScoutInfo(){
   	
  
    String firstName = firstNameField.getText();
    String middleInitial = middleInitialField.getText();
    String lastName = lastNameField.getText();
    String DOB = dateOfBirthField.getText();
    String phoneNumber = phoneNumberField.getText();
    String email = emailField.getText();
    String troopID = troopIDField.getText();
    
  //Check for Blank Fields
    if(firstName.equals("") || middleInitial.equals("") || lastName.equals("")
    	|| DOB.equals("") || phoneNumber.equals("") || email.equals("")
    	|| troopID.equals(""))
    {
    messageText.setFill(Color.RED);
    messageText.setText("Error in input fields");
    }
    else
    {
    	
    	Properties scoutInsert = new Properties();
    	scoutInsert.setProperty("LastName", lastName);
    	scoutInsert.setProperty("FirstName", firstName);
    	scoutInsert.setProperty("MiddleName", middleInitial);
    	scoutInsert.setProperty("DateOfBirth", DOB);
    	scoutInsert.setProperty("PhoneNumber", phoneNumber);
    	scoutInsert.setProperty("Email", email);
    	scoutInsert.setProperty("TroopID", troopID);
    	scoutInsert.setProperty("Status", "Active");
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    	Date date = new Date();
    	
    	scoutInsert.setProperty("DateStatusUpdated", dateFormat.format(date));
    	
    	Scout S = new Scout(scoutInsert); //Create Scout with Properties Obj
    	S.save(); //Save the scout in the database
    	
        messageText.setFill(Color.BLUE);
        messageText.setText("Scout Inserted!");
    }
   
  
    }
    
    public void back()
    {
    	tlc.transactionDone();
    }
    
    
	public void displayErrorMessage(String message)
	{
		statusLog.displayErrorMessage(message);
	}
	
	/**
	 * Display message
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
