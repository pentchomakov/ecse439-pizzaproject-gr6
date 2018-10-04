import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class MainPage extends JFrame{
	
	private JPanel contentPane;
	
	private static MainPage frame;
	private JTable pizzaTable;
	
	private JLabel tableLabel;
	
	private JButton addPizzaButton;
	private JButton deletePizzaButton;
	private JButton updatePizzaButton;
	
	public static void main(String[] args) {
		try {
			frame = new MainPage();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MainPage()
	{
		InitializeComponents();
	}
	
	public void InitializeComponents()
	{
		int frameWidth = 1000;
		int frameHeight = 700;
		
		setTitle("Mamma Mia Restaurant Ordering System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, frameWidth, frameHeight);
		
		pizzaTable = new JTable();
		
		tableLabel = new JLabel();
		tableLabel.setText("The currently available pizzas:");
		
		addPizzaButton = new JButton();
		addPizzaButton.setText("Add Pizza");
		
		deletePizzaButton = new JButton();
		deletePizzaButton.setText("Delete Pizza");
		
		updatePizzaButton = new JButton();
		updatePizzaButton.setText("Update Pizza");
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(tableLabel)
				.addComponent(pizzaTable, 100, 400, frameWidth)
				.addGroup(layout.createSequentialGroup()
						.addComponent(addPizzaButton, 100, 100, frameWidth/3)
						.addComponent(deletePizzaButton, 100, 100, frameWidth/3)
						.addComponent(updatePizzaButton, 100, 100, frameWidth/3)
						));
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(tableLabel)
				.addComponent(pizzaTable, 100, 300, frameHeight)
				.addGroup(layout.createParallelGroup()
						.addComponent(addPizzaButton)
						.addComponent(deletePizzaButton)
						.addComponent(updatePizzaButton)
						));
		
	}

}