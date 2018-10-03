/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 1 "model.ump"
public class Customer
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes
  private String name;
  private String email;
  private String phoneNumber;

  //Customer Associations
  private List<Order> orders;
  private Address address;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aName, String aEmail, String aPhoneNumber, Address aAddress)
  {
    name = aName;
    email = aEmail;
    phoneNumber = aPhoneNumber;
    orders = new ArrayList<Order>();
    boolean didAddAddress = setAddress(aAddress);
    if (!didAddAddress)
    {
      throw new RuntimeException("Unable to create customer due to address");
    }
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

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
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
  /* Code from template association_GetOne */
  public Address getAddress()
  {
    return address;
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
  /* Code from template association_AddMandatoryManyToOne */
  public Order addOrder(String aDate, double aTotalPrice, int aOrderNumber)
  {
    Order aNewOrder = new Order(aDate, aTotalPrice, aOrderNumber, this);
    return aNewOrder;
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    Customer existingCustomer = aOrder.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);

    if (isNewCustomer && existingCustomer.numberOfOrders() <= minimumNumberOfOrders())
    {
      return wasAdded;
    }
    if (isNewCustomer)
    {
      aOrder.setCustomer(this);
    }
    else
    {
      orders.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a customer
    if (this.equals(aOrder.getCustomer()))
    {
      return wasRemoved;
    }

    //customer already at minimum (1)
    if (numberOfOrders() <= minimumNumberOfOrders())
    {
      return wasRemoved;
    }

    orders.remove(aOrder);
    wasRemoved = true;
    return wasRemoved;
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setAddress(Address aAddress)
  {
    boolean wasSet = false;
    //Must provide address to customer
    if (aAddress == null)
    {
      return wasSet;
    }

    if (address != null && address.numberOfCustomers() <= Address.minimumNumberOfCustomers())
    {
      return wasSet;
    }

    Address existingAddress = address;
    address = aAddress;
    if (existingAddress != null && !existingAddress.equals(aAddress))
    {
      boolean didRemove = existingAddress.removeCustomer(this);
      if (!didRemove)
      {
        address = existingAddress;
        return wasSet;
      }
    }
    address.addCustomer(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=orders.size(); i > 0; i--)
    {
      Order aOrder = orders.get(i - 1);
      aOrder.delete();
    }
    Address placeholderAddress = address;
    this.address = null;
    if(placeholderAddress != null)
    {
      placeholderAddress.removeCustomer(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "email" + ":" + getEmail()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "address = "+(getAddress()!=null?Integer.toHexString(System.identityHashCode(getAddress())):"null");
  }
}