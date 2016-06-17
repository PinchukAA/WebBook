package server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Person;

import javax.swing.*;
import java.io.File;
import java.util.Iterator;

public class DataSaver {
    private DataBase dataBase;
    private ServerSession serverSession;

    private ObservableList<Person> data = FXCollections.observableArrayList();
    private String filePath;
    private String fileName;

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void setServerSession(ServerSession serverSession){
        this.serverSession = serverSession;
    }

    public void openFile(String fileName){
        this.fileName = fileName;
        filePath = "D:\\XML\\" + fileName+ ".xml";

        File file = new File(filePath);

        if (file != null) {
            loadPersonDataFromFile(file);
        }
        for (Iterator<Person> iteratorPerson = dataBase.getData().iterator(); iteratorPerson.hasNext();){
            iteratorPerson.next().setFIO();
        }
    }

    public void saveFile(String fileName){
        this.fileName = fileName;
        filePath = "D:\\XML\\" + fileName+ ".xml";

        File file = new File(filePath);
        if (file != null) {
            savePersonDataToFile(file);
        }
    }


    public void loadPersonDataFromFile(File file) {
        try {
            DataParser dataParser = new DataParser();
            dataBase.setData(dataParser.loadAddressBook(file));

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            e.printStackTrace();
            alert.showAndWait();
        }
    }

    public void savePersonDataToFile(File file) {
        try {
            DataParser dataParser = new DataParser();
            dataParser.save(file, dataBase.getData());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            e.printStackTrace();
            alert.showAndWait();
        }
    }

}
