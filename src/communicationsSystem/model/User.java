package communicationsSystem.model;

import java.util.ArrayList;

public class User {
    // Fields
    private String userId;
    private String username;
    private UserType userType;
    private String password;
    private ArrayList<Thread> threads = new ArrayList<>();


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

    public void addThread(Thread thread) {
        threads.add(thread);
    }

    // getters
    public String getId() {
        return userId;
    }

    // setters
    public void setId(String id) {
        userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        username = name;
    }

    public UserType getType() {
        return userType;
    }

    public void setType(UserType type) {
        userType = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        password = pass;
    }

    public ArrayList<Thread> getThreads() {
        return threads;
    }


    // TODO decide function/need of this method
    public void login(String password) {
        // debug message
        System.out.println("Login with password " + password);
    }

    // TODO decide function/need of this method
    // return type may need to be NetworkMessage, but for now void seems to make more sense
    public void message(ChatMessage message, String recipientUsername) {

        // debug message
        System.out.println("Message reading \"" + message.getMessageText() + "\" sent to " + recipientUsername);
    }

    // TODO decide function/need of this method
    // should only be accessible if user is an ADMIN
    // for a GUI application, it's less necessary to code the UserType check in this class,
    // but rather have the option only available on screen in user is an ADMIN
    public void viewLogs() {

        // TODO debug message
        System.out.println("View Logs successfully called.");
    }


}
