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

        // Login
        System.out.print("Enter your username: ");
        String username = sc.next();
        
        System.out.print("Enter your password: ");
        String password = sc.next();

        String loginCredentials = username + "::" + password;

        NetworkMessage client_login = new NetworkMessage();
        client_login.setType(MessageType.LOGIN);
        client_login.setLoginCredentials(loginCredentials);
        objectOutputStream.writeObject(client_login);

        NetworkMessage receivedMessage = (NetworkMessage) objectInputStream.readObject();

        if (receivedMessage.getStatus().equals(MessageStatus.SUCCESS)) {
            System.out.println("Login successful.");

            // Main command loop
            boolean running = true;
            while (running) {
                System.out.print("\nEnter a command (SEND, HISTORY, LIST, LOGOUT): ");
                String command = sc.next().toUpperCase();

                switch (command) {
                    case "SEND":
                        // ... (unchanged code)
                        break;

                    case "HISTORY":
                        // ... (unchanged code)
                        break;

                    case "LIST":
                        // ... (unchanged code)
                        break;

                    case "LOGOUT":
                        // ... (unchanged code)
                        break;

                    default:
                        System.out.println("Invalid command. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("Login failed. Please try again.");
        }

        System.out.println("Closing socket");
        socket.close();
    }
}
