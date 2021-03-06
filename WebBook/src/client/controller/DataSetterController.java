package client.controller;

import client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Constants;
import model.Person;

public class DataSetterController{
    boolean f = false;
    @FXML
    private TextField selectedDataSizeField;
    @FXML
    private TextField dataSizeField;

    private Stage dialogStage;
    private Client client;
    private TableOverviewController tableOverviewController;
    private ObservableList<Person> selectedData = FXCollections.observableArrayList();

    private int selectedDataSize = 0;

    public void setF(){
        f = true;
    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    public void setClient(Client client){
        this.client = client;
    }


    public void setTableOverviewController(TableOverviewController tableOverviewController){
        this.tableOverviewController = tableOverviewController;
    }

    public void initTable(){
        selectedData = client.getDataFromServer();
        dataSizeField.setText(String.valueOf(client.getNumberFromServer()));
        selectedDataSizeField.setText(String.valueOf(selectedDataSize));

        tableOverviewController.removeAllTableItems();
        tableOverviewController.setTableData(selectedData);
    }

    @FXML
    private void showFirstPage(){
        if(f) client.sendToServer(Constants.FIRST_FIND_PAGE);
        else client.sendToServer(Constants.FIRST_PAGE);
        initTable();
    }

    @FXML
    private void showLastPage(){
        if(f) client.sendToServer(Constants.LAST_FIND_PAGE);
        else client.sendToServer(Constants.LAST_PAGE);
        initTable();
    }

    @FXML
    private void showPrevPage(){
        if(f) client.sendToServer(Constants.PREV_FIND_PAGE);
        else client.sendToServer(Constants.PREV_PAGE);
        initTable();
    }

    @FXML
    public void showNextPage(){
        if(f) client.sendToServer(Constants.NEXT_FIND_PAGE);
        else client.sendToServer(Constants.NEXT_PAGE);
        initTable();
    }

    @FXML
    private void changeSelectedDataSize(){
        String errorMessage = "";
        try {
            selectedDataSize = Integer.parseInt(selectedDataSizeField.getText());
        } catch (NumberFormatException e) {
            errorMessage += "No valid select size (must be an integer)!\n";
        }

        if (errorMessage.length() != 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();
        } else {
            if(f) client.sendToServer(Constants.CHANGE_SELECTED_FIND_DATA_SIZE);
            else client.sendToServer(Constants.CHANGE_SELECTED_DATA_SIZE);
            client.sendToServer(selectedDataSize);
            initTable();
        }
    }
}
