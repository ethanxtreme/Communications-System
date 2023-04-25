import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;

public class FileServer {
    
    private static final String USERS_FILE = "Users.txt";
    private static final String THREADS_FILE = "Threads.txt";

    // Load users from Users.txt file
    public ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        // Read the Users.txt file
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split user data by comma
                String[] userData = line.split(",");
                // Create a new User object from the data
                User user = new User(
                        userData[0],
                        userData[1],
                        UserType.valueOf(userData[2]),
                        userData[3]
                );
                // Check if the user is an admin
                if (userData.length > 4 && userData[4].equals("true")) {
                    user.setAdmin();
                }
                // Add the user to the users list
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Return the users list
        return users;
    }
    
    // Load chat log from Threads.txt file
    public ArrayList<Thread> loadChatLog() {
        ArrayList<Thread> threads = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        // Read the Threads.txt file
        try (BufferedReader br = new BufferedReader(new FileReader(THREADS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split chat messages by a delimiter
                String[] chatMessageData = line.split("\\|");
                ArrayList<ChatMessage> messagesList = new ArrayList<>();
                for (String messageData : chatMessageData) {
                    String[] messageFields = messageData.split(",");
                    String messageId = messageFields[0];
                    String senderId = messageFields[1];
                    String[] recipientIds = messageFields[2].split(";");
                    String messageText = messageFields[3];
                    Date timeStamp = dateFormat.parse(messageFields[4]);

                    messagesList.add(new ChatMessage(messageId, senderId, recipientIds, messageText, timeStamp));
                }
                // Add the new Thread to the threads list
                threads.add(new Thread(messagesList.toArray(new ChatMessage[0])));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        // Return the threads list
        return threads;
    }
    
    
    // Save users to Users.txt file
    public void saveUsers(ArrayList<User> users) {
        // Write users data to Users.txt file
        try (PrintWriter pw = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                pw.println(
                        user.getId() + "," +
                        user.getUsername() + "," +
                        user.getType() + "," +
                        user.getPassword() + "," +
                        user.isAdmin()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    // Save chat log to Threads.txt file
    public void saveChatLog(ArrayList<Thread> chatLog) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        // Write the chat log to the Threads.txt file
        try (PrintWriter pw = new PrintWriter(new FileWriter(THREADS_FILE))) {
            for (Thread thread : chatLog) {
                StringBuilder messages = new StringBuilder();
                for (ChatMessage message : thread.getThread()) {
                    messages.append(message.getMessageId()).append(",")
                            .append(message.getSenderId()).append(",")
                            .append(String.join(";", message.getRecipientIds())).append(",")
                            .append(message.getMessageText()).append(",")
                            .append(dateFormat.format(message.getTimeStamp())).append("|");
                }
                // Remove the last delimiter '|' before writing to the file
                if (messages.length() > 0) {
                    messages.setLength(messages.length() - 1);
                }
                // Write the concatenated chat messages to the file
                pw.println(messages.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
       
}