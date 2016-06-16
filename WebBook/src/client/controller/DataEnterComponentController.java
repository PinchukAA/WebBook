package client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class DataEnterComponentController {
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

    @FXML
    private CheckBox firstNameCheckBox;
    @FXML
    private CheckBox lastNameCheckBox;
    @FXML
    private CheckBox surNameCheckBox;
    @FXML
    private CheckBox countryCheckBox;
    @FXML
    private CheckBox regionCheckBox;
    @FXML
    private CheckBox cityCheckBox;
    @FXML
    private CheckBox streetCheckBox;
    @FXML
    private CheckBox houseNumberCheckBox;
    @FXML
    private CheckBox pavilionNumberCheckBox;
    @FXML
    private CheckBox flatNumberCheckBox;

    public String getFirstNameText(){
        return firstNameField.getText();
    }
    public String getLastNameText(){
        return lastNameField.getText();
    }
    public String getSurNameText(){
        return surNameField.getText();
    }
    public String getCountryText(){
        return countryField.getText();
    }
    public String getRegionText(){
        return regionField.getText();
    }
    public String getCityText(){
        return cityField.getText();
    }
    public String getStreetText(){
        return streetField.getText();
    }
    public Integer getHouseNumberText(){
        return Integer.parseInt(houseNumberField.getText());
    }
    public Integer getPavilionNumberText(){
        return Integer.parseInt(pavilionNumberField.getText());
    }
    public Integer getFlatNumberText(){
        return Integer.parseInt(flatNumberField.getText());
    }

    public Boolean getFirstNameCheck(){
        return firstNameCheckBox.isSelected();
    }
    public Boolean getLastNameCheck(){
        return lastNameCheckBox.isSelected();
    }
    public Boolean getSurNameCheck(){
        return surNameCheckBox.isSelected();
    }
    public Boolean getCountryCheck(){
        return countryCheckBox.isSelected();
    }
    public Boolean  getRegionCheck(){
        return regionCheckBox.isSelected();
    }
    public Boolean getCityCheck(){
        return cityCheckBox.isSelected();
    }
    public Boolean getStreetCheck(){
        return streetCheckBox.isSelected();
    }
    public Boolean getHouseNumberCheck(){
        return houseNumberCheckBox.isSelected();
    }
    public Boolean getPavilionNumberCheck(){
        return pavilionNumberCheckBox.isSelected();
    }
    public Boolean getFlatNumberCheck(){
        return flatNumberCheckBox.isSelected();
    }
}
