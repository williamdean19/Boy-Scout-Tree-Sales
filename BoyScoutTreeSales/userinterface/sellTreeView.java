package userinterface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.TLC;

public class sellTreeView {
    @FXML
    private TextField barcodeInput;
    @FXML
    private Button sellTreeButton;
    @FXML
    private Label statusLog;
    
    private TLC m_TLC;
    
    public sellTreeView() {
    }
    
    @FXML
    private void initialize() {
    }
    
    public void setTLC(TLC tlc) {
        this.m_TLC = tlc;
    }
    
    @FXML
    private void sellTreeButtonOnAction() {
    	String barcode = barcodeInput.getText();
    	this.m_TLC.createAndShowSecondSellATreeView(barcode);
    }
    
    @FXML
    private void doneButtonOnAction() {
        this.m_TLC.transactionDone();
    }
}
