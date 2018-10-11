import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainPage extends JFrame{
	
	private JPanel contentPane;
	private int pizzaMenuSize = 3; // number of pre created types of pizza
	
	private static MainPage frame;
	private JTable pizzaTable;
	private JTable orderTable;

	// Array for holding the current pizza menu
	private List<Pizza> menu = new ArrayList<>();

	// Array for holding the possible topping choices
	private List<Ingredient> toppings = new ArrayList<>();

	private JLabel tableLabel;
	private JLabel orderLabel;

	private JButton addPizzaButton;
	private JButton createYourOwnButton;
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
		int frameWidth = 2000;
		int frameHeight = 350;
		
		setTitle("Mamma Mia Restaurant Ordering System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, frameWidth, frameHeight);

		// Sets up default topping choices
		initDefaultToppings();

		// Sets up default pizza menu choices
		initDefaultMenu();

		orderTable = new JTable();
		
		tableLabel = new JLabel();
		tableLabel.setText("The currently available pizzas:");
		
		addPizzaButton = new JButton();
		addPizzaButton.setText("Select Pizza");
		
		deletePizzaButton = new JButton();
		deletePizzaButton.setText("Delete Pizza");
		
		updatePizzaButton = new JButton();
		updatePizzaButton.setText("Update Pizza");

		createYourOwnButton = new JButton();
		createYourOwnButton.setText("Create Your own");
		
		addPizzaButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				addPizzaButtonActionPerformed(evt);
			}
		});
		
		deletePizzaButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				removePizzaButtonActionPerformed(evt);
			}
		});

		createYourOwnButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				createPizzaButtonActionPerformed(evt);
			}
		});
		
		updatePizzaButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				updatePizzaButtonActionPerformed(evt);
			}
		});
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(tableLabel)
				.addComponent(pizzaTable, 100, 600, frameWidth)
				.addGroup(layout.createSequentialGroup()
								.addComponent(addPizzaButton, 100, 100, frameWidth/4)
								.addComponent(deletePizzaButton, 100, 100, frameWidth/4)
								.addComponent(updatePizzaButton, 100, 100, frameWidth/4)
								.addComponent(createYourOwnButton, 100, 100, frameWidth/4)
						)
				.addComponent(tableLabel)
		);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(tableLabel)
				.addComponent(pizzaTable, 100, 300, frameHeight)
				.addGroup(layout.createParallelGroup()
						.addComponent(addPizzaButton)
						.addComponent(deletePizzaButton)
						.addComponent(updatePizzaButton)
						.addComponent(createYourOwnButton)
						));
		
		pack();		
	}

	public void initDefaultMenu() {

		DefaultTableModel model = new DefaultTableModel(new Object[]{"Pizza", "Calories"}, pizzaMenuSize);
		pizzaTable = new JTable(model);

		// Append a row for table headers
		model.addRow(new Object[]{"Pizza Name:", "Calories:"});

		// Cheese Pizza
//		Pizza cheese = new Pizza();

	}

	public void initDefaultToppings() {

		// Cheese
		Ingredient cheese = new Ingredient("Cheese", 0.5, 25);
		toppings.add(cheese);

		// Pizza Sauce
		Ingredient sauce = new Ingredient("Pizza Sauce", 0.5, 15);
		toppings.add(sauce);

		// Pepperoni
		Ingredient pepperoni = new Ingredient("Pepperoni", 2.5, 55);
		toppings.add(pepperoni);

		// Ham
		Ingredient ham = new Ingredient("Ham", 3.5, 155);
		toppings.add(ham);

		// Peppers
		Ingredient peppers = new Ingredient("Peppers", 2, 5);
		toppings.add(peppers);

		// Olives
		Ingredient olives = new Ingredient("Olives", 3, 15);
		toppings.add(olives);

	}
	
	public void addPizzaButtonActionPerformed(ActionEvent evt)
	{

	}

	public void createPizzaButtonActionPerformed(ActionEvent evt)
	{
		new AddPizzaPage().setVisible(true);
		dispose();
	}
	
	public void removePizzaButtonActionPerformed(ActionEvent evt)
	{
		
	}
	
	public void updatePizzaButtonActionPerformed(ActionEvent evt)
	{
		
	}


}
