
import java.util.Date;

public class Thread {
	
	private final int DEFAULT_MESSAGES = 20;
	private int numMessages;
	
	// array of messages
	private ChatMessage[] chatMessages;
	
	// constructors
	public Thread() {
		chatMessages = new ChatMessage[DEFAULT_MESSAGES];
		numMessages = 0;
	}
	
	public Thread(ChatMessage[] messages) {
		
		
		chatMessages = messages;
		
		// this will count the number of messages to save it into numMessages
		numMessages = 0;
		for (int i=0; i<chatMessages.length; i++) {
			if (chatMessages[i] == null) {
				break;
			}
			numMessages++;
		}
	}
	
	// other methods
	public void addMessage(ChatMessage message) {
		// this is assuming ChatMessage class already stores the timestamp of the message
		// rehash if message array is full
		if (numMessages == chatMessages.length) {
			ChatMessage[] newMessageArray = new ChatMessage[chatMessages.length*2];
			
			for (int i=0; i<numMessages; i++) {
				newMessageArray[i] = chatMessages[i];
			}
			
			chatMessages = newMessageArray;
		}
		
		chatMessages[numMessages] = message;
		numMessages++;
	}
	
	// returns the entire thread
	public ChatMessage[] getThread() {
		return chatMessages;
	}
	
}
