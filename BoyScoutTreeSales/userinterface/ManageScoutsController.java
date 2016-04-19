package userinterface;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class ManageScoutsController {

private TLC tlc; 
private MessageView statusLog;
private Stage myStage = MainStageContainer.getInstance();

@FXML
private Text manageScoutsTitle;
@FXML
private Button addScoutButton;
@FXML
private Button updateRemoveScoutButton;

@FXML
private Button backButton;

public void settlc(TLC tlc)
{
	this.tlc = tlc;
}

public void createAndShowAddScoutView() throws IOException
{
	
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(AddScoutController.class.getResource("AddScout.fxml"));
	loader.setResources(tlc.r);
    Parent root = loader.load();
    Scene scene = new Scene(root);

	myStage.setScene(scene);
	myStage.sizeToScene();
	AddScoutController controller = loader.getController();
	controller.setTlc(tlc);

}

public void CreateSearchScoutView() throws IOException
{
	
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(SearchScoutController.class.getResource("SearchScout.fxml"));
	loader.setResources(tlc.r);
    Parent root = loader.load();
    Scene scene = new Scene(root);

	myStage.setScene(scene);
	myStage.sizeToScene();
	SearchScoutController controller = loader.getController();
	controller.setTlc(tlc);

}

public void back()
{
	tlc.transactionDone();
}

}

