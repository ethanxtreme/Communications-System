package communicationsSystem.network;

import communicationsSystem.ui.GUI;
import communicationsSystem.model.ChatMessage;
import communicationsSystem.model.MessageStatus;
import communicationsSystem.model.MessageType;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/*
 * Client connects to listening server object
 * on connection, pass login message to server
 * server returns success status
 * client sends text message to server
 * client prompts user for text to send to server
 * user enters logout, send logout message to server
 */

public class Client {
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // create a new instance of the Client class
        Client client = new Client();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the port number to connect to: <1234>");
//        int port = Integer.parseInt(sc.nextLine());
        int port = 1234;

        // Get the local host's IP address
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("IPV4 Address : " + (localhost.getHostAddress()).trim());
        String host = localhost.getHostAddress().trim();

        // Connect to the ServerSocket at host:port
        Socket socket = new Socket(host, port);
        System.out.println("Connected to " + host + ":" + port);

        // Output stream socket
        OutputStream outputStream = socket.getOutputStream();
        //ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream = new ObjectOutputStream(outputStream);

        // Input stream socket
        InputStream inputStream = socket.getInputStream();
        //ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        objectInputStream = new ObjectInputStream(inputStream);

        // TODO debugging tool:
//        System.out.print("Type 'login' to test loggin in, otherwise, messaging will be tested: ");
//        String testType = sc.nextLine();

        // TODO trying to call GUI from client
        // create the GUI and pass the client instance to the constructor
        GUI gui = new GUI("login", false, client);
        client.setGUI(gui);

        // User login
        System.out.print("Enter your username: ");
        String username = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        NetworkMessage loginMessage = new NetworkMessage();
        loginMessage.setType(MessageType.LOGIN);
        loginMessage.setLoginCredentials(username + "::" + password);
        objectOutputStream.writeObject(loginMessage);

        NetworkMessage receivedMessage = null;

        try {
            receivedMessage = (NetworkMessage) objectInputStream.readObject();
        } catch (EOFException e) {
            System.out.println("Error: Unexpected end of stream");
        }

        assert receivedMessage != null;
        if (receivedMessage.getStatus().equals(MessageStatus.SUCCESS)) {
            System.out.println("Login successful.");

            // Main loop for handling user commands
            boolean running = true;
            while (running) {
                System.out.println("Commands: SEND, MESSAGES, LOGOUT");
                System.out.print("Enter command: ");
                String command = sc.next();

                NetworkMessage commandMessage = new NetworkMessage();
                switch (command.toUpperCase()) {
                    case "SEND" -> {
                        System.out.print("Enter the recipient's username: ");
                        String recipient = sc.next();
                        System.out.print("Enter your message: ");
                        sc.nextLine(); // Consume the newline character
                        String messageText = sc.nextLine();
                        ChatMessage chatMessage = new ChatMessage(null, username, new String[]{recipient}, messageText, null);
                        commandMessage.setType(MessageType.TEXT);
                        commandMessage.setChatMessage(chatMessage);
                        objectOutputStream.writeObject(commandMessage);
                    }
                    case "MESSAGES" -> {
                        commandMessage.setType(MessageType.MESSAGES);
                        objectOutputStream.writeObject(commandMessage);
                    }
                    case "LOGOUT" -> {
                        commandMessage.setType(MessageType.LOGOUT);
                        objectOutputStream.writeObject(commandMessage);
                        running = false;
                    }
                    default -> System.out.println("Invalid command. Please try again.");
                }

                // Wait for a response from the server and display it to the user
                if (running) {
                    receivedMessage = (NetworkMessage) objectInputStream.readObject();
                    System.out.println("Server response: " + receivedMessage.getText());
                }
            }
        } else {
            System.out.println("Login failed.");
        }

        // Close the input and output streams and the socket
        System.out.println("Closing socket.");
        inputStream.close();
        outputStream.close();
        socket.close();
        sc.close();
    }

    public static boolean login(String username, String password) {
        System.out.println("FROM GUI, LOGIN CALLED WITH USERNAME AND PASSWORD " + username + " " + password);

        NetworkMessage loginMessage = new NetworkMessage();
        try {
            loginMessage.setType(MessageType.LOGIN);
            loginMessage.setLoginCredentials(username + "::" + password);
            objectOutputStream.writeObject(loginMessage);
            NetworkMessage receivedMessage = (NetworkMessage) objectInputStream.readObject();
            return receivedMessage.getStatus().equals(MessageStatus.SUCCESS);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: Unexpected end of stream");
        }
        return false;


        //gui.updateUI();
    }

    private void setGUI(GUI gui) {
        // TODO Auto-generated method stub
    }


}
