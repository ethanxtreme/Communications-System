package communicationsSystem.network.server;

import communicationsSystem.model.ChatLog;
import communicationsSystem.model.MessageStatus;
import communicationsSystem.model.MessageType;
import communicationsSystem.model.User;
import communicationsSystem.network.NetworkMessage;
import communicationsSystem.network.server.dataManagement.FileServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A server program that accepts message objects from clients, once a client sends
 * a login message, the server then waits for text or logout messages, on receiving a
 * TEXT message, the server will save the message into a ChatLog, then
 * send the direct or group messages out to the correct clients
 * on receiving a LOGOUT message, the server will close the socket associated with that thread
 */


public class Server {
    private static final ArrayList<ConnectedClient> connectedClients = new ArrayList<>(); // TODO: Remove once ClientHandler is implemented

    /**
     * Runs the sever, spawns new thread with client connection returns to listening
     * limits number of threads via thread pool, set to 100 for now
     */
    // TODO: Move network related logic to ServerNetwork class
    public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(1234)) {
            System.out.println("The Communications Server is running...");
            ExecutorService executor = Executors.newFixedThreadPool(100); //used a separate thread for each connected client for improved
            //var pool = Executors.newFixedThreadPool(100);
            //load in user data and chatlog data from files
            FileServer fileServer = new FileServer();
            ArrayList<User> users = fileServer.loadUsers();
            ChatLog chatLog = new ChatLog(fileServer.loadChatLog(users));

            while (true) {
                Socket socket = listener.accept();
                executor.submit(new CommunicationsServer(socket, users, chatLog));
                //pool.execute(new CommunicationsServer(listener.accept(), users, chatLog));
            }
        }
    }

    //

    // TODO: Move logic to ServerNetwork or MessageHandler class
    public static void sendMessage(ArrayList<User> userRecipients, NetworkMessage message) {
        //logic to send messages to connected recipients
        for (ConnectedClient client : connectedClients) {
            for (User recipient : userRecipients) {
                if (client.user().equals(recipient)) {
                    try (ObjectOutputStream sendTo = client.outputStream()) {
                        //then send the text in the chatmessage to appropriate users
                        NetworkMessage messageToSend = new NetworkMessage();
                        messageToSend.setType(MessageType.TEXT);
                        messageToSend.setStatus(MessageStatus.SUCCESS);
                        messageToSend.setText(message.getChatMessage().getMessageText());
                        try {
                            sendTo.writeObject(messageToSend);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }

    // TODO: Move logic to LoginManager class
    // need to implement a new way to do this
    public static User login(NetworkMessage message, ObjectOutputStream objectOutputStream, ArrayList<User> users) throws IOException {
        String credentials = message.getLoginCredentials();
        String[] fields = credentials.split("::"); //stored as: username::password
        String username = fields[0];
        String password = fields[1];

        Boolean authenticate = authenticate(users, username, password);

        if (authenticate.equals(true)) {
            NetworkMessage successMessage = new NetworkMessage();
            successMessage.setType(MessageType.LOGIN);
            successMessage.setStatus(MessageStatus.SUCCESS);
            objectOutputStream.writeObject(successMessage);

            return getUserByUsername(username, users); // Return the authenticated user
        } else {
            NetworkMessage failMessage = new NetworkMessage();
            failMessage.setType(MessageType.LOGIN);
            failMessage.setStatus(MessageStatus.FAIL);
            objectOutputStream.writeObject(failMessage);
            return null; // Return null if authentication fails
        }
    }

    // TODO: Move logic to LoginManager class
    // need to implement a new way to do this
    public static Boolean authenticate(ArrayList<User> users, String username, String password) {
        System.out.println("Attempting to authenticate user: " + username + " with password: " + password);
        for (User user : users) {
            System.out.println("Comparing with user: " + user.getUsername() + " with password: " + user.getPassword());
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Authentication successful");
                return true;
            }
        }
        System.out.println("Authentication failed");
        return false;
    }

    // TODO: Move logic to LoginManager class
    // need to implement a new way to do this
    public static void logout(ObjectOutputStream objectOutputStream, Socket socket, InputStream inputStream,
                              OutputStream outputStream) {
        NetworkMessage failMessage = new NetworkMessage();
        failMessage.setStatus(MessageStatus.FAIL);
        try {
            objectOutputStream.writeObject(failMessage);

            socket.close();
            inputStream.close();
            outputStream.close();
            System.out.println("Closed: " + socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: Decide if needed, if not delete
    public static User getUserByUsername(String username, ArrayList<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // TODO: Decide if needed, if not delete
    public static User getUserById(String userId, ArrayList<User> users) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    // TODO: Move logic to ClientHandler class
    private static class CommunicationsServer implements Runnable {
        private final Socket socket;
        private final ArrayList<User> users;
        private ChatLog chatLog;
        private User loggedInUser = null; // To store the currently logged-in user

        CommunicationsServer(Socket socket, ArrayList<User> users, ChatLog chatLog) {
            this.socket = socket;
            this.users = users;
            this.chatLog = chatLog;
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
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                // Input stream socket
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                // read the  messages from the socket
                NetworkMessage message = (NetworkMessage) objectInputStream.readObject();

                boolean running = true;
                while (running) {//wait for logout message before closing connection

                    if (message.getType() == MessageType.LOGIN) {
                        loggedInUser = login(message, objectOutputStream, users); // Set loggedInUser here

                        if (loggedInUser != null) {
                            // Add the connected client to the list of connected clients (Jesse's suggestion)
                            ConnectedClient connectedUser = new ConnectedClient(loggedInUser, objectOutputStream);
                            addConnectedClient(connectedUser);

                            connectedClients.add(new ConnectedClient(loggedInUser, objectOutputStream));

                        }
                    } else if (message.getType() == MessageType.TEXT) {

                        // Get the recipient list from the message
                        ArrayList<User> recipients = message.getChatMessage().getRecipients();
                        ArrayList<User> userRecipients = new ArrayList<>();
                        for (User recipient : recipients) {
                            //userRecipients.add(getUserById(recipient, users));
                        }
//                        String[] participants = Arrays.copyOf(recipients, recipients.length + 1);
//                        participants[participants.length - 1] = message.getChatMessage().getSenderId();
                        //update the chatlog with new message
                        //chatLog.addMessage(message, participants);

                        //Send the update request message to connected user recipients
                        sendMessage(userRecipients, message);


                    } else if (message.getType() == MessageType.LOGOUT) {
                        running = false;
                        // Close the connection with the client if they log out of the system
                        if (loggedInUser != null) {
                            // Remove the connected client from the list of connected clients (Jesse's suggestion)
                            ConnectedClient connectedUser = new ConnectedClient(loggedInUser, objectOutputStream);
                            removeConnectedClient(connectedUser);
                            // removeConnectedClient(loggedInUser);
                        }
                        logout(objectOutputStream, socket, inputStream, outputStream);

                    } else { //type == UNDEFINED
                        System.out.println("Error:" + socket);
                    }
                    message = (NetworkMessage) objectInputStream.readObject(); //wait for next incoming message
                }

            } catch (Exception e) {
                System.out.println("Error:" + socket);
            } finally {
                try {
                    socket.close();
                } catch (IOException ignored) {
                }
                System.out.println("Closed: " + socket);
            }
        }
    }

    // TODO: Figure out how this will be used
    public record ConnectedClient(User user, ObjectOutputStream outputStream) {
    }
}
