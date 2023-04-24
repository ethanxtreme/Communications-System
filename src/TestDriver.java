// driver for testing use cases

public class TestDriver {

	public static void main(String[] args) {
		
		// START TESTING user.java FUNCTIONS //
		
		String temp;
		User defaultUser;
		User testUser;
//		ChatGroup[] testGroups = new ChatGroup[8];
//		for (int i=0; i<5; i++) {
//			temp = "Group" + Integer.toString(i);
//			testGroups[i] = new ChatGroup(temp);
//		}
		
		System.out.println("Creating default user (no output)");
		defaultUser = new User();
		
		System.out.println("Creating user with all fields set: ");
		testUser = new User("testId", "User123", UserType.USER, "Password777", testGroups);
		
		System.out.println("ID, USERNAME, USERTYPE, PASSWORD, GROUPS");
		System.out.println(testUser.getId() + " " + testUser.getUsername() + " " + testUser.getType() + " " +  
							testUser.getPassword() + ", " + "Groups:");
//		testUser.printGroups();
		
		System.out.println("Testing Login:");
		testUser.login("Password777");
		
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		
		//  END TESTING user.java FUNCTION  //

	}

}
