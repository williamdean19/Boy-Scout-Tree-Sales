package userinterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.TLC;

public class removeTreeView {
    
    @FXML
    private Button doneButton;
    @FXML
    private Button removeTreeButton;
    @FXML
    private TextField barcodeInput;
    @FXML
    private Label statusLog;
    
    private TLC m_TLC;
    
    public removeTreeView() {
        
    }
    
    @FXML
    private void initialize() {
    }
    
    public void setTLC(TLC tlc) {
        this.m_TLC = tlc;
    }
    
    @FXML
    private void removeTreeButtonOnAction() {
    }
    
    @FXML
    private void doneButtonOnAction() {
        this.m_TLC.createAndShowInventoryView();
    }
    
}
