package userinterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import model.TLC;

public class updateTreeTypeView {
    
    @FXML
    private Button updateTreeTypeButton;
    @FXML
    private TextField barcodeInput;
    @FXML
    private TextArea descriptionInput;
    @FXML
    private TextField costInput;
    @FXML
    private TextField ChooseInput;
    @FXML
    private Label statusLog;
    @FXML
    private Label ChooseRecord;
    
    private TLC m_TLC;
    
    public updateTreeTypeView() {
    }
    
    @FXML
    private void initialize() {
    }
    
    public void setTLC(TLC tlc) {
        this.m_TLC = tlc;
    }
    
    @FXML
    private void updateTreeTypeButtonOnAction() {
    	
    }
    
    @FXML
    private void doneButtonOnAction() {
        this.m_TLC.createAndShowInventoryView();
    }
}
