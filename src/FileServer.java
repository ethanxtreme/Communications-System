import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;

public class FileServer {
    
    private static final String USERS_FILE = "src/Users.txt";
    private static final String THREADS_FILE = "src/Threads.txt";

    // Load users from Users.txt file
    public ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        // Read the Users.txt file
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split user data by comma
                String[] userData = line.split(",");
                
                // added this line so that lines that are invalid in length are skipped
                if (userData.length != 4) {
                	continue;
                }
                
                // Create a new User object from the data
                User user = new User(
                        userData[0],
                        userData[1],
                        UserType.valueOf(userData[2].toUpperCase().strip()),
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
    public ArrayList<Thread> loadChatLog(ArrayList<User> users) {
        ArrayList<Thread> chatLog = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(THREADS_FILE))) {
            String line;
            Thread currentThread = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    // When a new line with two '|' characters is encountered, it indicates the start of a new thread
                    if (currentThread != null) {
                        // Add the previous thread to the chatLog list
                        chatLog.add(currentThread);
                    }
                    // Create a new Thread instance for the next thread
                    currentThread = new Thread();
                } else {
                    // Split the line into fields and parse the ChatMessage fields
                    String[] fields = line.split(",");
                    String messageId = fields[0];
                    String senderId = fields[1];
                    String[] recipientIds = fields[2].substring(1, fields[2].length() - 1).split("\\s*,\\s*");
                    String messageText = fields[3];
                    Date timeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(fields[4]);

                    // Create a new ChatMessage instance with the parsed fields
                    ChatMessage chatMessage = new ChatMessage(messageId, senderId, recipientIds, messageText, timeStamp);
                    currentThread.addMessage(chatMessage);

                    // Add the thread to the User objects
                    // Find the sender and add the currentThread to their threads list if not already present
                    User sender = findUserById(senderId, users);
                    if (sender != null && !sender.getThreads().contains(currentThread)) {
                        sender.getThreads().add(currentThread);
                    }
                    // Find the recipients and add the currentThread to their threads list if not already present
                    for (String recipientId : recipientIds) {
                        User recipient = findUserById(recipientId, users);
                        if (recipient != null && !recipient.getThreads().contains(currentThread)) {
                            recipient.getThreads().add(currentThread);
                        }
                    }
                }
            }
            // Add the last thread to the chatLog list
            if (currentThread != null) {
                chatLog.add(currentThread);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return chatLog;
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
    
    
    // HELPER FUNCTIONS
    
    // Find a User object based on the user ID
    private User findUserById(String userId, ArrayList<User> users) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
       
}