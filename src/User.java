
public class User {

	// default value for number of chat groups allowed at first
	private final int DEFAULT_GROUPS = 8;
	
	private String userId;
	private String username;
	private UserType userType;
	private String password;
	private ChatGroup[] chatGroups;
	private int numGroups;
	
	// default constructor
	public User() {
		userId = "";
		username = "";
		userType = UserType.USER; 
		password = "";
		chatGroups = new ChatGroup[DEFAULT_GROUPS];
		numGroups = 0;
	}
	
	public User(String userId, String username, UserType userType, String password, ChatGroup[] chatGroups) {
		this.userId = userId;
		this.username = username;
		this.userType = userType;
		this.password = password;
		this.chatGroups = chatGroups;
		
		// count the number of groups
		for (int i=0; i<chatGroups.length; i++) {
			if (chatGroups[i] == null) {
				break;
			}
			numGroups++;
		}
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
	
	// method here for adding a ChatGroup to chatGroups[]
	public void addGroup(ChatGroup group) {
		
		// rehash group if numGroups = size
		if (numGroups == chatGroups.length) {
			ChatGroup[] newGroupArray = new ChatGroup[chatGroups.length*2];
			
			for (int i=0; i<numGroups; i++) {
				newGroupArray[i] = chatGroups[i];
			}
			
			chatGroups = newGroupArray;
		}
		
		// add the new group
		chatGroups[numGroups] = group;
		numGroups++;
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
	
	public ChatGroup[] getGroups() {
		return chatGroups;
	}
	
	
	public void login(String userId, String password) {
		
		// TODO debug message
		System.out.println("Login with userID: " + userId + " and password " + password);
	}
	
	// return type may need to be NetworkMessage, but for now void seems to make more sense
	public void message(NetworkMessage message, String recipientUsername) { 
		
		// TODO debug message
		System.out.println("Message reading \"" + message + "\" sent to " + recipientUsername);
	}
	
	public ChatGroup createGroup(User[] users, String name) {
		
		// TODO debug message
		System.out.print("Group named \"" + name + "\" created with users:");
		for (int i=0; i<users.length; i++) {
			if (users[i] == null) {break;}
			System.out.println(users[i]);
		}
		
		return null;
	}
	
	// should only be accessible if user is an ADMIN
	// for a GUI application, it's less necessary to code the UserType check in this class,
	// but rather have the option only available on screen in user is an ADMIN
	public void viewLogs() {
		
		// TODO debug message
		System.out.println("View Logs successfully called.");
	}
	
	
}
