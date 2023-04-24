
public class User {

	// default value for number of chat groups allowed at first
	private final int DEFAULT_GROUPS = 8;
	
	private String userId;
	private String username;
	private UserType userType;
	private String password;
	private ChatGroup[] chatGroups;
	
	// default constructor
	public User() {
		userId = "";
		username = "";
		userType = UserType.USER; 
		password = "";
		chatGroups = new ChatGroup[DEFAULT_GROUPS];
	}
	
	public User(String userId, String username, UserType userType, String password, ChatGroup[] chatGroups) {
		this.userId = userId;
		this.username = username;
		this.userType = userType;
		this.password = password;
		this.chatGroups = chatGroups;
	}
	
	public void login(String userId, String password) {
		
	}
	
	// return type may need to be NetworkMessage, but for now void seems to make more sense
	public void message(NetworkMessage message, String recipientUsername) { 
		
	}
	
	public ChatGroup createGroup(User[] users, String name) {
		
		
		return null;
	}
	
	// should only be accessible if user is an ADMIN
	// for a GUI application, it's less necessary to code the UserType check in this class,
	// but rather have the option only available on screen in user is an ADMIN
	public void viewLogs() {
		
	}
	
}
