package client.controller;

import client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Person;

public class PersonAddDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField surNameField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField regionField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField houseNumberField;
    @FXML
    private TextField pavilionNumberField;
    @FXML
    private TextField flatNumberField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    private Client client;

    public void setClient(Client client){
        this.client = client;
    }

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        surNameField.setText(person.getSurName());
        countryField.setText(person.getAddress().getCountry());
        regionField.setText(person.getAddress().getRegion());
        cityField.setText(person.getAddress().getCity());
        streetField.setText(person.getAddress().getStreet());
        houseNumberField.setText(Integer.toString(person.getAddress().getHouseNumber()));
        pavilionNumberField.setText(Integer.toString(person.getAddress().getPavilionNumber()));
        flatNumberField.setText(Integer.toString(person.getAddress().getFlatNumber()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }


    @FXML
    public void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setSurName(surNameField.getText());
            person.getAddress().setCountry(countryField.getText());
            person.getAddress().setRegion(regionField.getText());
            person.getAddress().setCity(cityField.getText());
            person.getAddress().setStreet(streetField.getText());
            person.getAddress().setHouseNumber(Integer.parseInt(houseNumberField.getText()));
            person.getAddress().setPavilionNumber(Integer.parseInt(pavilionNumberField.getText()));
            person.getAddress().setFlatNumber(Integer.parseInt(flatNumberField.getText()));
            person.setFIO(lastNameField.getText(), firstNameField.getText(), surNameField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (surNameField.getText() == null || surNameField.getText().length() == 0) {
            errorMessage += "No valid surname!\n";
        }
        if (countryField.getText() == null || countryField.getText().length() == 0) {
            errorMessage += "No valid country!\n";
        }
        if (regionField.getText() == null || regionField.getText().length() == 0) {
            errorMessage += "No valid region!\n";
        }
        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (houseNumberField.getText() == null || houseNumberField.getText().length() == 0) {
            errorMessage += "No valid house number!\n";
        } else {
            try {
                Integer.parseInt(houseNumberField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid house number (must be an integer)!\n";
            }
        }

        if (pavilionNumberField.getText() == null || pavilionNumberField.getText().length() == 0) {
            errorMessage += "No valid pavilion number!\n";
        } else {
            try {
                Integer.parseInt(pavilionNumberField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid pavilion number (must be an integer)!\n";
            }
        }

        if (flatNumberField.getText() == null || flatNumberField.getText().length() == 0) {
            errorMessage += "No valid flat number!\n";
        } else {
            try {
                Integer.parseInt(flatNumberField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid flat number (must be an integer)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}