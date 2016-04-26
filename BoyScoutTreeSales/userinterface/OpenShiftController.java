package userinterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;





import java.util.Vector;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Scout;
import model.ScoutCollection;
import model.Session;
import model.SessionCollection;
import model.Shift;
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


public class OpenShiftController {
	
	private TLC tlc; 
	private MessageView statusLog;
	private Vector<Scout> scoutCollection;
	
	//Labels
	@FXML
	private Text shiftInfoPrompt;
	@FXML
	private Text scoutInfoPrompt;
	
	//Shift Info Label
    @FXML
    private Label dateLabel;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Label startCashLabel;
    @FXML
    private Label notesLabel;  
    //Shift Info Fields
    @FXML
    private TextField dateField;
    @FXML
    private TextField startTimeField;
    @FXML
    private TextField startCashField;
    @FXML
    private TextField notesField;
    
    //Scout Staffing section
    @FXML
    private Label scoutLabel;
    @FXML
    private Label companionLabel;
    @FXML
    private ComboBox scout1;
    @FXML
    private ComboBox scout2;
    @FXML
    private ComboBox scout3;
    @FXML
    private ComboBox scout4;
    @FXML
    private TextField companion1;
    @FXML
    private TextField companion2;
    @FXML
    private TextField companion3;
    @FXML
    private TextField companion4;
    
    
    @FXML
    private Text messageText;
    
    //Buttons

    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;

    
    public OpenShiftController()
    {

    }
    
    @FXML
    private void intialize()
    {

    }
    
    public void setTLC(TLC tlc)
    {
    	this.tlc = tlc;
    }
    
    public void populateComboBoxes()
    {
    	//Add Null first slot to boxes
		scout1.getItems().add("");
		scout2.getItems().add("");
		scout3.getItems().add("");
		scout4.getItems().add("");
		
    	//Get All Scouts and 
    	ScoutCollection sc = new ScoutCollection();
    	sc.getAllScouts();
    	scoutCollection = sc.getScoutCollectionVector();
    	Iterator it = scoutCollection.iterator();

    	
    	while (it.hasNext())
    	{
    		Scout S = (Scout)it.next();
    		scout1.getItems().add(S.getScoutId()+"-"+S.getFirstName()+" " + S.getLastName());
    		scout2.getItems().add(S.getScoutId()+"-"+S.getFirstName()+" " + S.getLastName());
    		scout3.getItems().add(S.getScoutId()+"-"+S.getFirstName()+" " + S.getLastName());
    		scout4.getItems().add(S.getScoutId()+"-"+S.getFirstName()+" " + S.getLastName());
    		   		
    	}

    }
    
	private MessageView createStatusLog(String initialMessage)
	{

		statusLog = new MessageView(initialMessage);

		return statusLog;
	}
    
    public void getShiftInfo(){
   	
  
    String startDate = dateField.getText();
    String startTime = startTimeField.getText();;
    String startCash = startCashField.getText();
    String notes = notesField.getText();
    
  //Check for Blank Fields
    if(startDate.equals("") || startTime.equals("") 
    	|| startCash.equals(""))
    {
    messageText.setFill(Color.RED);
    messageText.setText("Error in input fields");
    }
    else
    {
		Properties openSessionProps = new Properties();

		openSessionProps.setProperty("StartDate", startDate);
		openSessionProps.setProperty("StartTime", startTime);
		openSessionProps.setProperty("EndTime", "");
		openSessionProps.setProperty("StartingCash", startCash);
		openSessionProps.setProperty("EndingCash", "");
		openSessionProps.setProperty("TotalCheckTransactionAmount", "");
		openSessionProps.setProperty("Notes", notes);

		Session session = new Session(openSessionProps);
		session.save();//will have to check for open sessions first below
		/*
		SessionCollection sessionCollection = new SessionCollection();
		sessionCollection.findOpenSessions();
		
		if(sessionCollection.getSessionCollectionVector().size() == 0)
		{
			System.out.println("The number of open sessions is: " + sessionCollection.getSessionCollectionVector().size());
			session.save();
			System.out.println("The session ID is: " + session.getSessionId());
			addShifts(session);
		}
		else
		{
			System.out.println("The number of open sessions is: " + sessionCollection.getSessionCollectionVector().size());
			System.out.println("There is an open session, please close the current session before beginning a new one.");
		messageText.setFill(Color.RED);
        messageText.setText("ERROR: There is a session currently open");
		}
		*/
		addShifts(session);

        messageText.setFill(Color.BLUE);
        messageText.setText("Shift Added!");
    }
   
    }
    

    public void addShifts(Session session)
    {
		// We make a new shift for every scout that is working
    	//get all scouts from combo boxes
    	String selectedScout1 = (String)scout1.getSelectionModel().getSelectedItem().toString();
    	String selectedScout2 = (String)scout2.getSelectionModel().getSelectedItem().toString();
    	String selectedScout3 = (String)scout3.getSelectionModel().getSelectedItem().toString();
    	String selectedScout4 = (String)scout4.getSelectionModel().getSelectedItem().toString();
    	
    	if (selectedScout1 != "")
    	{
    	String scoutID  = selectedScout1.substring(0, 2);  
  		Properties shiftProps = new Properties();
  		shiftProps.setProperty("SessionID", session.getSessionId());
  		shiftProps.setProperty("ScoutID", scoutID);
  		shiftProps.setProperty("CompanionName", companion1.getText());
  		shiftProps.setProperty("StartTime", session.getStartTime());
  		shiftProps.setProperty("EndTime", " ");
  		shiftProps.setProperty("CompanionHours", "");
  		Shift sh1 = new Shift(shiftProps);
  		sh1.save();
    	}
    	if (selectedScout2 != "")
    	{
    	String scoutID  = selectedScout2.substring(0, 2);  
  		Properties shiftProps = new Properties();
  		shiftProps.setProperty("SessionID", session.getSessionId());
  		shiftProps.setProperty("ScoutID", scoutID);
  		shiftProps.setProperty("CompanionName", companion2.getText());
  		shiftProps.setProperty("StartTime", session.getStartTime());
  		shiftProps.setProperty("EndTime", " ");
  		shiftProps.setProperty("CompanionHours", "");
  		Shift sh2 = new Shift(shiftProps);
  		sh2.save();
    	}
    	if (selectedScout3 != "")
    	{
    	String scoutID  = selectedScout3.substring(0, 2);  
  		Properties shiftProps = new Properties();
  		shiftProps.setProperty("SessionID", session.getSessionId());
  		shiftProps.setProperty("ScoutID", scoutID);
  		shiftProps.setProperty("CompanionName", companion3.getText());
  		shiftProps.setProperty("StartTime", session.getStartTime());
  		shiftProps.setProperty("EndTime", " ");
  		shiftProps.setProperty("CompanionHours", "");
  		Shift sh3 = new Shift(shiftProps);
  		sh3.save();
    	}
    	if (selectedScout4 != "")
    	{
    	String scoutID  = selectedScout4.substring(0, 2);  
  		Properties shiftProps = new Properties();
  		shiftProps.setProperty("SessionID", session.getSessionId());
  		shiftProps.setProperty("ScoutID", scoutID);
  		shiftProps.setProperty("CompanionName", companion4.getText());
  		shiftProps.setProperty("StartTime", session.getStartTime());
  		shiftProps.setProperty("EndTime", " ");
  		shiftProps.setProperty("CompanionHours", "");
  		Shift sh4 = new Shift(shiftProps);
  		sh4.save();
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
