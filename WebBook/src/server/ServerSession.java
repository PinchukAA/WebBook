package server;

import javafx.collections.ObservableList;
import model.Constants;
import model.Person;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSession {
    private ServerSocket serverSocket;
    private Socket socket;
    private Server server;
    private JTextArea jTextArea;
    public ObjectOutputStream outputStream;
    public ObjectInputStream inputStream;

    private DataBase dataBase;
    private DataSaver dataSaver;
    private DataSetter dataSetter;
    private DataSetter findDataSetter;
    private DataRemover dataRemover;
    private DataSearcher dataSearcher;

    public ServerSession(ServerSocket serverSocket, Socket socket, Server server, JTextArea jTextArea) {
        this.server = server;
        this.serverSocket = serverSocket;
        this.socket = socket;
        this.jTextArea = jTextArea;

        createSession();
    }

    public void createSession() {
        jTextArea.append("New session\n");
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
            inputStream = new ObjectInputStream(socket.getInputStream());

            dataSetter = new DataSetter();
            dataSetter.setServerSession(this);

            dataBase = new DataBase(dataSetter);

            dataSaver = new DataSaver();
            dataSaver.setServerSession(this);
            dataSaver.setDataBase(dataBase);

            findDataSetter = new DataSetter();
            findDataSetter.setServerSession(this);
            dataSearcher = new DataSearcher(findDataSetter, dataBase);

            dataRemover = new DataRemover(dataSetter, dataBase, this);

            runSession();
        } catch (Exception e) {
            jTextArea.append("ERROR.\n");
            e.printStackTrace();
        }
    }

    public void runSession() throws IOException, ClassNotFoundException {
        jTextArea.append("Run session\n");
        String command;
        jTextArea.append("Client connected\n");

        while (true) {
            command = (String) inputStream.readObject();
            if (command.equals(Constants.CLIENT_EXIT)) break;
            jTextArea.append("New command from client " + command + "\n");
            switch (command) {
                case Constants.OPEN_FILE:
                    dataSaver.openFile((String)inputStream.readObject());
                    break;
                case Constants.SAVE_FILE:
                    dataSaver.saveFile((String) inputStream.readObject());
                    break;

                case Constants.FIRST_PAGE:
                    dataSetter.showFirstPage();
                    break;
                case Constants.PREV_PAGE:
                    dataSetter.showPrevPage();
                    break;
                case Constants.NEXT_PAGE:
                    dataSetter.showNextPage();
                    break;
                case Constants.LAST_PAGE:
                    dataSetter.showLastPage();
                    break;
                case Constants.CHANGE_SELECTED_DATA_SIZE:
                    dataSetter.changeSelectedDataSize((Integer) inputStream.readObject());
                    break;

                case Constants.FIRST_FIND_PAGE:
                    findDataSetter.showFirstPage();
                    break;
                case Constants.PREV_FIND_PAGE:
                    findDataSetter.showPrevPage();
                    break;
                case Constants.NEXT_FIND_PAGE:
                    findDataSetter.showNextPage();
                    break;
                case Constants.LAST_FIND_PAGE:
                    findDataSetter.showLastPage();
                    break;
                case Constants.CHANGE_SELECTED_FIND_DATA_SIZE:
                    findDataSetter.changeSelectedDataSize((Integer) inputStream.readObject());
                    break;

                case Constants.ADD_PERSON:
                    dataBase.addPerson((Person) inputStream.readObject());
                    break;
                case Constants.FIND_PERSON:
                    dataSearcher.setFindPerson((Person) inputStream.readObject());
                    break;
                case Constants.FIND_DELETE:
                    dataRemover.setDeletePerson((Person) inputStream.readObject());
                    break;
                case Constants.DELETE_ALL:
                    dataBase.deletePerson(dataBase.getData());
                    break;
                default:
                    jTextArea.append("Wrong command " + command);
                    break;
            }
        }
        jTextArea.append("Client exit\n");
    }

    public ObjectOutputStream getOutputStream(){
        return outputStream;
    }
}
