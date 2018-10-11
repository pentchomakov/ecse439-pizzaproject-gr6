/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 18 "model.ump"
public class Pizza
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Pizza Attributes
  private int calorieCount;
  private double basePrice;

  //Pizza Associations
  private List<Ingredient> ingredients;
  private List<Order> orders;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Pizza(int aCalorieCount, double aBasePrice)
  {
    calorieCount = aCalorieCount;
    basePrice = aBasePrice;
    ingredients = new ArrayList<Ingredient>();
    orders = new ArrayList<Order>();
  }

  public Pizza(List<Ingredient> ingredients)
  {

    for (Ingredient ingredient : ingredients) {
      calorieCount += ingredient.getCalorieCount();
      basePrice += ingredient.getPrice();
    }

    this.ingredients = ingredients;
    orders = new ArrayList<Order>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCalorieCount(int aCalorieCount)
  {
    boolean wasSet = false;
    calorieCount = aCalorieCount;
    wasSet = true;
    return wasSet;
  }

  public boolean setBasePrice(double aBasePrice)
  {
    boolean wasSet = false;
    basePrice = aBasePrice;
    wasSet = true;
    return wasSet;
  }

  public int getCalorieCount()
  {
    return calorieCount;
  }

  public double getBasePrice()
  {
    return basePrice;
  }
  /* Code from template association_GetMany */
  public Ingredient getIngredient(int index)
  {
    Ingredient aIngredient = ingredients.get(index);
    return aIngredient;
  }

  public List<Ingredient> getIngredients()
  {
    List<Ingredient> newIngredients = Collections.unmodifiableList(ingredients);
    return newIngredients;
  }

  public int numberOfIngredients()
  {
    int number = ingredients.size();
    return number;
  }

  public boolean hasIngredients()
  {
    boolean has = ingredients.size() > 0;
    return has;
  }

  public int indexOfIngredient(Ingredient aIngredient)
  {
    int index = ingredients.indexOf(aIngredient);
    return index;
  }
  /* Code from template association_GetMany */
  public Order getOrder(int index)
  {
    Order aOrder = orders.get(index);
    return aOrder;
  }

  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public int numberOfOrders()
  {
    int number = orders.size();
    return number;
  }

  public boolean hasOrders()
  {
    boolean has = orders.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = orders.indexOf(aOrder);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfIngredientsValid()
  {
    boolean isValid = numberOfIngredients() >= minimumNumberOfIngredients();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfIngredients()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addIngredient(Ingredient aIngredient)
  {
    boolean wasAdded = false;
    if (ingredients.contains(aIngredient)) { return false; }
    ingredients.add(aIngredient);
    if (aIngredient.indexOfPizza(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aIngredient.addPizza(this);
      if (!wasAdded)
      {
        ingredients.remove(aIngredient);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeIngredient(Ingredient aIngredient)
  {
    boolean wasRemoved = false;
    if (!ingredients.contains(aIngredient))
    {
      return wasRemoved;
    }

    if (numberOfIngredients() <= minimumNumberOfIngredients())
    {
      return wasRemoved;
    }

    int oldIndex = ingredients.indexOf(aIngredient);
    ingredients.remove(oldIndex);
    if (aIngredient.indexOfPizza(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aIngredient.removePizza(this);
      if (!wasRemoved)
      {
        ingredients.add(oldIndex,aIngredient);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setIngredients(Ingredient... newIngredients)
  {
    boolean wasSet = false;
    ArrayList<Ingredient> verifiedIngredients = new ArrayList<Ingredient>();
    for (Ingredient aIngredient : newIngredients)
    {
      if (verifiedIngredients.contains(aIngredient))
      {
        continue;
      }
      verifiedIngredients.add(aIngredient);
    }

    if (verifiedIngredients.size() != newIngredients.length || verifiedIngredients.size() < minimumNumberOfIngredients())
    {
      return wasSet;
    }

    ArrayList<Ingredient> oldIngredients = new ArrayList<Ingredient>(ingredients);
    ingredients.clear();
    for (Ingredient aNewIngredient : verifiedIngredients)
    {
      ingredients.add(aNewIngredient);
      if (oldIngredients.contains(aNewIngredient))
      {
        oldIngredients.remove(aNewIngredient);
      }
      else
      {
        aNewIngredient.addPizza(this);
      }
    }

    for (Ingredient anOldIngredient : oldIngredients)
    {
      anOldIngredient.removePizza(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addIngredientAt(Ingredient aIngredient, int index)
  {  
    boolean wasAdded = false;
    if(addIngredient(aIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIngredients()) { index = numberOfIngredients() - 1; }
      ingredients.remove(aIngredient);
      ingredients.add(index, aIngredient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveIngredientAt(Ingredient aIngredient, int index)
  {
    boolean wasAdded = false;
    if(ingredients.contains(aIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIngredients()) { index = numberOfIngredients() - 1; }
      ingredients.remove(aIngredient);
      ingredients.add(index, aIngredient);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addIngredientAt(aIngredient, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfOrdersValid()
  {
    boolean isValid = numberOfOrders() >= minimumNumberOfOrders();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrders()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    orders.add(aOrder);
    if (aOrder.indexOfPizza(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOrder.addPizza(this);
      if (!wasAdded)
      {
        orders.remove(aOrder);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    if (!orders.contains(aOrder))
    {
      return wasRemoved;
    }

    if (numberOfOrders() <= minimumNumberOfOrders())
    {
      return wasRemoved;
    }

    int oldIndex = orders.indexOf(aOrder);
    orders.remove(oldIndex);
    if (aOrder.indexOfPizza(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOrder.removePizza(this);
      if (!wasRemoved)
      {
        orders.add(oldIndex,aOrder);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setOrders(Order... newOrders)
  {
    boolean wasSet = false;
    ArrayList<Order> verifiedOrders = new ArrayList<Order>();
    for (Order aOrder : newOrders)
    {
      if (verifiedOrders.contains(aOrder))
      {
        continue;
      }
      verifiedOrders.add(aOrder);
    }

    if (verifiedOrders.size() != newOrders.length || verifiedOrders.size() < minimumNumberOfOrders())
    {
      return wasSet;
    }

    ArrayList<Order> oldOrders = new ArrayList<Order>(orders);
    orders.clear();
    for (Order aNewOrder : verifiedOrders)
    {
      orders.add(aNewOrder);
      if (oldOrders.contains(aNewOrder))
      {
        oldOrders.remove(aNewOrder);
      }
      else
      {
        aNewOrder.addPizza(this);
      }
    }

    for (Order anOldOrder : oldOrders)
    {
      anOldOrder.removePizza(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(orders.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Ingredient> copyOfIngredients = new ArrayList<Ingredient>(ingredients);
    ingredients.clear();
    for(Ingredient aIngredient : copyOfIngredients)
    {
      if (aIngredient.numberOfPizzas() <= Ingredient.minimumNumberOfPizzas())
      {
        aIngredient.delete();
      }
      else
      {
        aIngredient.removePizza(this);
      }
    }
    ArrayList<Order> copyOfOrders = new ArrayList<Order>(orders);
    orders.clear();
    for(Order aOrder : copyOfOrders)
    {
      if (aOrder.numberOfPizzas() <= Order.minimumNumberOfPizzas())
      {
        aOrder.delete();
      }
      else
      {
        aOrder.removePizza(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "calorieCount" + ":" + getCalorieCount()+ "," +
            "basePrice" + ":" + getBasePrice()+ "]";
  }
}