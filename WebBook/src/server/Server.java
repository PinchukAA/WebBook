package server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int DEFAULT_PORT = 5555;
    private Socket socket;
    private JTextArea textArea;
    private ServerSession serverSession;
    private ServerSocket serverSocket;
    private boolean b = true;

    public Server() {
        JFrame frame = new JFrame("Address table SERVER");
        frame.setSize(250, 690);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setSize(250, 690);
        panel.setLayout(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(0, 0, 250, 600);
        panel.add(scrollPane);

        JButton startButton = new JButton("START");
        startButton.setBounds(30, 610, 80, 30);
        startButton.addActionListener(e -> runServer());
        panel.add(startButton);

        JButton stopButton = new JButton("STOP");
        stopButton.setBounds(140, 610, 80, 30);
        stopButton.addActionListener(e -> stopServer());
        panel.add(stopButton);

        frame.add(panel);
        frame.setVisible(true);

    //    runServer();
    }

    private void runServer(){
        textArea.append("Run server\n");
        try {
            b = true;
            serverSocket = new ServerSocket(DEFAULT_PORT);
            while (b) {
                socket = serverSocket.accept();
                serverSession = new ServerSession(serverSocket, socket, this, textArea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopServer(){
        textArea.append("Server stopped\n");
        try {
            b = false;
            serverSession.getOutputStream().close();
            serverSession.getInputStream().close();

            socket.close();
            serverSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Server();
    }
}

