import java.util.ArrayList;
import java.util.Arrays;

public class User {
	// Fields
	private String userId;
	private String username;
	private UserType userType;
	private String password;
	private ArrayList<Thread> threads = new ArrayList<>();
	private boolean admin;
	
	
	// default constructor
	public User() {
		userId = "";
		username = "";
		userType = UserType.USER; 
		password = "";
		admin = false;
	}
	
	public User(String userId, String username, UserType userType, String password) {
		this.userId = userId;
		this.username = username;
		this.userType = userType;
		this.password = password;
		admin = false;
	}
	
	// setters
	public void setId(String id) {
		userId = id;
	}
	
	public void setUsername(String name) {
		username = name;
	}
	
	public void setType(UserType type) {
		userType = type;
	}
	
	public void setPassword(String pass) {
		password = pass;
	}
	
	public void setAdmin() {
		admin = true;
	}
	
	// getters
	public String getId() {
		return userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public UserType getType() {
		return userType;
	}
	
	public String getPassword() {
		return password;
	}
	
	public ArrayList<Thread> getThreads() {
        return threads;
    }
	
	public boolean isAdmin() {
		return admin;
	}
	
	
	public void login(String password) {
		
		// TODO debug message
		System.out.println("Login with password " + password);
	}
	
	// return type may need to be NetworkMessage, but for now void seems to make more sense
	public void message(ChatMessage message, String recipientUsername) { 
		
		// TODO debug message
		System.out.println("Message reading \"" + message.getMessageText() + "\" sent to " + recipientUsername);
	}
	
	// should only be accessible if user is an ADMIN
	// for a GUI application, it's less necessary to code the UserType check in this class,
	// but rather have the option only available on screen in user is an ADMIN
	public void viewLogs() {
		
		// TODO debug message
		System.out.println("View Logs successfully called.");
	}
	
	
}
