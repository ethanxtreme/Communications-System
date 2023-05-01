package Testing;
import communicationsSystem.Thread;
import communicationsSystem.User;
import communicationsSystem.UserType;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

//import org.junit.jupiter.api.Test;
import org.junit.Test;

public class UserTester {
	
	User test_user = new User();
	UserType type = null;
	User test_user1 = new User("000", "mb123", type.USER, "12345");
	
	@Test
    public void testUser() { //default constructor
		System.out.println("testUser id" + test_user.getId());
		System.out.println("testUser username" + test_user.getUsername());
		System.out.println("testUser password" + test_user.getPassword());
		System.out.println("testUser type" + test_user.getType());
    }
	
	@Test
    public void testUserConstructor() { //constructor
		assertEquals("000", test_user1.getId());
		assertEquals("mb123", test_user1.getUsername());
		assertEquals(type.USER, test_user1.getPassword());
    }
	
	@Test
	public void testsetId() {
		test_user.setId("001");
		assertEquals("001", test_user.getId());
	}
	
	@Test
	public void testsetUsername() {
		test_user.setUsername("hi123");
		assertEquals("hi123", test_user.getUsername());
	}
	
	@Test
	public void testsetType() {
		test_user.setType(type.USER);
		assertEquals(type.USER, test_user.getUsername());
	}
	
	@Test
	public void testsetPassword() {
		test_user.setPassword("6789");
		assertEquals("6789", test_user.getPassword());
	}
	
	@Test
	public void testaddThread() {
		boolean trueBool = true;
		boolean falseBool = false;
		Thread test_thread = new Thread();
		ArrayList<Thread> test_threadarr = new ArrayList<Thread>();
		test_threadarr.add(test_thread);
		if(test_threadarr.contains(test_thread)) {
			assertTrue(trueBool);
		}
		else {
			assertTrue(falseBool);
		}
	}
	
	@Test
	public void testgetId() {
		assertEquals("000", test_user1.getId());
		
	}
	
	@Test
	public void testgetUsername() {
		assertEquals("mb123", test_user1.getUsername());
	}
	
	@Test
	public void testgetType() {
		assertEquals(type.USER, test_user1.getType());
	}
	
	@Test
	public void testgetPassword() {
		assertEquals("12345", test_user1.getPassword());
	}
	
	@Test
	public void testgetThreads() {
		Thread test_thread = new Thread();
		ArrayList<Thread> test_threadarr = new ArrayList<Thread>();
		test_threadarr.add(test_thread);
		assertEquals(test_threadarr, test_thread.getThread());
	}
}

