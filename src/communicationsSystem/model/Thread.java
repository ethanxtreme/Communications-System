package communicationsSystem.model;
import communicationsSystem.model.ChatMessage;

import java.util.ArrayList;
import java.util.Arrays;

public class Thread {
	
	private int numMessages;
	
	// array of messages
	ArrayList<ChatMessage> chatMessages = new ArrayList<>();
	String[] participantIds = null;
	
	// constructors
	public Thread() {
		numMessages = 0;
	}
	
	public Thread(ChatMessage[] messages) {
		chatMessages.addAll(Arrays.asList(messages));
		
		// this will save the current number of messages
		numMessages = chatMessages.size();
	}
	
	
	// other methods
	
	// Add new message to my TV
	public void addMessage(ChatMessage message) {
		// Add the new message to the end of the array
	    chatMessages.add(message);
	    
	    // Increment the number of messages
	    numMessages++;
	}	
	
	public void addParticipants(String[] participants) {
		this.participantIds = participants;
	}
	
	// returns number of messages in thread
	public int getNumMessages() {
		return numMessages;
	}
	
	// returns the entire thread
	public ArrayList<ChatMessage> getThread() {
	    return chatMessages;
	}

	public String[] getParticipantIds() {
		return this.participantIds;
	}
	
}
