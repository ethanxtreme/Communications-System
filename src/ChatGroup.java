import java.util.*;

public class ChatGroup {
	public String groupName;
	public List<Thread> members;
	private ChatLog log_obj;
	
	public ChatGroup() {
		members = new ArrayList<Thread>();
		setgroupname(groupName);
	}
	
	public void setgroupname(String groupName) {
		this.groupName = groupName;
	}
	public String getgroupname() {
		return groupName;
	}
	
	public void addUser(Thread userId) {
		members.add(userId);
	}
	
	public void removeUser(Thread userId) {
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).equals(userId)) {
				members.remove(i);
			}
		}
	}
	
	public Thread sendGroupMessage(Thread message) {
		log_obj = new ChatLog();
		log_obj.chatLog.add(message);
		return message;
		
	}

}
