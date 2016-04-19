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


public class SearchScoutController {
	
	private TLC tlc; 
	private MessageView statusLog;
	private Stage myStage = MainStageContainer.getInstance();
	
	//Labels
	@FXML
	private Text searchScoutTitle;
	
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;

    //Text Fields
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    
    @FXML
    private Text messageText;
    
    //Buttons

    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;

    
    public void setTlc(TLC tlc)
    {
    	this.tlc = tlc;
    }

    
    public void searchScout(){
   	
  
    String firstName = firstNameField.getText();
    String lastName = lastNameField.getText();
   

  //Check for Blank Fields
    if(firstName.equals("") || lastName.equals(""))
    {
    messageText.setFill(Color.RED);
    messageText.setText("Error in input fields");
    }
    else
    {	
    	tlc.searchScouts(firstName, lastName);
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
	

}
