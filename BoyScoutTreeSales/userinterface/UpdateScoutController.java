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


public class UpdateScoutController {
	
	private TLC tlc; 
	private Scout myScout;
	private MessageView statusLog;
	
	//Labels
	@FXML
	private Label updateScoutTitle;
	
    @FXML
    private Label IDLabel;
    @FXML
    private Label ID;
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

    
    public UpdateScoutController()
    {
    	
    }
    
    @FXML
    private void intialize()
    {
    }
    
    //This method passes in the TLC to the view
    public void setTlc(TLC tlc)
    {
    	this.tlc = tlc;
    }
    
    //This method passes in the scout to the view
    public void setScout(Scout s)
    {
    	this.myScout = s;

    }
    
    //This Method Initializes all the fields to the Objects 
    //Current Data
    public void initializeData()
    {
    	ID.setText(myScout.getScoutId());
    	firstNameField.setText(myScout.getFirstName());
    	middleInitialField.setText(myScout.getMiddleName());
    	lastNameField.setText(myScout.getLastName());
    	emailField.setText(myScout.getEmail());
    	phoneNumberField.setText(myScout.getPhoneNumber());
    	dateOfBirthField.setText(myScout.getDateOfBirth());
    	troopIDField.setText(myScout.getTroopId());
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
    	//Set scout info to updated Fields from GUI
    	
    	myScout.setFirstName(firstName);
    	
    	myScout.setMiddleName(middleInitial);
    	myScout.setLastName(lastName);   	
    	myScout.setDateOfBirth(DOB);   
    	myScout.setPhoneNumber(phoneNumber);
    	
    	myScout.setEmail(email);
    	
    	//myScout.setTroopId(troopID); //Crashes
    	
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    	Date date = new Date();
    	
    	myScout.setDateStatusUpdated(dateFormat.format(date));
	
    	
    	myScout.save(); //Update the scout in the database
    	
        messageText.setFill(Color.BLUE);
        messageText.setText("Scout Updated!");
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
