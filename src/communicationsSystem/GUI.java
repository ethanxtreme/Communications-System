package communicationsSystem;


// Communications system GUI 

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GUI {
	public static void main(String[] args) {
	      createWindow();
	   }
	
	private static void createWindow() {    
	      JFrame frame = new JFrame("GUI");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	      createUI(frame);
	      frame.setSize(720, 480);      
	      frame.setLocationRelativeTo(null);  // Center on screen
	      frame.setVisible(true);	// make visible
	   }
	
	private static void createUI(final JFrame frame){  
	      JPanel panel = new JPanel();
	      LayoutManager layout = new FlowLayout();  // comment out: To try alternate layouts
	      //LayoutManager layout = new GridLayout();  // uncomment: To try alternate layouts
	      panel.setLayout(layout);       
	}
}
