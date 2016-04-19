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
import javafx.stage.Stage;
import model.TLC;

/**
 *
 * @author Lucas
 */
public class manageTreesView {
   
    @FXML
    private Button addTree;
    @FXML
    private Button updateTree;
    @FXML
    private Button removeTree;
    @FXML
    private Button goBack;
    
    private TLC m_TLC;
    private Stage myStage;
    
    public manageTreesView() {
        this.myStage = MainStageContainer.getInstance();
    }
    
    @FXML
    private void initialize() {
    }
    
    public void setTLC(TLC tlc) {
        this.m_TLC = tlc;
    }
    
    @FXML
    private void backButtonOnAction() {
        this.m_TLC.transactionDone();
    }
    
    @FXML
    private void addTreeButtonOnAction() {
        this.createAndShowAddTreeView();
    }
    
    private void createAndShowAddTreeView() {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(addTreeView.class.getResource("addTreeFXML.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
		myStage.setScene(scene);
		myStage.sizeToScene();
                
                addTreeView view = loader.getController();
                view.setTLC(m_TLC);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    @FXML
    private void updateTreeButtonOnAction() {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(updateTreeView.class.getResource("updateTreeFXML.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
		myStage.setScene(scene);
		myStage.sizeToScene();
                
                updateTreeView view = loader.getController();
                view.setTLC(m_TLC);
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }
    
    @FXML
    private void removeTreeButtonOnAction() {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(removeTreeView.class.getResource("removeTreeFXML.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
		myStage.setScene(scene);
		myStage.sizeToScene();
                
                removeTreeView view = loader.getController();
                view.setTLC(m_TLC);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    public void transactionDone() {
        this.m_TLC.transactionDone();
    }
    
}
