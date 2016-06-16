package client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Person;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {
    private MainApp mainApp;
    private String host;
    private Socket socket;
    private int port;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private ObservableList<Person> data = FXCollections.observableArrayList();
    private Integer num;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void connect(String host, int port){
        this.host = host;
        this.port = port;

        createSocket();

        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            JOptionPane.showMessageDialog
                    (null, "Can't install connection", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ObjectInputStream getInputStream(){
        return inputStream;
    }

    public void sendToServer(Object object){
        try {
            outputStream.writeObject(object);
            outputStream.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog
                    (null, "Can't send data to server", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Integer getNumberFromServer(){
        try{
            num = (Integer) inputStream.readObject();
        }catch (Exception e) {
            JOptionPane.showMessageDialog
                    (null, "Can't read date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return num;
    }

    public ObservableList<Person> getDataFromServer(){
        try{
            data = FXCollections.observableArrayList((Person[]) inputStream.readObject());
        }catch (Exception e) {
            JOptionPane.showMessageDialog
                    (null, "Can't read date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }


    public void createSocket(){
        try {
            socket = new Socket(host, port);
            JOptionPane.showMessageDialog
                    (null, "You connect to " + host + ":" + port, "INFO", JOptionPane.INFORMATION_MESSAGE);
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog
                    (null, "Unknown host " + host, "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog
                    (null, "I/O Error creating socket " + host + ":" + port, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
