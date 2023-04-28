package communicationsSystem;


// Communications system GUI 

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUI {
	public static void main(String[] args) {
	      createLoginWindow();
	   }
	
	private static void createLoginWindow() {    
	      JFrame frame = new JFrame("Log in");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      createLoginUI(frame);
	      frame.setSize(360, 200);      
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
	      panel.add(usernameText, constraints);
	      
	      constraints.gridx = 0;
	      constraints.gridy = 1;
	      panel.add(passwordLabel, constraints);
	      
	      constraints.gridx = 1;
	      panel.add(passwordText, constraints);
	      
	      constraints.gridx = 0;
	      constraints.gridy = 2;
	      panel.add(loginButton, constraints);
	      
	      constraints.gridx = 1;
	      panel.add(exitButton, constraints);
	      
	      
	   
	      
//	      frame.getRootPane().setDefaultButton(); //Default button focus

	      
	      frame.getContentPane().add(panel, BorderLayout.CENTER); 
	}
	
	
}
