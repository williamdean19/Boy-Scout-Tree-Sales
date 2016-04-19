/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.TLC;

/**
 *
 * @author gaetanrd
 */
public class manageTreeTypesView {
    
    
    @FXML
    private Button addTreeTypeButton;
    @FXML
    private Button updateTreeTypeButton;
    @FXML
    private Button BackButton;
    
    private TLC m_TLC;
    
    public manageTreeTypesView() {
    }
    
    @FXML
    private void initialize() {
    }
    
    public void setTLC(TLC tlc) {
        this.m_TLC = tlc;
    }
    
    @FXML
    private void addTreeTypeButtonOnAction() {
        try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(addTreeTypeView.class.getResource("addTreeTypeFXML.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
		MainStageContainer.getInstance().setScene(scene);
		MainStageContainer.getInstance().sizeToScene();
                
                addTreeTypeView view = loader.getController();
                view.setTLC(this.m_TLC);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    @FXML
    private void updateTreeTypeButtonOnAction() {
    	
    	m_TLC.searchTreeTypes();
    }
    @FXML
    private void BackButtonOnAction() {
        this.m_TLC.transactionDone();
    }
}
