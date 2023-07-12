package communicationsSystem.network.client.helpers;

import communicationsSystem.network.NetworkMessage;
import communicationsSystem.network.client.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

// TODO: Message Receipt Acknowledgement
// Currently, server doesn't acknowledge the receipt of messages.
public class ClientNetwork implements Runnable {
    private final Client client;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    // Constructor
    public ClientNetwork(Client client) {
        this.client = client;
        // Connect to the server and initialize the streams
        try {
            int port = 1234;
            InetAddress localhost = InetAddress.getLocalHost();
            String host = localhost.getHostAddress().trim();
            Socket socket = new Socket(host, port);
            System.out.println("Connected to " + host + ":" + port); // debug

            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            // TODO: Handle exceptions as needed

        }
    }

    // TODO: Method to send a message to the server
    public void sendMessage(NetworkMessage message) {
        try {
            outputStream.writeObject(message);
        } catch (IOException e) {
            // TODO: Handle exceptions as needed

        }
    }

    // Method to read a message from the server
    public NetworkMessage readMessage() {
        NetworkMessage message = null;
        try {
            message = (NetworkMessage) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // TODO: Handle exceptions as needed
        }
        return message;
    }

    public NetworkMessage sendAndReceiveMessage(NetworkMessage messageToSend) {
        try {
            // Send the message to the server
            outputStream.writeObject(messageToSend);
            outputStream.flush();

            return (NetworkMessage) inputStream.readObject();
        } catch (IOException e) {
            // Handle I/O error when communicating with the server
            System.out.println("Error: Failed to communicate with the server");
        } catch (ClassNotFoundException e) {
            // Handle unexpected response from the server
            System.out.println("Error: Unexpected response from the server");
        }

        return null;
    }

    @Override
    public void run() {
        // Listen for messages from the server in a loop
        while (true) {
            NetworkMessage message = readMessage();
            if (message != null) {
                // TODO: Pass the message to the client to handle
                // client.handleMessage(message);
            }
        }
    }
}
