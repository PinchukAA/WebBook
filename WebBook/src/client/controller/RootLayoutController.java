package client.controller;

import client.Client;
import client.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Constants;
import model.Person;

import java.util.regex.Pattern;

public class RootLayoutController {
    private MainApp mainApp;
    private TableOverviewController tableOverviewController;
    private Client client;
    private DataSetterController dataSetterController;

    @FXML
    private TextField hostField;
    @FXML
    private TextField portField;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    public void setTableOverviewController(TableOverviewController tableOverviewController){
        this.tableOverviewController = tableOverviewController;
    }

    public void setDataSetterController(DataSetterController dataSetterController){
        this.dataSetterController = dataSetterController;
    }

    public void setClient(Client client){
        this.client = client;
    }

    public String getHostText(){
        return hostField.getText();
    }

    public String getPortText(){
        return portField.getText();
    }

    private boolean isCorrectHostAndPort(){
        Pattern pHost = Pattern.compile("((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])");
        Pattern pPort = Pattern.compile("[0-9]{1,5}");
        if (pPort.matcher(getPortText()).matches()){
            int portInt = Integer.parseInt(getPortText());
            return (pHost.matcher(getHostText()).matches() && 0 <= portInt && portInt <= 65535);
        } else {
            return false;
        }
    }

    @FXML
    private void connect(){
        if(isCorrectHostAndPort()){
            client.connect(getHostText(), Integer.parseInt(getPortText()));
      //      System.out.print(client.getNumberFromServer());
      //      dataSetterController.initTable();
        }
    }

    @FXML
    public void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonAddDialog(tempPerson);
        if (okClicked) {
            client.sendToServer(Constants.ADD_PERSON);
            client.sendToServer(tempPerson);
            dataSetterController.initTable();
        }
    }

    @FXML
    public void handleFindPerson(){
        boolean okClicked = mainApp.showPersonFindDialog();
    }

    @FXML
    public void handleFindAndDeletePerson() {
        mainApp.showPersonDeleteDialog();
    }

    @FXML
    private void handleOpen() {
        mainApp.showTableNameDialog();
        dataSetterController.initTable();
    }

    @FXML
    private void handleSave() {
        client.sendToServer(Constants.SAVE_FILE);
    }
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
