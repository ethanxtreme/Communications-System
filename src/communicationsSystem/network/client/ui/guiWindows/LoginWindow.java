package communicationsSystem.network.client.ui.guiWindows;

import communicationsSystem.network.client.Client;

import javax.swing.*;
import java.awt.*;


public class LoginWindow {
    private static JFrame frame;
    private static Client client;

    public LoginWindow(Client client) {
        LoginWindow.client = client;
        createLoginWindow();
    }

    private static void createLoginUI() {

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
//				JOptionPane.showMessageDialog(frame, "Logging in with username -> password = " + name + " -> " + pass);
            // once a login function with username and password as parameters is
            // created, just pass name and pass into the function
            boolean success = Client.login(name, pass);
            if (success) {
                // set variable to username entered
                //loggedInUser = usernameText.getText();
                // JOptionPane.showMessageDialog(frame, "Login successful!");
                new MessageWindow(client);
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

    private void createLoginWindow() {
        frame = new JFrame("Log in");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createLoginUI();

        frame.setSize(500, 350);
        frame.setLocationRelativeTo(null);  // Center on screen
        frame.setVisible(true);    // make visible
    }
}
