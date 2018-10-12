import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainPage extends JFrame {
	
	private JPanel contentPane;
	// Preset Menu Pizzas
	private int pizzaMenuSize = 3;
	
	private static MainPage frame;
	private JTable pizzaTable;
	DefaultTableModel menuModel;
	private JTable orderTable;
	DefaultTableModel orderModel;

	// Array for holding the current pizza menu
	private List<Pizza> presetPizzaMenu = new ArrayList<>();
	private List<Ingredient> ingredients = new ArrayList<>();

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
	
	public MainPage() {
		// Menu and Ingredients Initialization (hardcoded persistence)
		initMenuAndIngredients();
		
		// UI
		InitializeComponents();
	}
	
	public void initMenuAndIngredients() {
		// Available ingredients
		// Cheese 
		Ingredient cheese = new Ingredient("Cheese", 0.5, 25);
		ingredients.add(cheese);

		// Pizza Sauce
		Ingredient sauce = new Ingredient("Pizza Sauce", 0.5, 15);
		ingredients.add(sauce);

		// Pepperoni
		Ingredient pepperoni = new Ingredient("Pepperoni", 2.5, 55);
		ingredients.add(pepperoni);

		// Ham
		Ingredient ham = new Ingredient("Ham", 3.5, 155);
		ingredients.add(ham);

		// Peppers
		Ingredient peppers = new Ingredient("Peppers", 2, 5);
		ingredients.add(peppers);

		// Olives
		Ingredient olives = new Ingredient("Olives", 3, 15);
		ingredients.add(olives);

		// Preset Pizza Menu
		// Cheese Pizza
		Pizza cheesePizza = new Pizza(Arrays.asList(cheese, sauce), "Cheese Pizza");
		presetPizzaMenu.add(cheesePizza);

		// Pepperoni Pizza
		Pizza pepperoniPizza = new Pizza(Arrays.asList(cheese, sauce, pepperoni), "Pepperoni Pizza");
		presetPizzaMenu.add(pepperoniPizza);

		// Meat Lovers Pizza
		Pizza meatPizza = new Pizza(Arrays.asList(cheese, sauce, pepperoni, ham), "Meat Lovers Pizza");
		presetPizzaMenu.add(meatPizza);

		// Vegetarian
		Pizza vegetarianPizza = new Pizza(Arrays.asList(cheese, sauce, peppers, olives), "Vegetarian Pizza");
		presetPizzaMenu.add(vegetarianPizza);

	}
	
	public void InitializeComponents() {
		int frameWidth = 2000;
		int frameHeight = 350;
		
		setTitle("Mamma Mia Restaurant Ordering System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, frameWidth, frameHeight);

		// Sets up default pizza menu choices
		initDefaultMenu();
		
		// Set up order list
		initTotalOrder();
		
		tableLabel = new JLabel();
		tableLabel.setText("Mamma Mia's Menu - Select a pizza from the menu or make your own!");
		
		addPizzaButton = new JButton();
		addPizzaButton.setText("Add Pizza");
		
		deletePizzaButton = new JButton();
		deletePizzaButton.setText("Delete Pizza");
		
		updatePizzaButton = new JButton();
		updatePizzaButton.setText("Update Pizza");

		createYourOwnButton = new JButton();
		createYourOwnButton.setText("Create Your own");
		
		addPizzaButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addPizzaButtonActionPerformed(evt);
			}
		});
		
		deletePizzaButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removePizzaButtonActionPerformed(evt);
			}
		});

		createYourOwnButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createPizzaButtonActionPerformed(evt);
			}
		});
		
		updatePizzaButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
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
				.addComponent(pizzaTable, 100, 600, frameWidth/2)
				.addComponent(orderTable, 100, 600, frameWidth)
				.addGroup(layout.createSequentialGroup()
								.addComponent(addPizzaButton, 100, 100, frameWidth/4)
								.addComponent(deletePizzaButton, 100, 100, frameWidth/4)
								.addComponent(updatePizzaButton, 100, 100, frameWidth/4)
								.addComponent(createYourOwnButton, 100, 100, frameWidth/4))
				
		);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(tableLabel)
				.addComponent(pizzaTable, 100, 300, frameHeight)
				.addComponent(orderTable, 100, 300, frameHeight)
				.addGroup(layout.createParallelGroup()
						.addComponent(addPizzaButton)
						.addComponent(deletePizzaButton)
						.addComponent(updatePizzaButton)
						.addComponent(createYourOwnButton)
						));
		
		pack();		
	}

	public void initDefaultMenu() {

		menuModel = new DefaultTableModel(new Object[]{"Pizza", "Price", "Calories", "Ingredients"}, pizzaMenuSize);
		pizzaTable = new JTable(menuModel);

		// Append a row for table headers
		menuModel.addRow(new Object[]{"Pizza Name:", "Price", "Calories:", "Ingredients:"});

		for (Pizza pizza : presetPizzaMenu) {
			menuModel.addRow(new Object[]{pizza.getName(),"$"+pizza.getBasePrice(), pizza.getCalorieCount(),pizza.listIngredients()});
		}

	}
	
	public void initTotalOrder() {
		orderModel = new DefaultTableModel(new Object[]{"Pizza", "Price", "Calories", "Ingredients"}, pizzaMenuSize);
		orderTable = new JTable(orderModel);
		orderModel.addRow(new Object[]{"Pizza Name:", "Price", "Calories:", "Ingredients:"});
	}
	
	public void addPizzaButtonActionPerformed(ActionEvent evt) {
		
		// Get the selected pizza and add it to the the current "checkout basket"
		int rowNumber = pizzaTable.getSelectedRow();
		// If equals -1, no rows are selected. If equals to 0, it is the header row)
		if (rowNumber != -1 || rowNumber != 0) {
			String pizzaName = pizzaTable.getModel().getValueAt(rowNumber, 0).toString();
			
			for (Pizza pizza : presetPizzaMenu) {
				if (pizza.getName().equals(pizzaName)) {
					addPizzaToShoppingCart(pizza);
					break;
				}
			}
		}
	}
	
	public void addPizzaToShoppingCart(Pizza pizza) {
		orderModel.addRow(new Object[]{pizza.getName(),"$"+pizza.getBasePrice(), pizza.getCalorieCount(),pizza.listIngredients()});
		orderModel.fireTableDataChanged();
	}

	public void createPizzaButtonActionPerformed(ActionEvent evt) {
		new AddPizzaPage().setVisible(true);
		
		// TODO add pizza to Order Table
	}
	
	public void removePizzaButtonActionPerformed(ActionEvent evt) {
		// Get selected pizza from Order Table
		// Get the selected pizza and add it to the the current "checkout basket"
		int rowNumber = orderTable.getSelectedRow();
		
		// If equals -1, no rows are selected. If equals to 0, it is the header row)
		if (rowNumber != -1 || rowNumber != 0) {
			// Delete the pizza from the Order Table
			removePizzaFromShoppingCart(rowNumber);
		}
	}
	
	public void removePizzaFromShoppingCart(int row) {
		orderModel.removeRow(row);
		orderModel.fireTableDataChanged();
	}
	
	public void updatePizzaButtonActionPerformed(ActionEvent evt) {
		
		// Get selected pizza from the Order Table and make it a new object
		int rowNumber = orderTable.getSelectedRow();
		Pizza pizza = null;
		if (rowNumber != -1 || rowNumber != 0) {
			
			String name = orderTable.getValueAt(rowNumber, 0).toString();
			double price = (double) orderTable.getValueAt(rowNumber, 1);
			int caloriesCount = (int) orderTable.getValueAt(rowNumber, 2);
			List<Ingredient> ingredients = (List) orderTable.getValueAt(rowNumber, 2);
			
			pizza = new Pizza(caloriesCount, price, name);
			for (Ingredient ingredient : ingredients) {
				pizza.setIngredients(ingredient);
			}
		}	
		
		if (pizza != null) {
			new UpdatePizzaPage(pizza).setVisible(true);
		}

		
		// TODO
		// Update the Order Table
	}

	public void checkoutButtonActionPerformed(ActionEvent evt) {
		// TODO
		// Add button check under the 4 other buttons
		
		// TODO
		// Checkout makes a new window with the Shopping Cart
		
		// TODO
		// Total with taxes has to appear
		
		// TODO
		// Customer has to input their personal information and address
		
		// TODO
		// Then "checkout"
	}
}
