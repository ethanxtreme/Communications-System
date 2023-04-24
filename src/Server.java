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


/**
 * A server program that accepts message objects from clients, once a client sends 
 * a login message, the server then waits for text or logout messages, on receiving a
 * TEXT message, the server will save the message into a ChatLog, then 
 * send the direct or group messages out to the correct clients
 * on receiving a LOGOUT message, the server will close the socket associated with that thread
 */
public class Server {

    /**
     * Runs the sever, spawns new thread with client connection returns to listening
     * limits number of threads via thread pool, set to 100 for now
     */
    public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(1234)) {
            System.out.println("The Communications Server is running...");
            var pool = Executors.newFixedThreadPool(100);
            while (true) {
                pool.execute(new CommunicationsServer(listener.accept()));
            }
        }
    }

    private static class CommunicationsServer implements Runnable {
        private Socket socket;

        CommunicationsServer(Socket socket) {
            this.socket = socket;
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
	                	NetworkMessage successMessage = new NetworkMessage();
	                	successMessage.setStatus(MessageStatus.SUCCESS);
	                	objectOutputStream.writeObject(successMessage);
	                	
	                }
	                else if(message.getType() == MessageType.TEXT) {
	                	
	                	//create some sort of array or list of users that the message 
	                	//needs to be sent to. i.e User[] UserArray = message.getChatMessage().getUsers();
	                	//then send the text in the chatmessage to appropriate users
	                	//will also have to save messages to chatlog as a thread at some point
	                	NetworkMessage toSend = new NetworkMessage();
	                	toSend.setType(MessageType.TEXT);
	                	toSend.setStatus(MessageStatus.SUCCESS);
	                	objectOutputStream.writeObject(toSend);
	                	
	                }
	                else if(message.getType() == MessageType.LOGOUT) {
	                	//close the thread connected with the client if they logout of the system
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
    
}
