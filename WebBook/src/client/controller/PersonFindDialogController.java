package client.controller;

import client.Client;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Constants;
import model.Person;

public class PersonFindDialogController {
    private TableOverviewController findTableOverviewController;
    private DataSetterController findDataSetterController;
    private DataEnterComponentController findDataEnterComponentController;
    private Client client;

    private Stage dialogStage;
    private boolean okClicked = false;

    public void setClient(Client client){
        this.client = client;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setFindDataSetterController(DataSetterController findDataSetterController){
        this.findDataSetterController = findDataSetterController;
    }

    public void setFindDataEnterComponentController(DataEnterComponentController findDataEnterComponentController){
        this.findDataEnterComponentController = findDataEnterComponentController;
    }

    public void setFindTableOverviewController(TableOverviewController findTableOverviewController){
        this.findTableOverviewController = findTableOverviewController;
    }

    public boolean isFindClicked() {
        return okClicked;
    }

    @FXML
    public void handleFindPerson(){

        Person tempPerson = new Person();
        if(findDataEnterComponentController.getFirstNameCheck())
            tempPerson.setFirstName(findDataEnterComponentController.getFirstNameText());
        else tempPerson.setFirstName(null);
        if(findDataEnterComponentController.getLastNameCheck())
            tempPerson.setLastName(findDataEnterComponentController.getLastNameText());
        else tempPerson.setLastName(null);
        if(findDataEnterComponentController.getSurNameCheck())
            tempPerson.setSurName(findDataEnterComponentController.getSurNameText());
        else tempPerson.setSurName(null);
        if(findDataEnterComponentController.getCountryCheck())
            tempPerson.getAddress().setCountry(findDataEnterComponentController.getCountryText());
        else tempPerson.getAddress().setCountry(null);
        if(findDataEnterComponentController.getRegionCheck())
            tempPerson.getAddress().setRegion(findDataEnterComponentController.getRegionText());
        else tempPerson.getAddress().setRegion(null);
        if(findDataEnterComponentController.getCityCheck())
            tempPerson.getAddress().setCity(findDataEnterComponentController.getCityText());
        else tempPerson.getAddress().setCity(null);
        if(findDataEnterComponentController.getStreetCheck())
            tempPerson.getAddress().setStreet(findDataEnterComponentController.getStreetText());
        else tempPerson.getAddress().setStreet(null);
        if(findDataEnterComponentController.getHouseNumberCheck())
            tempPerson.getAddress().setHouseNumber(findDataEnterComponentController.getHouseNumberText());
        else tempPerson.getAddress().setHouseNumber(0);
        if(findDataEnterComponentController.getPavilionNumberCheck())
            tempPerson.getAddress().setPavilionNumber(findDataEnterComponentController.getPavilionNumberText());
        else tempPerson.getAddress().setPavilionNumber(0);
        if(findDataEnterComponentController.getFlatNumberCheck())
            tempPerson.getAddress().setFlatNumber(findDataEnterComponentController.getFlatNumberText());
        else tempPerson.getAddress().setFlatNumber(0);

        client.sendToServer(Constants.FIND_PERSON);
        client.sendToServer(tempPerson);
        findDataSetterController.initTable();
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

}
