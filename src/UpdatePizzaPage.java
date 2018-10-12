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
		try {
			frame = new UpdatePizzaPage(null);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public UpdatePizzaPage(Pizza pizza) {
		this.pizza = pizza;
		InitializeComponents();
	}

	public void InitializeComponents() {
		int frameWidth = 1000;
		int frameHeight = 350;
		
		setTitle("Add a New Pizza");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, frameWidth, frameHeight);
		
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		// Sets up default topping choices
		initDefaultIngredients();

		ingredientList = new JList<>(model);

		addPizzaButton = new JButton();
		addPizzaButton.setText("Add Pizza");
		
		addIngredientButton = new JButton();
		addIngredientButton.setText("Add Ingredient");
		
		removeIngredientButton = new JButton();
		removeIngredientButton.setText("Remove Ingredient");
		
		nameField = new JTextField();
		ingredientField = new JTextField();
		calorieField = new JTextField();
		
		nameLabel = new JLabel();
		ingredientLabel = new JLabel();
		calorieLabel = new JLabel();
		
		ingredientsSelector = new JComboBox();
		// TODO: get ingredients list from MainPage
		for (Ingredient ingredient: ingredients) {
			ingredientsSelector.addItem(ingredient.getName() + " - " + ingredient.getCalorieCount() + " Calories");
		}
		
		nameLabel.setText("Name of the new Pizza");
		ingredientLabel.setText("Ingredients of the pizza");
		calorieLabel.setText("The amount of calories of the pizza");
		 
		addPizzaButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addPizzaButtonActionPerformed(evt);
			}
		});
		
		addIngredientButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addIngredientButtonActionPerformed(evt);
			}
		});
		
		removeIngredientButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeIngredientButtonActionPerformed(evt);
			}
		});
		
		
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addComponent(ingredientList, 100, 200, frameWidth)
				.addGroup(layout.createSequentialGroup()
						.addComponent(nameLabel)
						.addComponent(ingredientLabel)
						.addComponent(calorieLabel))
				.addGroup(layout.createSequentialGroup()
						.addComponent(nameField)
						.addComponent(ingredientField)
						.addComponent(calorieField)
						)
				.addGroup(layout.createSequentialGroup()
						.addComponent(addPizzaButton)
						.addComponent(addIngredientButton)
						.addComponent(removeIngredientButton))
				.addComponent(ingredientsSelector)
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {nameField, nameLabel});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {ingredientField, ingredientLabel});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {calorieField, calorieLabel});
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addPizzaButton, nameLabel});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addIngredientButton, ingredientLabel});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {removeIngredientButton, calorieLabel});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addComponent(ingredientList, 100, 200, frameHeight)
				.addGroup(layout.createParallelGroup()
						.addComponent(nameLabel)
						.addComponent(ingredientLabel)
						.addComponent(calorieLabel))
				.addGroup(layout.createParallelGroup()
						.addComponent(nameField)
						.addComponent(ingredientField)
						.addComponent(calorieField))
				.addGroup(layout.createParallelGroup()
						.addComponent(addPizzaButton)
						.addComponent(addIngredientButton)
						.addComponent(removeIngredientButton))
				.addComponent(ingredientsSelector)
				);
		
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {nameField, addPizzaButton});
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {ingredientField, addPizzaButton});
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {calorieField, addPizzaButton});
		
		
		pack();
		
	}
}
