package userinterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import model.TLC;

public class addTreeView {
    
    @FXML
    private Button addTreeButton;
    @FXML
    private TextField barcodeInput;
    @FXML
    private TextArea notesInput;
    @FXML
    private Label statusLog;
    
    private TLC m_TLC;
    
    public addTreeView() {
        
    }
    
    @FXML
    private void initialize() {
        
    }
    
    public void setTLC(TLC tlc) {
        this.m_TLC = tlc;
    }
    
    @FXML
    private void addTreeButtonOnAction() {
        
    }
    
    @FXML
    private void doneButtonOnAction() {
        this.m_TLC.createAndShowInventoryView();
    }
}
