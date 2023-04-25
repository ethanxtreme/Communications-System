
public class AdminUser extends User {
	
	private ChatGroup[] chatGroups;
	
	public AdminUser(String userId, String username, UserType userType, String password) {
		super(userId, username, userType, password);
	}
	
	private void viewLogs() {
		ChatLog log = new ChatLog();
		log.readChat();
	}

}
