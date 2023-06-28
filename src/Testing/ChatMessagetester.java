package Testing;
import communicationsSystem.model.ChatMessage;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

//import org.junit.jupiter.api.Test;
import org.junit.Test;

public class ChatMessagetester {
	Date test_date = new Date();
    String[] test_recipentIDs = {"a", "b", "c"};
    ChatMessage message = new ChatMessage("123", "efgh",test_recipentIDs,"Hello", test_date);
    String[] emptyrecipentIDs = {"", "", ""};
	ChatMessage test_message = new ChatMessage("", "",emptyrecipentIDs, "", null);

	@Test
	public void testChatMessage() {
        assertEquals("123", message.getMessageId());
        assertEquals("efgh", message.getSenderId());
        assertEquals(test_recipentIDs, message.getRecipientIds());
        assertEquals("Hello", message.getMessageText());
        assertEquals(test_date, message.getTimeStamp());
	}

	@Test
	public void testGetMessageId() {
		System.out.println("test getMessageID:" + message.getMessageId());
		assertEquals("123", message.getMessageId());
	}

	@Test
	public void testGetSenderId() {
		System.out.println("test getSenderID:" + message.getSenderId());
		assertEquals("efgh", message.getSenderId());
	}

	@Test
	public void testGetRecipientIds() {
		System.out.println("test getRecipentID:" + message.getRecipientIds());
		assertEquals(test_recipentIDs, message.getRecipientIds());
	}

	@Test
	public void testGetMessageText() {
		System.out.println("test getMesageText:" + message.getMessageText());
		assertEquals("Hello", message.getMessageText());
	}

	@Test
	public void testGetTimeStamp() {
		System.out.println("test gettimestamp:" + message.getTimeStamp());
		assertEquals(test_date, message.getTimeStamp());
	}

	@Test
	public void testSetMessageId() {
		String messageID = "456";
		test_message.setMessageId(messageID);
		System.out.println("test setmessageid" + test_message.getMessageId());
		assertEquals("456", test_message.getMessageId());
	}

	@Test
	public void testSetSenderId() {
		String senderID = "hij";
		test_message.setMessageId(senderID);
		System.out.println("test setsenderid" + test_message.getSenderId());
		assertEquals("hij", test_message.getSenderId());
	}

	@Test
	public void testSetRecipientIds() {
		String[] populatedrecipetIDs = {"a", "b", "c"};
		test_message.setRecipientIds(populatedrecipetIDs);
		System.out.println("test setrecipientIDs" + test_message.getRecipientIds());
		assertEquals(populatedrecipetIDs, test_message.getRecipientIds());
	}

	@Test
	public void testSetMessageText() {
		String test_text = "How are you?";
		test_message.setMessageText(test_text);
		System.out.println("test setmessagetext" + test_message.getMessageText());
		assertEquals(test_text, test_message.getMessageText());
	}

	@Test
	public void testSetTimeStamp() {
		Date other_testdate = new Date();
		test_message.setTimeStamp(other_testdate);
		System.out.println("test settimestamp" + test_message.getTimeStamp());
		assertEquals(other_testdate, test_message.getTimeStamp());
		
	}

}
