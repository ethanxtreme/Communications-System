import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A server program that accepts message objects from clients, once a client sends 
 * a login message, the server then waits for text or logout messages, on receiving a
 * TEXT message, the server will save the message into a ChatLog, then 
 * send the direct or group messages out to the correct clients
 * on receiving a LOGOUT message, the server will close the socket associated with that thread
 */

public class Server {
	// List of connected clients
    private static ArrayList<User> connectedClients = new ArrayList<>();
	
    /**
     * Runs the sever, spawns new thread with client connection returns to listening
     * limits number of threads via thread pool, set to 100 for now
     */
	public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(1234)) {
            System.out.println("The Communications Server is running...");
            var pool = Executors.newFixedThreadPool(100);
            //load in user data and chatlog data data from files
            FileServer fileServer = new FileServer();
            ArrayList<User> users = fileServer.loadUsers();
            ChatLog chatLog = new ChatLog(fileServer.loadChatLog(users));
            
            while (true) {
                pool.execute(new CommunicationsServer(listener.accept(), users, chatLog));
            }
        }
    }
	
    private static class CommunicationsServer implements Runnable {
        private Socket socket;
        private ChatLog chatLog = new ChatLog();
        private ArrayList<User> users;
        private User loggedInUser = null; // To store the currently logged-in user

        CommunicationsServer(Socket socket, ArrayList<User> users, ChatLog chatLog) {
            this.socket = socket;
            this.users = users;
            this.chatLog = chatLog;
        }
        
        // Adds a connected client to the list of connected clients
        public synchronized static void addConnectedClient(User user) {
            connectedClients.add(user);
        }

        // Removes a connected client from the list of connected clients
        public synchronized static void removeConnectedClient(User user) {
            connectedClients.remove(user);
        }
        
        @Override
        public void run() {
            System.out.println("Connected: " + socket);
            try {

            	InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                
                // read the  messages from the socket
                NetworkMessage message = (NetworkMessage) objectInputStream.readObject();
                
                while(!message.getType().equals(MessageType.LOGOUT)) {//wait for logout message before closing connection
                	
	                if(message.getType() == MessageType.LOGIN) {
	                	//Validate login, send login success message to client to authenticate login
	                	login(message, objectOutputStream, users);
	        
	                	if (loggedInUser != null) {
                            // Add the connected client to the list of connected clients
                            addConnectedClient(loggedInUser);
                        }
	                }
	                else if(message.getType() == MessageType.TEXT) {
	                	
	                	// Get the recipient list from the message
	                	String[] recipients = message.getChatMessage().getRecipientIds();

	                	// Check if the thread already exists
	                	Thread existingThread = null;
	                	for (int i = 0; i < chatLog.getLog().size(); i++) {
	                	    Thread thread = chatLog.getLog().get(i);
	                	    String[] threadRecipients = thread.getRecipientIds();
	                	    if (Arrays.equals(threadRecipients, recipients)) { //not sure if this is the right check
	                	        existingThread = thread;
	                	        break;
	                	    }
	                	}

	                	if (existingThread == null) {
	                	    // If the thread doesn't exist, create a new thread and add it to the chat log
	                	    Thread newThread = new Thread();
	                	    newThread.addMessage(message.getChatMessage());
	                	    chatLog.addToLog(newThread);

	                	} else {
	                	    // If the thread already exists, add the message to the existing thread
	                	    existingThread.addMessage(message.getChatMessage());
	                	    // Update the chat log with the modified thread
	                	    //modifyLog(existingThread); //this method does not exist yet
	                	}

	                	//then send the text in the chatmessage to appropriate users
	                	NetworkMessage toSend = new NetworkMessage();
	                	toSend.setType(MessageType.TEXT);
	                	toSend.setStatus(MessageStatus.SUCCESS);
	                	objectOutputStream.writeObject(toSend);
	                	
	                }
	                else if(message.getType() == MessageType.LOGOUT) {
	                	// Close the connection with the client if they logout of the system
                        if (loggedInUser != null) {
                            removeConnectedClient(loggedInUser);
                        }

                        NetworkMessage failMessage = new NetworkMessage();
                        failMessage.setStatus(MessageStatus.FAIL);
                        objectOutputStream.writeObject(failMessage);

                        socket.close();
                        inputStream.close();
                        outputStream.close();
                        System.out.println("Closed: " + socket);
	                }
	                else { //type == UNDEFINED
	                    System.out.println("Error:" + socket);
	                }
	                message = (NetworkMessage) objectInputStream.readObject(); //wait for next incoming message
                }
                 
            } catch (Exception e) {
                System.out.println("Error:" + socket);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                System.out.println("Closed: " + socket);
            }
        }
    }
    
    public static Boolean authenticate(ArrayList<User> users, String username, String password) throws IOException {
    	
    	for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    	
    }		

    public static User login(NetworkMessage message, ObjectOutputStream objectOutputStream, ArrayList<User> users) throws IOException {
    	String credentials = message.getLoginCredentials();
    	String[] fields = credentials.split("::"); //stored as: username::password
        String username = fields[0];
        String password = fields[1];

        Boolean authenticate = authenticate(users, username, password);

        if (authenticate.equals(true)) {
            NetworkMessage successMessage = new NetworkMessage();
            successMessage.setStatus(MessageStatus.SUCCESS);
            objectOutputStream.writeObject(successMessage);

            // Find the user object for the authenticated user
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        } else {
            NetworkMessage failMessage = new NetworkMessage();
            failMessage.setStatus(MessageStatus.FAIL);
            objectOutputStream.writeObject(failMessage);
        }

        return null;
    }
}