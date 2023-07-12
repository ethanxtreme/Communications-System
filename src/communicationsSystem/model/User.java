package communicationsSystem.model;

public class User {
    // Fields
    private String userId; // TODO: Figure out what a userID is being used for
    private String username;
    private UserType userType;
    private String password;


    // default constructor
    public User() {
        userId = "";
        username = "";
        userType = UserType.USER;
        password = "";
    }

    public User(String userId, String username, UserType userType, String password) {
        this.userId = userId;
        this.username = username;
        this.userType = userType;
        this.password = password;
    }

    // GETTERS & SETTERS

    // id
    public String getId() {
        return userId;
    }

    public void setId(String id) {
        userId = id;
    }

    // username
    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        username = name;
    }

    // type
    public UserType getType() {
        return userType;
    }

    public void setType(UserType type) {
        userType = type;
    }

    // password
    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        password = pass;
    }


}
