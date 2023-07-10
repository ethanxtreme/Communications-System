package Testing;

import communicationsSystem.model.ChatMessage;
import communicationsSystem.model.User;
import communicationsSystem.model.UserType;
import communicationsSystem.model.Thread;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class UserTester {

	private User user;

	@Before
	public void setUp() {
		user = new User("1", "john_doe", UserType.USER, "password");
	}

	@Test
	public void testDefaultConstructor() {
		User defaultUser = new User();
		assertEquals("", defaultUser.getId());
		assertEquals("", defaultUser.getUsername());
		assertEquals(UserType.USER, defaultUser.getType());
		assertEquals("", defaultUser.getPassword());
		assertEquals(new ArrayList<>(), defaultUser.getThreads());
	}

	@Test
	public void testParameterizedConstructor() {
		assertEquals("1", user.getId());
		assertEquals("john_doe", user.getUsername());
		assertEquals(UserType.USER, user.getType());
		assertEquals("password", user.getPassword());
		assertEquals(new ArrayList<>(), user.getThreads());
	}

	@Test
	public void testSetId() {
		user.setId("2");
		assertEquals("2", user.getId());
	}

	@Test
	public void testSetUsername() {
		user.setUsername("jane_doe");
		assertEquals("jane_doe", user.getUsername());
	}

	@Test
	public void testSetType() {
		user.setType(UserType.ADMIN);
		assertEquals(UserType.ADMIN, user.getType());
	}

	@Test
	public void testSetPassword() {
		user.setPassword("new_password");
		assertEquals("new_password", user.getPassword());
	}

	@Test
	public void testAddThread() {
		Thread thread = new Thread();
		user.addThread(thread);
		assertTrue(user.getThreads().contains(thread));
	}

	@Test
	public void testLogin() {
		// Call the login method
		user.login("password");
	}

	@Test
	public void testMessage() {
		String messageId = "123";
		String senderId = "user1";
		String[] recipientIds = {"user2", "user3"};
		String messageText = "Hello, world!";
		Date timeStamp = new Date();
		ChatMessage chatMessage = new ChatMessage(messageId, senderId, recipientIds, messageText, timeStamp);
		String recipientUsername = "jane_doe";
	}

	@Test
	public void testViewLogs() {
		// Call the viewLogs method
		user.viewLogs();
	}
}
