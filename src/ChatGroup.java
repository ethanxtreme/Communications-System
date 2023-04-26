import java.util.*;

public class ChatGroup {
	public String groupName;
	public ArrayList<User> user_list = new ArrayList<User>();
	
	public void setgroupname(String groupName) {
		this.groupName = groupName;
	}
	public String getgroupname() {
		return groupName;
	}
	
	public void addUser(User userId) {
		user_list.add(userId);
	}
	
	public void removeUser(User userId) {
		for(int i = 0; i < user_list.size(); i++) {
			if(user_list.get(i).equals(userId)) {
				user_list.remove(i);
			}
		}
	}
	
	public Thread sendGroupMessage(Thread message) {
		return message;
	}
}
