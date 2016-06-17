package server;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int DEFAULT_PORT = 5555;
    private Socket socket;
    private JTextArea textArea;
    private ServerSession serverSession;
    private ServerSocket serverSocket;

    public Server() {
        JFrame frame = new JFrame("Address table SERVER");
        frame.setSize(250, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(0, 0, 250, 600);
        frame.add(scrollPane);

        /*JButton startButton = new JButton("START");
        startButton.setBounds(200, 630, 60, 30);
        frame.add(startButton);*/
        frame.setVisible(true);

        runServer();
    }

    private void runServer() {
        textArea.append("Run server\n");
        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
            while (true) {
                socket = serverSocket.accept();
                serverSession = new ServerSession(serverSocket, socket, this, textArea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}

