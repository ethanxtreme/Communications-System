package Testing;
import communicationsSystem.model.ChatGroup;
import communicationsSystem.model.User;
import communicationsSystem.model.Thread;

import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.Test;
import org.junit.Test;

public class ChatGroupTester {
	
	String groupname = "Group1";

	@Test
	public void testSetGroupName() {
		assertEquals("Groupname", groupname);
	}

	@Test
	public void testGetGroupName() {
		ChatGroup group = new ChatGroup();
		group.setGroupName(groupname);
		assertEquals("Groupname", group.getGroupName());
		
	}

	@Test
	public void testAddUser() {
		boolean trueBool = true;
		boolean falseBool = false;
		ChatGroup user_list = new ChatGroup();
		User user = new User();
		user_list.addUser(user);
		if(user_list.contains(user)) {
			assertTrue(trueBool);
		}
		else {
			assertTrue(falseBool);
		}
	}

	@Test
	public void testRemoveUser() {
		boolean trueBool = true;
		boolean falseBool = false;
		ChatGroup user_list = new ChatGroup();
		User user = new User();
		user_list.removeUser(user);
		if(!user_list.contains(user)) { //meaning the user was successfully removed
			assertTrue(trueBool);
		}
		else {  //user was not successfully removed
			assertTrue(falseBool);
		}
	}

	@Test
	public void testSendGroupMessage() {
		ChatGroup test_group = new ChatGroup();
		Thread thread = new Thread();
		Thread test_thread = new Thread();
		assertEquals(thread, test_group.sendGroupMessage(thread));
	}

}
