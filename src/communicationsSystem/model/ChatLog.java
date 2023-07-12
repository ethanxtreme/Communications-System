package communicationsSystem.model;

import java.util.ArrayList;
import java.util.List;

public class ChatLog {
    //private Map<String, Thread> chatLog;
    public List<Thread> chatLog;
    public int numThreads;

    // Default Constructor
    public ChatLog() {
        // chatLog = new HashMap<String, Thread>();
        chatLog = new ArrayList<Thread>();
        numThreads = 0;
    }

    // Constructor
    //public ChatLog(Map<String, Thread> chatLog)
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
        return this.numThreads;
    }

    // TODO: Redo implementation of addMessage
    // Quick notes:
    // - Don't want to use NetworkMessage, want to use ChatMessage
    // - addMessage() should only be possible on existing threads
//	public void addMessage(NetworkMessage message, String[] participants) {
//		// Check if the thread already exists
//		Thread existingThread;
//		existingThread = null;
//		for (int i = 0; i < numThreads; i++) {
//			Thread thread = getLog().get(i);
//        	ArrayList<User> threadParticipants = thread.getParticipants();
//       	    	if (Arrays.equals(threadParticipants, participants)) { //not sure if this is the right check
//       	    	existingThread = thread;
//        	    break;
//        	}
//        }
//
//        if (existingThread == null) {
//        // If the thread doesn't exist, create a new thread and add it to the chat log
//        Thread newThread = new Thread();
//        newThread.addMessage(message.getChatMessage());
//        addToLog(newThread);
//
//        } else {
//        	// If the thread already exists, add the message to the existing thread
//        	existingThread.addMessage(message.getChatMessage());
//        	// Update the chat log with the modified thread
//        	//modifyLog(existingThread); //this method does not exist yet
//        }
//	}
}
