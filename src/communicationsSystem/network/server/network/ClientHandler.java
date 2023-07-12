package communicationsSystem.network.server.network;

import communicationsSystem.model.ChatLog;
import communicationsSystem.model.MessageType;
import communicationsSystem.model.User;
import communicationsSystem.network.NetworkMessage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private static final ArrayList<ConnectedClient> connectedClients = new ArrayList<>();
    private final Socket socket;
    private final ArrayList<User> users;
    private User loggedInUser = null; // To store the currently logged-in user

    ClientHandler(Socket socket, ArrayList<User> users, ChatLog chatLog) {
        this.socket = socket;
        this.users = users;
    }

    // Adds a connected client to the list of connected clients
    public synchronized static void addConnectedClient(ConnectedClient connectedClient) {
        connectedClients.add(connectedClient);
    }

    // Removes a connected client from the list of connected clients
    public synchronized static void removeConnectedClient(ConnectedClient connectedClient) {
        connectedClients.remove(connectedClient);
    }

    @Override
    public void run() {
        System.out.println("Connected: " + socket);
        try {

            // Output stream socket
            OutputStream outputStream = null;
            try {
                outputStream = socket.getOutputStream();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            ObjectOutputStream objectOutputStream = null;
            try {
                objectOutputStream = new ObjectOutputStream(outputStream);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Input stream socket
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            // read the  messages from the socket
            NetworkMessage message = (NetworkMessage) objectInputStream.readObject();

            boolean running = true;
            while (running) {//wait for logout message before closing connection
                // TODO: login(), logout(), and getUserById() are commented out to avoid errors
                if (message.getType() == MessageType.LOGIN) {
                    //loggedInUser = login(message, objectOutputStream, users); // Set loggedInUser here

                    if (loggedInUser != null) {
                        // Add the connected client to the list of connected clients (Jesse's suggestion)
                        ConnectedClient connectedUser = new ConnectedClient(loggedInUser, objectOutputStream);
                        addConnectedClient(connectedUser);

                        connectedClients.add(new ConnectedClient(loggedInUser, objectOutputStream));

                    }
                } else if (message.getType() == MessageType.TEXT) {

                    // Get the recipient list from the message
                    //String[] recipients = message.getChatMessage().getRecipientIds();
                    ArrayList<User> userRecipients = new ArrayList<>();
                    //for (String recipient : recipients) {
                    //userRecipients.add(getUserById(recipient, users));
                }
                //String[] participants = Arrays.copyOf(recipients, recipients.length + 1);
                //participants[participants.length - 1] = message.getChatMessage().getSenderId();
                //update the chatlog with new message
                //chatLog.addMessage(message, participants);

                //Send the update request message to connected user recipients
                //sendMessage(userRecipients, message);


                else if (message.getType() == MessageType.LOGOUT) {
                    running = false;
                    // Close the connection with the client if they log out of the system
                    if (loggedInUser != null) {
                        // Remove the connected client from the list of connected clients (Jesse's suggestion)
                        ConnectedClient connectedUser = new ConnectedClient(loggedInUser, objectOutputStream);
                        removeConnectedClient(connectedUser);
                        // removeConnectedClient(loggedInUser);
                    }
                    //logout(objectOutputStream, socket, inputStream, outputStream);

                } else { //type == UNDEFINED
                    System.out.println("Error:" + socket);
                }
                message = (NetworkMessage) objectInputStream.readObject(); //wait for next incoming message

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    // TODO: Figure out how this will be used
    record ConnectedClient(User user, ObjectOutputStream outputStream) {
    }
}


