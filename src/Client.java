import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the port number to connect to: <1234>");
        int port = sc.nextInt();
        
        // Get the local host's IP address
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("IPV4 Address : " + (localhost.getHostAddress()).trim());
        String host = localhost.getHostAddress().trim();

        // Connect to the ServerSocket at host:port
        Socket socket = new Socket(host, port);
        System.out.println("Connected to " + host + ":" + port);

        // Output stream socket
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        // Input stream socket
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        NetworkMessage receivedMessage = new NetworkMessage(); // TODO edit this later, initialized for debugging

        // User login
        System.out.print("Enter your username: ");
        String username = sc.next();
        System.out.print("Enter your password: ");
        String password = sc.next();

        NetworkMessage loginMessage = new NetworkMessage();
        loginMessage.setType(MessageType.LOGIN);
        loginMessage.setLoginCredentials(username + "|" + password);
        objectOutputStream.writeObject(loginMessage);
        
        try {
	        // Wait for a response from the server indicating success or failure
	        receivedMessage = (NetworkMessage) objectInputStream.readObject();  // this is where an EOFExeption is thrown
        
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
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
                case "SEND":
                    System.out.print("Enter the recipient's username: ");
                    String recipient = sc.next();
                    System.out.print("Enter your message: ");
                    sc.nextLine(); // Consume the newline character
                    String messageText = sc.nextLine();

                    ChatMessage chatMessage = new ChatMessage(null, username, new String[]{recipient}, messageText, null);
                    commandMessage.setType(MessageType.TEXT);
                    commandMessage.setChatMessage(chatMessage);
                    objectOutputStream.writeObject(commandMessage);
                    break;
                case "MESSAGES":
                    commandMessage.setType(MessageType.MESSAGES);
                    objectOutputStream.writeObject(commandMessage);
                    break;
                case "LOGOUT":
                    commandMessage.setType(MessageType.LOGOUT);
                    objectOutputStream.writeObject(commandMessage);
                    running = false;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
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
    }
}
