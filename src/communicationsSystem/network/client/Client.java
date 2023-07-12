package communicationsSystem.network.client;

import communicationsSystem.model.MessageStatus;
import communicationsSystem.model.MessageType;
import communicationsSystem.network.NetworkMessage;
import communicationsSystem.network.client.helpers.ClientNetwork;
import communicationsSystem.network.client.ui.GUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * Client connects to listening server object
 * on connection, pass login message to server
 * server returns success status
 * client sends text message to server
 * client prompts user for text to send to server
 * user enters logout, send logout message to server
 */

// TODO: Make multi-threaded so client can listen for messages while waiting for user input
// TODO: Split into multiple classes

public class Client {
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;

    private static ClientNetwork clientNetwork;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // create a new instance of the Client class
        Client client = new Client();

        clientNetwork = new ClientNetwork(client);

        // TODO: Need to figure out GUI calling logic
        GUI gui = new GUI("login", false, client);
        client.setGUI(gui);

//        NetworkMessage receivedMessage = null;
//
//        try {
//            receivedMessage = (NetworkMessage) objectInputStream.readObject();
//        } catch (EOFException e) {
//            System.out.println("Error: Unexpected end of stream");
//        }
//
//        assert receivedMessage != null;
//        if (receivedMessage.getStatus().equals(MessageStatus.SUCCESS)) {
//            System.out.println("Login successful.");
//
//            // Main loop for handling user commands
//            boolean running = true;
//            while (running) {
//                System.out.println("Commands: SEND, MESSAGES, LOGOUT");
//                System.out.print("Enter command: ");
//                String command = sc.next();
//
//                NetworkMessage commandMessage = new NetworkMessage();
//                switch (command.toUpperCase()) {
//                    case "SEND" -> {
//                        System.out.print("Enter the recipient's username: ");
//                        String recipient = sc.next();
//                        System.out.print("Enter your message: ");
//                        sc.nextLine(); // Consume the newline character
//                        String messageText = sc.nextLine();
//                        ChatMessage chatMessage = new ChatMessage(null, username, new String[]{recipient}, messageText, null);
//                        commandMessage.setType(MessageType.TEXT);
//                        commandMessage.setChatMessage(chatMessage);
//                        objectOutputStream.writeObject(commandMessage);
//                    }
//                    case "MESSAGES" -> {
//                        commandMessage.setType(MessageType.MESSAGES);
//                        objectOutputStream.writeObject(commandMessage);
//                    }
//                    case "LOGOUT" -> {
//                        commandMessage.setType(MessageType.LOGOUT);
//                        objectOutputStream.writeObject(commandMessage);
//                        running = false;
//                    }
//                    default -> System.out.println("Invalid command. Please try again.");
//                }
//
//                // Wait for a response from the server and display it to the user
//                if (running) {
//                    receivedMessage = (NetworkMessage) objectInputStream.readObject();
//                    System.out.println("Server response: " + receivedMessage.getText());
//                }
//            }
//        } else {
//            System.out.println("Login failed.");
//        }
//
//        // Close the input and output streams and the socket
//        System.out.println("Closing socket.");
//        inputStream.close();
//        outputStream.close();
//        socket.close();
//        sc.close();
    }

    public static boolean login(String username, String password) {
        System.out.println("FROM GUI, LOGIN CALLED WITH USERNAME AND PASSWORD " + username + " " + password); // debug

        NetworkMessage loginMessage = new NetworkMessage();
        // Prepare the login message with the provided username and password
        loginMessage.setType(MessageType.LOGIN);
        loginMessage.setLoginCredentials(username + "::" + password);

        // Send the login message to the server and receive the response
        NetworkMessage receivedMessage = clientNetwork.sendAndReceiveMessage(loginMessage);

        // Check if the login was successful based on the received message status
        return receivedMessage != null && receivedMessage.getStatus().equals(MessageStatus.SUCCESS);
    }

    private void setGUI(GUI gui) {
        // Auto-generated method stub
    }

    private void closeStreams() {
        try {
            objectOutputStream.close();
            objectInputStream.close();
        } catch (IOException e) {
            // Handle I/O error when closing the streams
            System.out.println("Error: Failed to close the network streams");
        }
    }


}
