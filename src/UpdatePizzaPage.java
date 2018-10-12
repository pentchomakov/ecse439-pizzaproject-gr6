import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UpdatePizzaPage extends JFrame  {
	
	private static UpdatePizzaPage frame;
	private Pizza pizza;
	
	public static void main(String[] args) {
		
	}
	
	// Receive a pizza to be modified and then
	public UpdatePizzaPage(Pizza pizza) {
		this.pizza = pizza;
	}

}
