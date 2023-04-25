import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileServer {

    // other methods here

    public static List<User> getUsers(String filename) throws IOException {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {//while line read is not null
            	
            	//User Data is stored as:
            	//user_id, username, user_type, password

                String[] userData = line.split(",");
                String userId = userData[0];
                String username = userData[1];
                String password = userData[2];
                UserType userType = UserType.valueOf(userData[3].toUpperCase());

                User user = new User(userId, username, userType, password);
                users.add(user);
            }
        }

        return users;
    }

    // other methods here
    public static List<Thread> getThreads(String filename) throws IOException{
		return null;
    	
    }

}
