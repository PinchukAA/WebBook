package server;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Person;

import java.io.IOException;

public class DataSetter {
    private ServerSession serverSession;
    private ObservableList<Person> currentData;
    private ObservableList<Person> selectedData = FXCollections.observableArrayList();
    private int selectedDataSize = 0;
    private int startIndex = 0;
    private int endIndex = 0;

    public void setServerSession(ServerSession serverSession){
        this.serverSession = serverSession;
    }

    public void setCurrentData(ObservableList<Person> currentData){
        this.currentData = currentData;
        if (selectedDataSize > currentData.size()) selectedDataSize = currentData.size();
        if (endIndex > currentData.size()) endIndex = currentData.size();
        if (startIndex > currentData.size() && startIndex - selectedDataSize > 0) startIndex -= selectedDataSize;

        if (selectedDataSize == 0){selectedDataSize = currentData.size();
            showFirstPage();
        } else selectData();

       // dataSizeField.setText(String.valueOf(currentData.size()));
       // selectedDataSizeField.setText(String.valueOf(selectedDataSize));
    }

    private void selectData(){
        if (startIndex == endIndex && endIndex != 0) showPrevPage();
        selectedData.setAll(currentData.subList(startIndex, endIndex));
//        selectedDataSizeField.setText(String.valueOf(selectedDataSize));

        try {
            System.out.print(1);
            serverSession.getOutputStream().writeObject(selectedData.toArray(new Person[selectedData.size()]));
            serverSession.getOutputStream().flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //      serverSession.sendToClient(selectedData);
    }


    public void showFirstPage(){
        startIndex = 0;
        endIndex = selectedDataSize;

        selectData();
    }

    public void showLastPage(){
        endIndex = currentData.size();
        startIndex = currentData.size() - (currentData.size()%selectedDataSize);

        if (startIndex == endIndex) startIndex = currentData.size() - selectedDataSize;
        selectData();
    }

    public void showPrevPage(){
        if(startIndex >= selectedDataSize - 1) {
            endIndex = startIndex;
            startIndex -= selectedDataSize;
            selectData();
        }
        else showFirstPage();
    }

    public void showNextPage(){
        if(currentData.size() - endIndex >= selectedDataSize) {
            startIndex = endIndex;
            endIndex += selectedDataSize;

            selectData();
        }
        else showLastPage();
    }


    public void changeSelectedDataSize(Integer selectedDataSize){
        if (selectedDataSize > currentData.size()) this.selectedDataSize = currentData.size();
        showFirstPage();
    }
}
