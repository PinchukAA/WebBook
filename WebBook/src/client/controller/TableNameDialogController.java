package client.controller;


import client.Client;
import client.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Constants;

public class TableNameDialogController {
    @FXML
    private TextField tableNameField;

    private MainApp mainApp;
    private Client client;

    public void setClient(Client client){
        this.client = client;
    }

    @FXML
    public void openTable(){
        client.sendToServer(Constants.OPEN_FILE);
    //    client.sendToServer(tableNameField.getText());
    }
}
