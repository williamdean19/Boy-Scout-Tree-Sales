package userinterface;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.TLC;

public class sellATreeSecondView {
    
    @FXML
    private Label treeTypeLbl;
    @FXML
    private Label treeInfosLbl;
    @FXML
    private Label treePriceLbl;
    @FXML
    private TextField finalPriceField;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField customerEmailField;
    @FXML
    private TextField customerPhoneField;
    @FXML
    private TextField transactionAmoutField;
    @FXML
    private ChoiceBox paymentMethodChoice;
    @FXML
    private Button submitBtn;
    @FXML
    private Button cancelBtn;
    
    private TLC m_TLC;
    String Barcode;
    
    public sellATreeSecondView() {
        
    }
    
    @FXML
    private void initialize() {
        
        paymentMethodChoice.setItems(FXCollections.observableArrayList("Cash", "Check"));
    }
    
    public void setTLC(TLC tlc) {
        this.m_TLC = tlc;
    }
    public void setBarcode(String barcode)
    {
    	this.Barcode = barcode;
    }
    
    @FXML
    private void sellATreeSubmitButtonOnAction() {
    }
    
    @FXML
    private void sellATreeCancelButtonOnAction() {
        this.m_TLC.transactionDone();
    }
}
