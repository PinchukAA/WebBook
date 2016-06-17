package server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Person;

import java.util.Iterator;

public class DataSearcher {
    private Person findPerson;
    private DataSetter findDataSetter;
    private DataBase dataBase;
    private ObservableList<Person> findData = FXCollections.observableArrayList();

    public DataSearcher(DataSetter findDataSetter, DataBase dataBase){
        this.dataBase = dataBase;
        this.findDataSetter = findDataSetter;
    }

    public void setFindPerson(Person findPerson){
        this.findPerson = findPerson;
        find();
    }

    public void find(){
        if (findData != null)
        findData.remove(0, findData.size());

        for (Iterator<Person> iteratorPerson = (dataBase.getData()).iterator(); iteratorPerson.hasNext();){
            boolean a = false;
            boolean b = true;
            Person tempPerson = iteratorPerson.next();
            if(findPerson.getFirstName() != null)
                if ((tempPerson.getFirstName()).equals(findPerson.getFirstName())) a = true;
                else b = false;
            if(findPerson.getLastName() != null)
                if ((tempPerson.getLastName()).equals(findPerson.getLastName())) a = true;
                else b = false;
            if(findPerson.getSurName() != null)
                if ((tempPerson.getSurName()).equals(findPerson.getSurName())) a = true;
                else b = false;
            if(findPerson.getAddress().getCountry() != null)
                if ((tempPerson.getAddress().getCountry()).equals(findPerson.getAddress().getCountry())) a = true;
                else b = false;
            if(findPerson.getAddress().getRegion() != null)
                if ((tempPerson.getAddress().getRegion()).equals(findPerson.getAddress().getRegion())) a = true;
                else b = false;
            if(findPerson.getAddress().getCity() != null )
                if ((tempPerson.getAddress().getCity()).equals(findPerson.getAddress().getCity())) a = true;
                else b = false;
            if(findPerson.getAddress().getStreet() != null)
                if ((tempPerson.getAddress().getStreet()).equals(findPerson.getAddress().getStreet())) a = true;
                else b = false;
            if(findPerson.getAddress().getHouseNumber() != 0)
                if ((tempPerson.getAddress().getHouseNumber()).equals(findPerson.getAddress().getHouseNumber())) a = true;
                else b = false;
            if(findPerson.getAddress().getPavilionNumber() != 0)
                if ((tempPerson.getAddress().getPavilionNumber()).equals(findPerson.getAddress().getPavilionNumber())) a = true;
                else b = false;
            if(findPerson.getAddress().getFlatNumber() != 0)
                if ((tempPerson.getAddress().getFlatNumber()).equals(findPerson.getAddress().getFlatNumber())) a = true;
                else b = false;
            if (a && b) findData.add(tempPerson);
        }
        findDataSetter.setCurrentData(findData);
    }
}
