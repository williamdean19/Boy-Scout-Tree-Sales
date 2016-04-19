package userinterface;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import model.TLC;

public class manageInventoryView {
    
    @FXML
    private Button manageTrees;
    @FXML
    private Button doneButton;
    @FXML
    private Button manageTreeTypes;
    
    private TLC m_TLC;
    public ResourceBundle r;
    
    public manageInventoryView() {
        
    }
    
    @FXML
    private void initialize() {
        
    }
    
    public void setTLC(TLC tlc) {
        this.m_TLC = tlc;
        r = tlc.r;
    }
    
    @FXML
    private void manageTreesButtonOnAction() {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(manageTreesView.class.getResource("manageTreesFXML.fxml"));
                loader.setResources(r);
                Parent root = loader.load();
                Scene scene = new Scene(root);
		MainStageContainer.getInstance().setScene(scene);
		MainStageContainer.getInstance().sizeToScene();
                
                manageTreesView view = loader.getController();
                view.setTLC(m_TLC);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    @FXML
    private void managesTreeTypesButtonOnAction() {
    	
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(manageTreeTypesView.class.getResource("manageTreeTypesFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
	MainStageContainer.getInstance().setScene(scene);
	MainStageContainer.getInstance().sizeToScene();
            
		manageTreeTypesView view = loader.getController();
            view.setTLC(m_TLC);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @FXML
    private void doneButtonOnAction() {
        this.m_TLC.transactionDone();
    }
}
