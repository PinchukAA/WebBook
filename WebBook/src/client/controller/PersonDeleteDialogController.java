package client.controller;

import client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Constants;
import model.Person;
import server.DataSetter;

import java.util.Iterator;

public class PersonDeleteDialogController {
    private DataEnterComponentController dataEnterComponentController;
    private DataSetterController dataSetterController;
    private Client client;
    private Stage dialogStage;
    private Integer delNumber;

    public void setClient(Client client){
        this.client = client;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setDataEnterComponentController(DataEnterComponentController dataEnterComponentController){
        this.dataEnterComponentController = dataEnterComponentController;
    }

    public void setDataSetterController(DataSetterController dataSetterController){
        this.dataSetterController = dataSetterController;
    }

    @FXML
    public void deleteData(){
        Person tempPerson = new Person();
        if(dataEnterComponentController.getFirstNameCheck())
            tempPerson.setFirstName(dataEnterComponentController.getFirstNameText());
        else tempPerson.setFirstName(null);
        if(dataEnterComponentController.getLastNameCheck())
            tempPerson.setLastName(dataEnterComponentController.getLastNameText());
        else tempPerson.setLastName(null);
        if(dataEnterComponentController.getSurNameCheck())
            tempPerson.setSurName(dataEnterComponentController.getSurNameText());
        else tempPerson.setSurName(null);
        if(dataEnterComponentController.getCountryCheck())
            tempPerson.getAddress().setCountry(dataEnterComponentController.getCountryText());
        else tempPerson.getAddress().setCountry(null);
        if(dataEnterComponentController.getRegionCheck())
            tempPerson.getAddress().setRegion(dataEnterComponentController.getRegionText());
        else tempPerson.getAddress().setRegion(null);
        if(dataEnterComponentController.getCityCheck())
            tempPerson.getAddress().setCity(dataEnterComponentController.getCityText());
        else tempPerson.getAddress().setCity(null);
        if(dataEnterComponentController.getStreetCheck())
            tempPerson.getAddress().setStreet(dataEnterComponentController.getStreetText());
        else tempPerson.getAddress().setStreet(null);
        if(dataEnterComponentController.getHouseNumberCheck())
            tempPerson.getAddress().setHouseNumber(dataEnterComponentController.getHouseNumberText());
        else tempPerson.getAddress().setHouseNumber(0);
        if(dataEnterComponentController.getPavilionNumberCheck())
            tempPerson.getAddress().setPavilionNumber(dataEnterComponentController.getPavilionNumberText());
        else tempPerson.getAddress().setPavilionNumber(0);
        if(dataEnterComponentController.getFlatNumberCheck())
            tempPerson.getAddress().setFlatNumber(dataEnterComponentController.getFlatNumberText());
        else tempPerson.getAddress().setFlatNumber(0);

        client.sendToServer(Constants.FIND_DELETE);
        client.sendToServer(tempPerson);
        delNumber = client.getNumberFromServer();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(dialogStage);

        if(delNumber != 0){
            dataSetterController.initTable();
            
            alert.setTitle("Deleted items");
            alert.setHeaderText("Deleted items");
            alert.setContentText(delNumber + " items have been deleted.");

            alert.showAndWait();
        } else {
            alert.setTitle("Deleted items");
            alert.setHeaderText("No content in the table");
            alert.setContentText(0 + " items have been deleted.");

            alert.showAndWait();
        }

    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

}
