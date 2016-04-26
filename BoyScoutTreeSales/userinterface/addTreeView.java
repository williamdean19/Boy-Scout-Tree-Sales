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
    private Button doneButton;
    @FXML
    private TextField barcodeInput;
    @FXML
    private TextField notesInput;
    @FXML
    private Label barcodeLabel;
    @FXML
    private Label notesLabel;
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
    	String barcode = barcodeInput.getText();
    	String notes = notesInput.getText();
    	String prefix  = barcode.substring(0, 2);
    	System.out.println("Barcode = " + barcode);
    	System.out.println("Prefix = " + prefix);
        
    }
    
    @FXML
    private void doneButtonOnAction() {
        this.m_TLC.createAndShowInventoryView();
    }
}
