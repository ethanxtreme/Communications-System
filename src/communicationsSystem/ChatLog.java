package communicationsSystem;
import java.util.*;

import communicationsSystem.Thread;

public class ChatLog {
	public List<Thread> chatLog;
	public int numThreads;
	
	// Default Constructor
	public ChatLog() { 
		chatLog = new ArrayList<Thread>();
		numThreads = 0;
	}
	
	// Constructor
	public ChatLog(ArrayList<Thread> chatLog) { 
		this.chatLog = chatLog;
		numThreads = chatLog.size();
	}
	
	
	// Add new thread to chat log
	public void addToLog(Thread thread) {
		chatLog.add(thread);
		numThreads++;
	}
	
	
	public List<Thread> getLog() {
		return chatLog;
	}

	public int getNumThreads() {
		// TODO Auto-generated method stub
		return this.numThreads;
	}

	public void addMessage(NetworkMessage message, String[] recipients) {
		// Check if the thread already exists                                                                         
		Thread existingThread = null;                                                               // TODO Auto-generated method stub
		for (int i = 0; i < numThreads; i++) {                                         
			Thread thread = getLog().get(i);                                                
        	String[] threadRecipients = thread.getRecipientIds();                                   
       	    if (Arrays.equals(threadRecipients, recipients)) { //not sure if this is the right check
       	    	existingThread = thread;                                                            
        	    break;                                                                              
        	}                                                                                       
        }                                                                                           
                                                                                                                      
        if (existingThread == null) {                                                               
        // If the thread doesn't exist, create a new thread and add it to the chat log          
        Thread newThread = new Thread();                                                        
        newThread.addMessage(message.getChatMessage());                                         
        addToLog(newThread);                                                            
                                                                                                                      
        } else {                                                                                    
        	// If the thread already exists, add the message to the existing thread                 
        	existingThread.addMessage(message.getChatMessage());                                    
        	// Update the chat log with the modified thread                                         
        	//modifyLog(existingThread); //this method does not exist yet                           
        } 
	}
}