import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPizzaPage extends JFrame{
	
	private JPanel contentPane;
	
	private static AddPizzaPage frame;
	
	private JButton addPizzaButton;
	
	private JTextField nameField;
	private JTextField ingredientField;
	private JTextField calorieField;
	
	private JLabel nameLabel;
	private JLabel ingredientLabel;
	private JLabel calorieLabel;
	
	public static void main(String[] args) {
		try {
			frame = new AddPizzaPage();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public AddPizzaPage()
	{
		InitializeComponents();
	}
	
	public void InitializeComponents()
	{
		int frameWidth = 500;
		int frameHeight = 350;
		
		setTitle("Add a New Pizza");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, frameWidth, frameHeight);
		
		addPizzaButton = new JButton();
		addPizzaButton.setText("Add Pizza");
		
		nameField = new JTextField();
		ingredientField = new JTextField();
		calorieField = new JTextField();
		
		nameLabel = new JLabel();
		ingredientLabel = new JLabel();
		calorieLabel = new JLabel();
		
		nameLabel.setText("Name of the new Pizza");
		ingredientLabel.setText("Ingredients of the pizza");
		calorieLabel.setText("The amount of calories of the pizza");
		
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup());
		
		
		
		
	}

}
