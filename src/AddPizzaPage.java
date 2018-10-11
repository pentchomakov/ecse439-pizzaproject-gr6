import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class AddPizzaPage extends JFrame{
	
	private JPanel contentPane;
	
	private static AddPizzaPage frame;
	
	private JButton addPizzaButton;
	private JButton addIngredientButton;
	private JButton removeIngredientButton;
	
	DefaultListModel<String> model = new DefaultListModel<>();
	private JList<String> ingredientList;

	// Array for holding the possible topping choices
	private List<Ingredient> toppings = new ArrayList<>();

	private JTextField nameField;
	private JTextField ingredientField;
	private JTextField calorieField;
	
	private JLabel nameLabel;
	private JLabel ingredientLabel;
	private JLabel calorieLabel;
	
	private JLabel errorMessage;
	private String error;
	
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
		int frameWidth = 1000;
		int frameHeight = 350;
		
		setTitle("Add a New Pizza");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, frameWidth, frameHeight);
		
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		// Sets up default topping choices
		initDefaultToppings();

		ingredientList = new JList<>( model );

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
		
		nameLabel.setText("Name of the new Pizza");
		ingredientLabel.setText("Ingredients of the pizza");
		calorieLabel.setText("The amount of calories of the pizza");
		
		addPizzaButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				addPizzaButtonActionPerformed(evt);
			}
		});
		
		addIngredientButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				addIngredientButtonActionPerformed(evt);
			}
		});
		
		removeIngredientButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
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
				);
		
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {nameField, addPizzaButton});
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {ingredientField, addPizzaButton});
		layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {calorieField, addPizzaButton});
		
		
		pack();
		
	}
	
	public void refresh() 
	{
		errorMessage.setText(error);
		ingredientField.setText("");
		pack();
	}
	
	public static boolean isStringNumeric( String str )
	{
	    DecimalFormatSymbols currentLocaleSymbols = DecimalFormatSymbols.getInstance();
	    char localeMinusSign = currentLocaleSymbols.getMinusSign();

	    if ( !Character.isDigit( str.charAt( 0 ) ) && str.charAt( 0 ) != localeMinusSign ) return false;

	    boolean isDecimalSeparatorFound = false;
	    char localeDecimalSeparator = currentLocaleSymbols.getDecimalSeparator();

	    for ( char c : str.substring( 1 ).toCharArray() )
	    {
	        if ( !Character.isDigit( c ) )
	        {
	            if ( c == localeDecimalSeparator && !isDecimalSeparatorFound )
	            {
	                isDecimalSeparatorFound = true;
	                continue;
	            }
	            return false;
	        }
	    }
	    return true;
	}
	
	public boolean addPizzaFieldsFilledCorrectly(String pizzaName, String calorieAmount)
	{
		if(pizzaName == null|| pizzaName.length() == 0)
		{
			error = "You must enter a name for the pizza.";
			return false;
		}
		else if(calorieAmount == null || calorieAmount.length() == 0)
		{
			error = "You must enter a calorie amount for the pizza.";
			return false;
		}
		else if(isStringNumeric(calorieAmount) == false)
		{
			error = "The calorie amount must be a number.";
			return false;
		}
		else if(model.isEmpty())
		{
			error = "The pizza must have ingredients.";
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean addIngredientFieldsFilledCorrectly(String ingredientName)
	{
		if(ingredientName == null || ingredientName.length() == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public void addPizzaButtonActionPerformed(ActionEvent evt)
	{
		String pizzaName = nameField.getText();
		String calorieAmount = calorieField.getText();
		
		if(addPizzaFieldsFilledCorrectly(pizzaName,calorieAmount) == true)
		{
			new MainPage().setVisible(true);
			dispose();
		}
		else
		{
			refresh();
		}
	}
	
	public void addIngredientButtonActionPerformed(ActionEvent evt)
	{
		String ingredientName = ingredientField.getText();
		if(addIngredientFieldsFilledCorrectly(ingredientName) == true)
		{
			error = null;
			model.addElement(ingredientName);
			refresh();
		}
		else
		{
			error = "You must enter an ingredient name to add it to the list.";
			refresh();
		}
	}
	
	public void removeIngredientButtonActionPerformed(ActionEvent evt)
	{
		if(ingredientList.getSelectedIndex() == -1)
		{
			error = "No ingredient has been selected to be removed.";
			refresh();
		}
		else
		{
			error = null;
			model.removeElementAt(ingredientList.getSelectedIndex() );
			refresh();
		}
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
}
