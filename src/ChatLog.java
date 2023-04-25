import java.util.*;

public class ChatLog {
	public List<Thread> chatLog;
	public int numThreads;
	
	// Constructor
	public ChatLog() { 
		chatLog = new ArrayList<Thread>();
		numThreads = 0;
	}
	
	// Add new thread to chat log
	private void addToLog(Thread thread) {
		chatLog.add(thread);
		numThreads++;
	}
	
	
	private List<Thread> getLog() {
		return chatLog;
	}

}
