package server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Person;

import java.io.File;
import java.util.Iterator;

public class DataSaver {
    private DataBase dataBase;
    private ServerSession serverSession;

    private Stage primaryStage;
    private ObservableList<Person> data = FXCollections.observableArrayList();
    private String filePath;

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void setServerSession(ServerSession serverSession){
        this.serverSession = serverSession;
    }

    public void openFile(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            loadPersonDataFromFile(file);
        }
        for (Iterator<Person> iteratorPerson = dataBase.getData().iterator(); iteratorPerson.hasNext();){
            iteratorPerson.next().setFIO();
        }
    }

    public void saveFile() {
        File personFile = getPersonFilePath();
        if (personFile != null) {
            savePersonDataToFile(personFile);
        } else {
            saveFileAs();
        }
    }

    private void saveFileAs(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            savePersonDataToFile(file);
        }
    }

    public File getPersonFilePath() {
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setPersonFilePath(File file) {
        if (file != null) {
            filePath = file.getAbsolutePath();
        } else {
            filePath = null;
        }

    }

    public void loadPersonDataFromFile(File file) {
        try {
            DataParser dataParser = new DataParser();
            dataBase.setData(dataParser.loadAddressBook(file));

            setPersonFilePath(file);

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
            setPersonFilePath(file);
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
