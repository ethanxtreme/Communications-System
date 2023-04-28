package communicationsSystem;


// Communications system GUI 

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUI {
	public static void main(String[] args) {
//	    createLoginWindow();
		createMessageWindow(true);   // boolean parameter is true for admin, false for other users
	   }
	
	private static void createLoginWindow() {    
		JFrame frame = new JFrame("Log in");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	    createLoginUI(frame);
	    frame.setSize(360, 200);      
	    frame.setLocationRelativeTo(null);  // Center on screen
	    frame.setVisible(true);	// make visible
	      
	   }
	
	private static void createMessageWindow(boolean isAdmin) {
		JFrame frame = new JFrame("Messaging");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createMessageUI(frame, isAdmin);	
		frame.setSize(900, 720);   
	    frame.setLocationRelativeTo(null);  // Center on screen
	    frame.setVisible(true);	// make visible
	}
	
	private static void createLoginUI(final JFrame frame){  
	    JPanel panel = new JPanel(new GridBagLayout());
	     
	    JLabel usernameLabel = new JLabel("Username:");
	    JTextField usernameText = new JTextField(20); // create a textfield for entering username
	      
	    JLabel passwordLabel = new JLabel("Password:");
	    JPasswordField passwordText = new JPasswordField(20); // textfield for password
	    JButton loginButton = new JButton("Log In");
	    JButton exitButton = new JButton("Exit");
	      
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
	
	private static void createMessageUI(final JFrame frame, boolean isAdmin) {
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
	    if (!isAdmin) {
	    	viewLogsButton.setEnabled(false);
	    }
	    
	    // TODO: in real program, this will fill an array with names of other users and use that 
	    // as the parameter
	    
	    // for testing
	    String [] exampleUsers = {"Select a user to message:", "Jesse", "Samantha", "EJ", "Ethan", "Apurva"};
	    String [] exampleGroups = {"Select a group conversation:", "Communication", "BlackJack", "Banking", "Class"};
	    
	    JComboBox convosComboBox = new JComboBox(exampleUsers);
//	    convosComboBox.setSelectedIndex(4);
	    
	    JComboBox groupsComboBox = new JComboBox(exampleGroups);
	    
	    // this will be the area where messages are shown
	    JTextArea textArea = new JTextArea(5, 30);
	    JScrollPane messagesScrollPane = new JScrollPane(textArea);
	    messagesScrollPane.setPreferredSize(new Dimension(450, 400));
	    
	    // label for what conversation is being viewed
	    String exampleConversation = "BlackJack";
	    JLabel conversationLabel = new JLabel("Currently messaging: " + exampleConversation);
	    
	    // text field for sending a message
	    JTextField messageText = new JTextField(45);
	    
	    JButton sendButton = new JButton("Send");
	    
	    // imagining a 10x10 grid 
	    // 
	    
	    constraints.gridx = 0;
	    constraints.gridy = 0;
	    panel.add(convosComboBox, constraints);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 1;
	    panel.add(groupsComboBox, constraints);
	    
	    constraints.gridx = 1;
	    constraints.gridy = 0;
	    panel.add(conversationLabel);
	    
	    constraints.gridx = 1;
	    constraints.gridy = 1;
	    panel.add(messagesScrollPane, constraints);
	    
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
