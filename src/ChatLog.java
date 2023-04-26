import java.util.*;

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

}
