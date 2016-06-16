package client.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Person;


public class TableOverviewController {
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> FIOColumn;
    @FXML
    private TableColumn<Person, String> countryColumn;
    @FXML
    private TableColumn<Person, String> regionColumn;
    @FXML
    private TableColumn<Person, String> cityColumn;
    @FXML
    private TableColumn<Person, String> streetColumn;
    @FXML
    private TableColumn<Person, Integer> houseNumberColumn;
    @FXML
    private TableColumn<Person, Integer> pavilionNumberColumn;
    @FXML
    private TableColumn<Person, Integer> flatNumberColumn;

    private ObservableList<Person> tableData;

    @FXML
    private void initialize() {
        FIOColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFIO()));
        countryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getCountry()));
        regionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getRegion()));
        cityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getCity()));
        streetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress().getStreet()));
        houseNumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAddress().getHouseNumber()).asObject());
        pavilionNumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAddress().getPavilionNumber()).asObject());
        flatNumberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAddress().getFlatNumber()).asObject());
    }

    public void setTableData(ObservableList<Person> tableData){
        this.tableData = tableData;
        setTableItems();
    }

    public void setTableItems(){
       tableView.setItems(tableData);
    }

    public Person getSelectedPerson(){
        return tableView.getSelectionModel().getSelectedItem();
    }

    public void removeAllTableItems(){
        if (tableData != null) tableView.getItems().removeAll();
    }
}