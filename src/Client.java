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
        System.out.println("IPV4 Address : " + 
                    (localhost.getHostAddress()).trim());
        String host = localhost.getHostAddress().trim();

        // Connect to the ServerSocket at host:port
        Socket socket = new Socket(host, port);
        System.out.println("Connected to " + host + ":" + port);
        
        // Output stream socket
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        
        //Input stream socket
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        
        NetworkMessage Received_Messages = (NetworkMessage) objectInputStream.readObject();
        
        NetworkMessage client_login = new NetworkMessage();
        client_login.setType(MessageType.LOGIN);
        objectOutputStream.writeObject(client_login);
        
        if(Received_Messages.getStatus().equals(MessageStatus.SUCCESS)) {
        	NetworkMessage client_text = new NetworkMessage();
        	client_text.setType(MessageType.TEXT);
        	objectOutputStream.writeObject(client_text);
        	if(Received_Messages.getStatus().equals(MessageStatus.SUCCESS)) {
        	     System.out.print("Enter message info.\n"); 
        	     String text_input = sc.next();
        	     if(!text_input.equals("logout") || !text_input.equals("LOGOUT")) {
        	    	 client_text.setText(text_input);
        	    	 objectOutputStream.writeObject(text_input);
        	     }
        	     else {
        	    	 client_text.setType(MessageType.LOGOUT);
        	    	 if(Received_Messages.getStatus().equals(MessageStatus.FAIL)) {
        	    		 System.out.println("Closing socket");
        	    		 inputStream.close();
        	    		 outputStream.close();
        	    		 socket.close();
        	    	 }
        	     }
        	     
        	}
        }
        
        System.out.println("Closing socket");
        socket.close();
        }
	
	
}
