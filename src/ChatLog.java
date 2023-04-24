import java.util.*;

public class ChatLog {
	public List<ChatMessage> chatLog;
	public int numMessages;
	
	
	public ChatLog() { 
		numMessages = 0;
		chatLog = new ArrayList<ChatMessage>();
	}
	
	private void WriteChat(ChatMessage message, ChatMessage id, ChatMessage timeStamps) {
		chatLog.add(message);
		chatLog.add(id);
		chatLog.add(timeStamps);
		numMessages++;
		
	}
	
	private List<ChatMessage> readChat() {
		return chatLog;
	}

}
