package client.controller;


import client.Client;
import client.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Constants;
import server.DataSetter;

import java.awt.*;

public class TableNameDialogController {
    @FXML
    private TextField tableNameField;

    private DataSetterController dataSetterController;
    private Client client;

    public void setClient(Client client){
        this.client = client;
    }
    public void setDataSetterController(DataSetterController dataSetterController){
        this.dataSetterController = dataSetterController;
    }

    @FXML
    public void openTable(){
        client.sendToServer(Constants.OPEN_FILE);
        client.sendToServer(tableNameField.getText());
        dataSetterController.initTable();
    }

    @FXML
    public void saveTable(){
        client.sendToServer(Constants.SAVE_FILE);
        client.sendToServer(tableNameField.getText());
    }
}
