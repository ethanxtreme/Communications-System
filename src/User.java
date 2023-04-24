
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
	
	public User(String userId, String username, UserType userType, String password, Chatgroup[] chatGroups) {
		this.userId = userId;
		this.username = username;
		this.userType = userType;
		this.password = password;
		this.chatGroups = chatGroups;
	}
	
}
