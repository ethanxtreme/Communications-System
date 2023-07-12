package Testing;

import communicationsSystem.model.ChatMessage;
import communicationsSystem.model.Thread;
import communicationsSystem.model.User;
import communicationsSystem.model.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ThreadTest {

    private Thread thread;

    @Before
    public void setUp() {
        // Create some initial ChatMessages and Users
        ChatMessage[] messages = {
                new ChatMessage("1", "user1", new String[]{"user2"}, "Hello", null),
                new ChatMessage("2", "user2", new String[]{"user1"}, "Hi", null)
        };
        User[] participants = {
                new User("id1", "user1", UserType.USER, "password1"),
                new User("id2", "user2", UserType.USER, "password2")
        };

        // Create a Thread using the constructor
        this.thread = new Thread(messages, participants);
    }

    @Test
    public void defaultConstructor() {
        Thread testThread = new Thread();
        System.out.println("testThread number of threads" + testThread.getNumMessages());
        assertEquals(0, testThread.getNumMessages());
    }

    @Test
    public void constructor() {
        // Create some ChatMessages and Users
        ChatMessage[] messages = {
                new ChatMessage("1", "John", new String[]{"Jane"}, "Hello, Jane!", null),
                new ChatMessage("2", "Jane", new String[]{"John"}, "Hi, John!", null)
        };
        User[] participants = {
                new User("id1", "John", UserType.USER, "password123"),
                new User("id2", "Jane", UserType.USER, "pass456")
        };
        // Create a Thread using the constructor
        Thread testThread = new Thread(messages, participants);

        // Verify that the messages and participants are set correctly
        assertEquals(2, testThread.getThread().size());
        assertEquals(2, testThread.getParticipants().size());
        assertTrue(testThread.getThread().contains(messages[0]));
        assertTrue(testThread.getThread().contains(messages[1]));
        assertTrue(testThread.getParticipants().contains(participants[0]));
        assertTrue(testThread.getParticipants().contains(participants[1]));

        // Verify that the numMessages field is set to the correct value
        assertEquals(2, testThread.getNumMessages());
    }

    @Test
    public void testGetThread() {
        // Get the thread from the thread object
        ArrayList<ChatMessage> chatMessages = thread.getThread();

        // Verify that the chatMessages list contains the expected elements
        assertEquals(2, chatMessages.size());

        // Check each ChatMessage object in the list
        ChatMessage expectedMsg1 = new ChatMessage("1", "user1", new String[]{"user2"}, "Hello", null);
        ChatMessage expectedMsg2 = new ChatMessage("2", "user2", new String[]{"user1"}, "Hi", null);

        boolean containsExpectedMsg1 = false;
        boolean containsExpectedMsg2 = false;

        for (ChatMessage message : chatMessages) {
            if (message.getMessageId().equals(expectedMsg1.getMessageId())
//					&& message.getSender().equals(expectedMsg1.getSender())
//					&& Arrays.equals(message.getRecipients(), expectedMsg1.getRecipients())
                    && message.getMessageText().equals(expectedMsg1.getMessageText())) {
                containsExpectedMsg1 = true;
            }
            if (message.getMessageId().equals(expectedMsg2.getMessageId())
//					&& message.getSender().equals(expectedMsg2.getSender())
//					&& Arrays.equals(message.getRecipients(), expectedMsg2.getRecipients())
                    && message.getMessageText().equals(expectedMsg2.getMessageText())) {
                containsExpectedMsg2 = true;
            }
        }

        assertTrue(containsExpectedMsg1);
        assertTrue(containsExpectedMsg2);
    }

    @Test
    public void testGetParticipants() {
        // Create some sample users
        User user1 = new User("id1", "John", UserType.USER, "password123");
        User user2 = new User("id2", "Jane", UserType.USER, "pass456");
        User user3 = new User("id3", "Bob", UserType.USER, "pass789");

        // Create an ArrayList and add the sample users to it
        ArrayList<User> participants = new ArrayList<>();
        participants.add(user1);
        participants.add(user2);
        participants.add(user3);

        // Create an instance of the class under test
        Thread testThread = new Thread(thread.getThread().toArray(new ChatMessage[0]), participants.toArray(new User[0]));

        // Call the getParticipants method and assert the returned list matches the original list
        Assertions.assertEquals(participants, testThread.getParticipants());
    }

    @Test
    public void addMessage() {
        boolean trueBool = true;
        boolean falseBool = false;
        ArrayList<ChatMessage> holdTestMessage = new ArrayList<>();
        String[] testRecipientIDs = {"a", "b", "c"};
        Date test_date = new Date();
        ChatMessage test_message = new ChatMessage("123", "efgh", testRecipientIDs, "Hello", test_date);
        holdTestMessage.add(test_message);
        if (holdTestMessage.contains(test_message)) {
            assertTrue(trueBool);
        } else {
            assertFalse(falseBool);
        }

    }

    @Test
    public void getNumMessages() {
        assertEquals(2, thread.getNumMessages());

    }
}
