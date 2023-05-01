package Testing;
import communicationsSystem.ChatLog;
import communicationsSystem.Thread;

import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import java.util.*;
import org.junit.Assert;

public class ChatLogtester {

	@Test
	public void testChatLog() {
		ChatLog default_log = new ChatLog();
		System.out.println("Default log:" + default_log.getLog());
		System.out.println("Default number of threads:" + default_log.getNumThreads());
	}

	@Test
	public void testChatLogConstructor() {
		ChatLog log = new ChatLog();
		System.out.println("Log:" + log.getLog());
		System.out.println("Number of threads:" + log.getNumThreads());
	}

	@Test
	public void testAddToLog() {
		ArrayList<Thread>chatLog = new ArrayList<>();
		Thread thread = new Thread();
		boolean trueBool = true;
		boolean falseBool = false;
		if(chatLog.contains(thread)) {
			assertTrue(trueBool);
		}
		else {
			assertFalse(falseBool);
		}
	}

	@Test
	public void testGetLog() {
		ChatLog log = new ChatLog();
		ChatLog log_two = new ChatLog();
		assertEquals(log, log_two.getLog());
	}

	@Test
	public void testGetNumThreads() {
		ChatLog testlog = new ChatLog();
		testlog.numThreads = 0;
		assertEquals(0, testlog.getNumThreads());
	}

}
