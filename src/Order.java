/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 32 "model.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private String date;
  private double totalPrice;
  private int orderNumber;

  //Order Associations
  private List<Pizza> pizzas;
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(String aDate, double aTotalPrice, int aOrderNumber, Customer aCustomer)
  {
    date = aDate;
    totalPrice = aTotalPrice;
    orderNumber = aOrderNumber;
    pizzas = new ArrayList<Pizza>();
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create order due to customer");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(String aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalPrice(double aTotalPrice)
  {
    boolean wasSet = false;
    totalPrice = aTotalPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setOrderNumber(int aOrderNumber)
  {
    boolean wasSet = false;
    orderNumber = aOrderNumber;
    wasSet = true;
    return wasSet;
  }

  public String getDate()
  {
    return date;
  }

  public double getTotalPrice()
  {
    return totalPrice;
  }

  public int getOrderNumber()
  {
    return orderNumber;
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
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
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
    if (aPizza.indexOfOrder(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPizza.addOrder(this);
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
    if (aPizza.indexOfOrder(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPizza.removeOrder(this);
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
        aNewPizza.addOrder(this);
      }
    }

    for (Pizza anOldPizza : oldPizzas)
    {
      anOldPizza.removeOrder(this);
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    //Must provide customer to order
    if (aCustomer == null)
    {
      return wasSet;
    }

    if (customer != null && customer.numberOfOrders() <= Customer.minimumNumberOfOrders())
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      boolean didRemove = existingCustomer.removeOrder(this);
      if (!didRemove)
      {
        customer = existingCustomer;
        return wasSet;
      }
    }
    customer.addOrder(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Pizza> copyOfPizzas = new ArrayList<Pizza>(pizzas);
    pizzas.clear();
    for(Pizza aPizza : copyOfPizzas)
    {
      if (aPizza.numberOfOrders() <= Pizza.minimumNumberOfOrders())
      {
        aPizza.delete();
      }
      else
      {
        aPizza.removeOrder(this);
      }
    }
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeOrder(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "date" + ":" + getDate()+ "," +
            "totalPrice" + ":" + getTotalPrice()+ "," +
            "orderNumber" + ":" + getOrderNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}