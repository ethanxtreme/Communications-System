package Testing;

import communicationsSystem.model.User;
import communicationsSystem.model.UserType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

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
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("1", user.getId());
        assertEquals("john_doe", user.getUsername());
        assertEquals(UserType.USER, user.getType());
        assertEquals("password", user.getPassword());
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

}
