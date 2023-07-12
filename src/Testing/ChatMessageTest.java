package Testing;

import communicationsSystem.model.ChatMessage;
import communicationsSystem.model.User;
import communicationsSystem.model.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ChatMessageTest {
    User sender;
    User recipient1;
    User recipient2;
    ArrayList<User> recipients;
    Date timeStamp;
    ChatMessage testMessage;

    @Before
    public void setUp() {
        // Create some sample users
        sender = new User("id1", "John", UserType.USER, "password123");
        recipient1 = new User("id2", "Jane", UserType.USER, "pass456");
        recipient2 = new User("id3", "Bob", UserType.USER, "pass789");

        // Create an ArrayList and add the recipient users to it
        recipients = new ArrayList<>();
        recipients.add(recipient1);
        recipients.add(recipient2);

        // Create a timestamp for the message
        timeStamp = new Date();

        // Create a ChatMessage using the constructor
        testMessage = new ChatMessage("messageId123", sender, recipients, "Hello", timeStamp);
    }

    // Create an instance of the class under test using the constructor

    @Test
    public void testConstructorAndGetters() {
        // Assert that the values are correctly set by the constructor
        Assertions.assertEquals("messageId123", testMessage.getMessageId());
        Assertions.assertEquals(sender, testMessage.getSender());
        Assertions.assertEquals(recipients, testMessage.getRecipients());
        Assertions.assertEquals("Hello", testMessage.getMessageText());
        Assertions.assertEquals(timeStamp, testMessage.getTimeStamp());
    }

    @Test
    public void testSetMessageId() {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageId("123");

        Assertions.assertEquals("123", chatMessage.getMessageId());
    }

    @Test
    public void testSetSender() {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(sender);

        Assertions.assertEquals(sender, chatMessage.getSender());
    }

    @Test
    public void testSetRecipients() {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRecipients(recipients);

        Assertions.assertEquals(recipients, chatMessage.getRecipients());
    }

    @Test
    public void testSetMessageText() {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageText("Hello");

        Assertions.assertEquals("Hello", chatMessage.getMessageText());
    }

    @Test
    public void testSetTimeStamp() {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setTimeStamp(timeStamp);

        Assertions.assertEquals(timeStamp, chatMessage.getTimeStamp());
    }

}
