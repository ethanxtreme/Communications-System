package Testing;
import communicationsSystem.network.NetworkMessage;
import communicationsSystem.model.MessageType;
import communicationsSystem.model.MessageStatus;
import communicationsSystem.model.ChatMessage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

//import org.junit.jupiter.api.Test;
import org.junit.Test;

public class NetworkMessagetester {
	
	NetworkMessage test_message = new NetworkMessage();
	MessageType test_type = null;
	MessageStatus test_status = null;
	NetworkMessage initial_testmessage = new NetworkMessage(test_type.LOGIN,test_status.SUCCESS,"Hello");

	@Test
	public void testNetworkMessage() { //tests default constructor
		System.out.println("test message type:" + test_message.getType());
		System.out.println("test message status:" + test_message.getStatus());
		System.out.println("test message text:" + test_message.getText());
	}

	@Test
	public void testNetworkMessageConstructor() { //tests constructor
		assertEquals(test_type.LOGIN, initial_testmessage.getType());
		assertEquals(test_status.SUCCESS, initial_testmessage.getStatus());
		assertEquals("Hello", initial_testmessage.getText());
	}

	@Test
	public void testSetType() {
		test_message.setType(test_type.LOGIN);
		assertEquals(test_type.LOGIN,test_message.getType());
	}

	@Test
	public void testSetStatus() {
		test_message.setStatus(test_status.SUCCESS);
		assertEquals(test_status.SUCCESS,test_message.getStatus());
	}

	@Test
	public void testSetLoginCredentials() {
		String login = "abcd";
		test_message.setLoginCredentials(login);
		assertEquals(login, test_message.getLoginCredentials());
	}

	@Test
	public void testGetLoginCredentials() {
		String login = "efgh";
		test_message.setLoginCredentials(login);
		assertEquals(login, test_message.getLoginCredentials());
	}

	@Test
	public void testSetText() {
		String test_text = "Hi";
		test_message.setText(test_text);
		assertEquals("Hi", test_message.getText());
		
	}

//	@Test
//	public void testSetChatMessage() {
//		String[] test_recipientIDs = {};
//		Date test_time = new Date();
//		ChatMessage testmessage = new ChatMessage("1", "abcd",test_recipientIDs, "Hello", test_time);
//		test_message.setChatMessage(testmessage);
//		assertEquals(testmessage, test_message.getChatMessage());
//	}

	@Test
	public void testGetType() {
		assertEquals(test_type.LOGIN, initial_testmessage.getType());
	}

	@Test
	public void testGetStatus() {
		assertEquals(test_status.SUCCESS, initial_testmessage.getStatus());
	}

	@Test
	public void testGetText() {
		assertEquals("Hello", initial_testmessage.getText());
	}

//	@Test
//	public void testGetChatMessage() {
//		String[] test_recipientIDs = {};
//		Date test_time = new Date();
//		ChatMessage testmessage = new ChatMessage("1", "abcd",test_recipientIDs, "Hello", test_time);
//		assertEquals(testmessage, test_message.getChatMessage());
//	}

}
