/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 24 "model.ump"
public class Ingredient
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ingredient Attributes
  private String name;
  private double price;
  private int calorieCount;

  //Ingredient Associations
  private List<Pizza> pizzas;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ingredient(String aName, double aPrice, int aCalorieCount)
  {
    name = aName;
    price = aPrice;
    calorieCount = aCalorieCount;
    pizzas = new ArrayList<Pizza>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setCalorieCount(int aCalorieCount)
  {
    boolean wasSet = false;
    calorieCount = aCalorieCount;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public double getPrice()
  {
    return price;
  }

  public int getCalorieCount()
  {
    return calorieCount;
  }
  /* Code from template association_GetMany */
  public Pizza getPizza(int index)
  {
    Pizza aPizza = pizzas.get(index);
    return aPizza;
  }

  public List<Pizza> getPizzas()
  {
    List<Pizza> newPizzas = Collections.unmodifiableList(pizzas);
    return newPizzas;
  }

  public int numberOfPizzas()
  {
    int number = pizzas.size();
    return number;
  }

  public boolean hasPizzas()
  {
    boolean has = pizzas.size() > 0;
    return has;
  }

  public int indexOfPizza(Pizza aPizza)
  {
    int index = pizzas.indexOf(aPizza);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfPizzasValid()
  {
    boolean isValid = numberOfPizzas() >= minimumNumberOfPizzas();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPizzas()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPizza(Pizza aPizza)
  {
    boolean wasAdded = false;
    if (pizzas.contains(aPizza)) { return false; }
    pizzas.add(aPizza);
    if (aPizza.indexOfIngredient(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPizza.addIngredient(this);
      if (!wasAdded)
      {
        pizzas.remove(aPizza);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removePizza(Pizza aPizza)
  {
    boolean wasRemoved = false;
    if (!pizzas.contains(aPizza))
    {
      return wasRemoved;
    }

    if (numberOfPizzas() <= minimumNumberOfPizzas())
    {
      return wasRemoved;
    }

    int oldIndex = pizzas.indexOf(aPizza);
    pizzas.remove(oldIndex);
    if (aPizza.indexOfIngredient(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPizza.removeIngredient(this);
      if (!wasRemoved)
      {
        pizzas.add(oldIndex,aPizza);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setPizzas(Pizza... newPizzas)
  {
    boolean wasSet = false;
    ArrayList<Pizza> verifiedPizzas = new ArrayList<Pizza>();
    for (Pizza aPizza : newPizzas)
    {
      if (verifiedPizzas.contains(aPizza))
      {
        continue;
      }
      verifiedPizzas.add(aPizza);
    }

    if (verifiedPizzas.size() != newPizzas.length || verifiedPizzas.size() < minimumNumberOfPizzas())
    {
      return wasSet;
    }

    ArrayList<Pizza> oldPizzas = new ArrayList<Pizza>(pizzas);
    pizzas.clear();
    for (Pizza aNewPizza : verifiedPizzas)
    {
      pizzas.add(aNewPizza);
      if (oldPizzas.contains(aNewPizza))
      {
        oldPizzas.remove(aNewPizza);
      }
      else
      {
        aNewPizza.addIngredient(this);
      }
    }

    for (Pizza anOldPizza : oldPizzas)
    {
      anOldPizza.removeIngredient(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPizzaAt(Pizza aPizza, int index)
  {  
    boolean wasAdded = false;
    if(addPizza(aPizza))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPizzas()) { index = numberOfPizzas() - 1; }
      pizzas.remove(aPizza);
      pizzas.add(index, aPizza);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePizzaAt(Pizza aPizza, int index)
  {
    boolean wasAdded = false;
    if(pizzas.contains(aPizza))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPizzas()) { index = numberOfPizzas() - 1; }
      pizzas.remove(aPizza);
      pizzas.add(index, aPizza);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPizzaAt(aPizza, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Pizza> copyOfPizzas = new ArrayList<Pizza>(pizzas);
    pizzas.clear();
    for(Pizza aPizza : copyOfPizzas)
    {
      if (aPizza.numberOfIngredients() <= Pizza.minimumNumberOfIngredients())
      {
        aPizza.delete();
      }
      else
      {
        aPizza.removeIngredient(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "," +
            "calorieCount" + ":" + getCalorieCount()+ "]";
  }
}