import java.io.*;
import java.util.ArrayList;

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