package communicationsSystem;


// Communications system GUI 

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;


// the only solution I think might work is connecting the GUI to the server so that it can have
// access to functions and info that server contains

// also because I need to know what user is sending the message in order to write the name of
// the sender besides each message

public class GUI {
    static String loggedInUser;
    static Client client = null;
    static boolean isAdmin = false;
    static String username;

    public GUI(String window, boolean isAdmin, Client passedClient) {

        client = passedClient;
        GUI.isAdmin = isAdmin;

        if (window.equals("login")) {
            createLoginWindow();
        } else {
            createMessageWindow();
        }

    }

    private static void createLoginWindow() {

        JFrame frame = new JFrame("Log in");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createLoginUI(frame);

        frame.setSize(360, 200);
        frame.setLocationRelativeTo(null);  // Center on screen
        frame.setVisible(true);    // make visible

    }

    private static void createMessageWindow() {
        JFrame frame = new JFrame("Messaging");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createMessageUI(frame);
        frame.setSize(800, 680);
        frame.setLocationRelativeTo(null);  // Center on screen
        frame.setVisible(true);    // make visible
    }

    private static void createLoginUI(final JFrame frame) {

        JPanel panel = new JPanel(new GridBagLayout());

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameText = new JTextField(20); // create a textfield for entering username

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordText = new JPasswordField(20); // textfield for password
        JButton loginButton = new JButton("Log In");
        JButton exitButton = new JButton("Exit");

        // adding action listeners to buttons
        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Quitting Application.");
            System.exit(0);    // End program
        });

        loginButton.addActionListener(e -> {
            String name = usernameText.getText();
            char[] passArray = passwordText.getPassword();

            // convert char[] to String
            String pass = new String(passArray);

            // TODO debug message
//				JOptionPane.showMessageDialog(frame, "Logging in with username -> password = " + name + " -> " + pass);
            // TODO here, once a login function with username and password as parameters is
            // created, just pass name and pass into the function
            boolean success = Client.login(name, pass);
            username = name;
            if (success) {
                // set variable to username entered
                loggedInUser = usernameText.getText();
                JOptionPane.showMessageDialog(frame, "Login successful!");
                createMessageWindow();
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Login failed. Please check your username and password.");
            }

        });


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(usernameText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordText, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(loginButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(exitButton, constraints);


//	    frame.getRootPane().setDefaultButton(); //Default button focus
        // finally adds all items to frame
        frame.getContentPane().add(panel, BorderLayout.CENTER);

    }


    private static void createMessageUI(final JFrame frame) {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        /*
         * Actions:
         * - choose user to message
         * - choose group to message
         * - send message
         * - create a group
         * - view text logs (if admin)
         */

        /*
         * Utilities:
         * - scroll panel for reading through messages
         * - combo box for selecting conversations
         * - labels
         * - buttons
         * - text field
         */

        JButton createGroupButton = new JButton("Create Group");
        JButton viewLogsButton = new JButton("View Chat Logs");

        // check if user is admin
//	    if (!isAdmin) {
//	    	viewLogsButton.setEnabled(false);
//	    }

        JButton selectButton = new JButton("Select");

        // TODO: in real program, this will fill an array with names of other users and use that
        // as the parameter

        // TODO fill array list with names of users
        ArrayList<User> users = new ArrayList<>();


        // for testing
        String[] exampleUsers = {"Select a user to message:", "Jesse", "Samantha", "EJ", "Ethan", "Apurva"};
        String[] exampleGroups = {"Select a group conversation:", "Communication", "BlackJack", "Banking", "Class"};

        JComboBox<String> convosComboBox = new JComboBox<>(exampleUsers);
//	    convosComboBox.setSelectedIndex(4);

        JComboBox<String> groupsComboBox = new JComboBox<>(exampleGroups);

        // this will be the area where messages are shown
        JTextArea textArea = new JTextArea(5, 30);
        JScrollPane messagesScrollPane = new JScrollPane(textArea);
        messagesScrollPane.setPreferredSize(new Dimension(450, 400));

        // label for what conversation is being viewed
        String exampleConversation = "BlackJack";
        JLabel conversationLabel = new JLabel("Currently messaging: " + exampleUsers[convosComboBox.getSelectedIndex()]);

        // text field for sending a message
        JTextField messageText = new JTextField(45);

        JButton sendButton = new JButton("Send");

        selectButton.addActionListener(e -> conversationLabel.setText("Currently messaging: " + exampleUsers[convosComboBox.getSelectedIndex()]));

        sendButton.addActionListener(e -> {
            Date timestamp = new Date();
            String time = timestamp.toString();

            // get the userID TODO hardcoded for now
            User sender = new User("0001", loggedInUser, UserType.ADMIN, "1234abc");
            User recipient = new User("0002", "ej", UserType.ADMIN, "12345");

            String[] recipientIds = {recipient.getId()};

            String messageId = "1";

            ChatMessage message = new ChatMessage(messageId, sender.getId(), recipientIds, messageText.getText(), timestamp);

            // TODO this only creates the message, not actually sends it

            // TODO in reality, this should put the message into the thread and then write the entire
            // thread to the screen each time
            if (!Objects.equals(messageText.getText().strip(), "")) {
                textArea.setText(textArea.getText() + timestamp.getHours() + ":" + timestamp.getMinutes() + " " +
                        sender.getUsername() + ": " + messageText.getText() + "\n");
            }
            // deletes the text inside the message text field
            messageText.setText("");


            // Generate a response
            String responseText = "I'm busy right now. Can we talk later?";

            // Get the name of the user who received the message
            String recipientName = (String) convosComboBox.getSelectedItem(); // TODO: replace with actual name

            // Create a new message object
            textArea.setText(textArea.getText() + timestamp.getHours() + ":" + timestamp.getMinutes() +
                    " " + recipientName + ": " + responseText + "\n");
            //Message response = new Message(recipientName, responseText, new Date());


        });

        createGroupButton.addActionListener(e -> {
            JPanel panel1 = new JPanel(new GridBagLayout());

            GridBagConstraints constraints1 = new GridBagConstraints();
            constraints1.anchor = GridBagConstraints.WEST;
            constraints1.insets = new Insets(10, 10, 10, 10);
            JFrame frame1 = new JFrame("Create Group");

            JLabel groupLabel = new JLabel("Enter the name of the group:");
            JTextField groupTextField = new JTextField(20);
            JLabel label = new JLabel("Enter usernames of group members separated by commas:");
            JTextField textField = new JTextField(20);
            JButton button = new JButton("Create");


            button.addActionListener(e1 -> {
                String groupName = groupTextField.getText();
                String input = textField.getText();
                //chatGroups.add(new ChatGroup(groupName, usernames)); // assuming groupList is a List<Group>
                frame1.dispose();
            });


            constraints1.gridx = 0;
            constraints1.gridy = 0;
            panel1.add(groupLabel, constraints1);

            constraints1.gridx = 0;
            constraints1.gridy = 1;
            panel1.add(groupTextField, constraints1);

            constraints1.gridx = 1;
            constraints1.gridy = 0;
            panel1.add(label, constraints1);

            constraints1.gridx = 1;
            constraints1.gridy = 1;
            panel1.add(textField, constraints1);

            constraints1.gridx = 2;
            constraints1.gridy = 1;
            panel1.add(button, constraints1);


            frame1.getContentPane().add(panel1, BorderLayout.CENTER);

            frame1.setSize(800, 200);
            frame1.setLocationRelativeTo(null);  // Center on screen
            frame1.setVisible(true);    // make visible
        });

        viewLogsButton.addActionListener(e -> {
            //create a JFrame to display the chat logs
            JFrame logsFrame = new JFrame("Viewing All Chat Logs");

            //create a JTextArea to hold the chat logs
            JTextArea logsTextArea = new JTextArea();
            logsTextArea.setEditable(false);

            //add the logsTextArea to a JScrollPane to enable scrolling
            JScrollPane logsScrollPane = new JScrollPane(logsTextArea);

            //add the logsScrollPane to the logsFrame
            logsFrame.getContentPane().add(logsScrollPane);

            //set the size and visibility of the logsFrame
            logsFrame.setSize(500, 500);
            logsFrame.setVisible(true);

            //add some example chat logs to the logsTextArea
            logsTextArea.append("2023-05-01 10:15:00 Jesse: Hi, how are you?\n");
            logsTextArea.append("2023-05-01 10:16:00 Samantha: I'm good, thanks for asking. How about you?\n");
            logsTextArea.append("2023-05-01 10:17:00 Jesse: I'm doing well too. What have you been up to lately?\n");
            logsTextArea.append("2023-05-01 10:18:00 Samantha: Not much, just working on some projects. What about you?\n");
            logsTextArea.append("2023-05-01 10:19:00 Jesse: Same here, just trying to get some work done.\n");

        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(convosComboBox, constraints);

        //constraints.gridx = 0;
        constraints.gridx = 0;
        //constraints.gridy = 1;
        constraints.gridy = 1;

        panel.add(groupsComboBox, constraints);

        constraints.gridx = 1; //was 1
        constraints.gridy = 0; //was 0
//	    constraints.gridwidth = 2; //was not here
        panel.add(conversationLabel, constraints);

        constraints.gridx = 1; //was 1
        constraints.gridy = 1; //was 1
//	    constraints.gridwidth = 2; //was not here
        panel.add(messagesScrollPane, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(selectButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(createGroupButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(viewLogsButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(messageText, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        panel.add(sendButton, constraints);

        // finally adds all components to frame
        frame.getContentPane().add(panel, BorderLayout.CENTER);

    }


}
