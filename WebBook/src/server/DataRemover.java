package server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Person;

import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;

public class DataRemover {
    private ServerSession serverSession;
    private Person deletePerson;
    private DataSetter dataSetter;
    private DataBase dataBase;
    private ObservableList<Person> deleteData = FXCollections.observableArrayList();

    public DataRemover(DataSetter dataSetter, DataBase dataBase, ServerSession serverSession){
        this.dataBase = dataBase;
        this.dataSetter = dataSetter;
        this.serverSession = serverSession;
    }

    public void setDeletePerson(Person deletePerson){
        this.deletePerson = deletePerson;
        delete();
    }

    public void delete(){
        if (deleteData != null) deleteData.remove(0, deleteData.size());

        for (Iterator<Person> iteratorPerson = (dataBase.getData()).iterator(); iteratorPerson.hasNext();){
            boolean a = false;
            boolean b = true;
            Person tempPerson = iteratorPerson.next();
            if(deletePerson.getFirstName() != null)
                if ((tempPerson.getFirstName()).equals(deletePerson.getFirstName())) a = true;
                else b = false;
            if(deletePerson.getLastName() != null)
                if ((tempPerson.getLastName()).equals(deletePerson.getLastName())) a = true;
                else b = false;
            if(deletePerson.getSurName() != null)
                if ((tempPerson.getSurName()).equals(deletePerson.getSurName())) a = true;
                else b = false;
            if(deletePerson.getAddress().getCountry() != null)
                if ((tempPerson.getAddress().getCountry()).equals(deletePerson.getAddress().getCountry())) a = true;
                else b = false;
            if(deletePerson.getAddress().getRegion() != null)
                if ((tempPerson.getAddress().getRegion()).equals(deletePerson.getAddress().getRegion())) a = true;
                else b = false;
            if(deletePerson.getAddress().getCity() != null )
                if ((tempPerson.getAddress().getCity()).equals(deletePerson.getAddress().getCity())) a = true;
                else b = false;
            if(deletePerson.getAddress().getStreet() != null)
                if ((tempPerson.getAddress().getStreet()).equals(deletePerson.getAddress().getStreet())) a = true;
                else b = false;
            if(deletePerson.getAddress().getHouseNumber() != 0)
                if ((tempPerson.getAddress().getHouseNumber()).equals(deletePerson.getAddress().getHouseNumber())) a = true;
                else b = false;
            if(deletePerson.getAddress().getPavilionNumber() != 0)
                if ((tempPerson.getAddress().getPavilionNumber()).equals(deletePerson.getAddress().getPavilionNumber())) a = true;
                else b = false;
            if(deletePerson.getAddress().getFlatNumber() != 0)
                if ((tempPerson.getAddress().getFlatNumber()).equals(deletePerson.getAddress().getFlatNumber())) a = true;
                else b = false;
            if (a && b) deleteData.add(tempPerson);
        }
        if(deleteData != null) {
            try {
                serverSession.getOutputStream().writeObject(deleteData.size());
                serverSession.getOutputStream().flush();
                dataBase.deletePerson(deleteData);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog
                        (null, "Can't send data to client", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else try {
            serverSession.getOutputStream().writeObject(0);
            serverSession.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog
                    (null, "Can't send data to client", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

}
