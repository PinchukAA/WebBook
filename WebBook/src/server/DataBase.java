package server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Person;
import model.PersonAddress;

public class DataBase {
    private ObservableList<Person> data;
    private DataSetter dataSetter;

    public DataBase(DataSetter dataSetter){
        this.dataSetter = dataSetter;

        data = FXCollections.observableArrayList();

        data.add(new Person("Maks", "Andreevich", "Nikiforovec", new PersonAddress("Belarus", "MinskRegion", "Minsk", "Gde-to tam", 0, 0, 0)));
        data.add(new Person("Maks", "Andreevich", "Nikiforovec", new PersonAddress("Belarus", "MinskRegion", "Minsk", "Gde-to tam", 1, 1, 1)));
        data.add(new Person("Maks", "Andreevich", "Nikiforovec", new PersonAddress("Belarus", "MinskRegion", "Minsk", "Gde-to tam", 2, 2, 2)));
        data.add(new Person("Vlad", "Vladimirovich", "Oganov", new PersonAddress("Belarus", "MinskRegion", "Minsk", "Gde-to tam", 3, 3, 3)));
        data.add(new Person("Vlad", "Vladimirovich", "Oganov", new PersonAddress("Belarus", "MinskRegion", "Minsk", "Gde-to tam", 4, 4, 4)));
        data.add(new Person("Vlad", "Vladimirovich", "Oganov", new PersonAddress("Belarus", "MinskRegion", "Minsk", "Gde-to tam", 5, 5, 5)));
        data.add(new Person("Nastya", "Aleksandrovna", "Hilko", new PersonAddress("Belarus", "MinskRegion", "Baranovichi", "Gde-to tam", 6, 6, 6)));
        data.add(new Person("Nastya", "Aleksandrovna", "Hilko", new PersonAddress("Belarus", "MinskRegion", "Baranovichi", "Gde-to tam", 7, 7, 7)));
        data.add(new Person("Maks", "Andreevich", "Nikiforovec", new PersonAddress("Belarus", "MinskRegion", "Minsk", "Gde-to tam", 8, 8, 8)));
        data.add(new Person("Maks", "Andreevich", "Nikiforovec", new PersonAddress("Belarus", "MinskRegion", "Minsk", "Gde-to tam", 9, 9, 9)));
        data.add(new Person("Maks", "Andreevich", "Nikiforovec", new PersonAddress("Belarus", "MinskRegion", "Minsk", "Gde-to tam", 10, 10, 10)));
        data.add(new Person("Vlad", "Vladimirovich", "Oganov", new PersonAddress("Belarus", "MinskRegion", "Minsk", "Gde-to tam", 11, 11, 11)));
        data.add(new Person("Vlad", "Vladimirovich", "Oganov", new PersonAddress("Belarus", "MinskRegion", "Minsk", "Gde-to tam", 12, 12, 12)));
        data.add(new Person("Vlad", "Vladimirovich", "Oganov", new PersonAddress("Belarus", "MinskRegion", "Minsk", "Gde-to tam", 13, 13, 13)));
        data.add(new Person("Nastya", "Aleksandrovna", "Hilko", new PersonAddress("Belarus", "MinskRegion", "Baranovichi", "Gde-to tam", 14, 14, 14)));
        data.add(new Person("Nastya", "Aleksandrovna", "Hilko", new PersonAddress("Belarus", "MinskRegion", "Baranovichi", "Gde-to tam", 15, 15, 15)));
        data.add(new Person("Maks", "Andreevich", "Nikiforovec", new PersonAddress("Belarus", "MinskRegion", "Minsk", "Gde-to tam", 16, 16, 16)));
        data.add(new Person("Maks", "Andreevich", "Nikiforovec", new PersonAddress("Belarus", "MinskRegion", "Minsk", "Gde-to tam", 17, 17, 17)));

        changeDataSet();
    }


    public boolean addPerson(Person person){
        boolean isAdded = data.add(person);
        if(isAdded) changeDataSet();
        return  isAdded;
    }

    public boolean deletePerson(Person person){
        boolean isDeleted = data.remove(person);
        if(isDeleted) changeDataSet();
        return isDeleted;
    }


    public boolean deletePerson(ObservableList<Person> persons){
        boolean isDeleted = data.removeAll(persons);
        if(isDeleted) changeDataSet();
        return isDeleted;
    }

    public void setData(ObservableList<Person> data){
        this.data = data;
        changeDataSet();
    }

    public ObservableList<Person> getData(){
        return data;
    }

    private void changeDataSet(){
        dataSetter.setCurrentData(data);
    }
}
