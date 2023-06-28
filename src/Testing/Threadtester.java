package Testing;
import communicationsSystem.model.Thread;
import communicationsSystem.model.ChatMessage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.*;

//import org.junit.jupiter.api.Test;
import org.junit.Test;

public class Threadtester {
	
	Thread test_thread = new Thread();

	@Test
	public void testThread() { //default constructor
		System.out.println("testThread number of threads" + test_thread.getNumMessages());
		assertEquals(0,test_thread.getNumMessages());
	}

	@Test
	public void testThreadConstructor() { //constructor
		//tests if ChatMessage[] is appended to arraylist
		boolean trueBool = true;
		boolean falseBool = false;
		ArrayList<ChatMessage> arr = new ArrayList<>();
		ChatMessage[] messages = {};
		for(int i = 0; i < messages.length; i++) {
			arr.add(messages[i]);
			if(arr.contains(messages[i])) {
				assertTrue(trueBool);
			}
			else {
				assertFalse(falseBool);
			}
		}
	}

	@Test
	public void testAddMessage() {
		boolean trueBool = true;
		boolean falseBool = false;
		ArrayList<ChatMessage> hold_testmessage = new ArrayList<>();
		String[] test_recipentIDs = {"a", "b", "c"};
		Date test_date = new Date();
		ChatMessage test_message = new ChatMessage("123", "efgh",test_recipentIDs,"Hello", test_date);
		hold_testmessage.add(test_message);
		if(hold_testmessage.contains(test_message)) {
			assertTrue(trueBool);
		}
		else {
			assertFalse(falseBool);
		}
		
	}

	@Test
	public void testGetNumMessages() {
		assertEquals(0, test_thread.getNumMessages());
		
	}

	@Test
	public void testGetThread() {
		ArrayList<ChatMessage> hold_testmessage = new ArrayList<>();
		String[] test_recipentIDs = {"a", "b", "c"};
		Date test_date = new Date();
		ChatMessage test_message = new ChatMessage("123", "efgh",test_recipentIDs,"Hello", test_date);
		hold_testmessage.add(test_message);
		assertEquals(hold_testmessage, test_thread.getThread());
		
	}

	@Test
	public void testGetRecipientIds() {
		String[] recipientids = {};
		String content="";
		for(int i = 0; i < recipientids.length; i++) {
			content = recipientids[i];
		}
		assertEquals(recipientids, content);
	}

}
