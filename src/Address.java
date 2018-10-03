/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 9 "model.ump"
public class Address
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Address Attributes
  private String streetAddress;
  private int aptNumber;
  private String postalCode;
  private String city;

  //Address Associations
  private List<Customer> customers;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Address(String aStreetAddress, int aAptNumber, String aPostalCode, String aCity)
  {
    streetAddress = aStreetAddress;
    aptNumber = aAptNumber;
    postalCode = aPostalCode;
    city = aCity;
    customers = new ArrayList<Customer>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStreetAddress(String aStreetAddress)
  {
    boolean wasSet = false;
    streetAddress = aStreetAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setAptNumber(int aAptNumber)
  {
    boolean wasSet = false;
    aptNumber = aAptNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setPostalCode(String aPostalCode)
  {
    boolean wasSet = false;
    postalCode = aPostalCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setCity(String aCity)
  {
    boolean wasSet = false;
    city = aCity;
    wasSet = true;
    return wasSet;
  }

  public String getStreetAddress()
  {
    return streetAddress;
  }

  public int getAptNumber()
  {
    return aptNumber;
  }

  public String getPostalCode()
  {
    return postalCode;
  }

  public String getCity()
  {
    return city;
  }
  /* Code from template association_GetMany */
  public Customer getCustomer(int index)
  {
    Customer aCustomer = customers.get(index);
    return aCustomer;
  }

  public List<Customer> getCustomers()
  {
    List<Customer> newCustomers = Collections.unmodifiableList(customers);
    return newCustomers;
  }

  public int numberOfCustomers()
  {
    int number = customers.size();
    return number;
  }

  public boolean hasCustomers()
  {
    boolean has = customers.size() > 0;
    return has;
  }

  public int indexOfCustomer(Customer aCustomer)
  {
    int index = customers.indexOf(aCustomer);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfCustomersValid()
  {
    boolean isValid = numberOfCustomers() >= minimumNumberOfCustomers();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCustomers()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Customer addCustomer(String aName, String aEmail, String aPhoneNumber)
  {
    Customer aNewCustomer = new Customer(aName, aEmail, aPhoneNumber, this);
    return aNewCustomer;
  }

  public boolean addCustomer(Customer aCustomer)
  {
    boolean wasAdded = false;
    if (customers.contains(aCustomer)) { return false; }
    Address existingAddress = aCustomer.getAddress();
    boolean isNewAddress = existingAddress != null && !this.equals(existingAddress);

    if (isNewAddress && existingAddress.numberOfCustomers() <= minimumNumberOfCustomers())
    {
      return wasAdded;
    }
    if (isNewAddress)
    {
      aCustomer.setAddress(this);
    }
    else
    {
      customers.add(aCustomer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCustomer(Customer aCustomer)
  {
    boolean wasRemoved = false;
    //Unable to remove aCustomer, as it must always have a address
    if (this.equals(aCustomer.getAddress()))
    {
      return wasRemoved;
    }

    //address already at minimum (1)
    if (numberOfCustomers() <= minimumNumberOfCustomers())
    {
      return wasRemoved;
    }

    customers.remove(aCustomer);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCustomerAt(Customer aCustomer, int index)
  {  
    boolean wasAdded = false;
    if(addCustomer(aCustomer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomers()) { index = numberOfCustomers() - 1; }
      customers.remove(aCustomer);
      customers.add(index, aCustomer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCustomerAt(Customer aCustomer, int index)
  {
    boolean wasAdded = false;
    if(customers.contains(aCustomer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomers()) { index = numberOfCustomers() - 1; }
      customers.remove(aCustomer);
      customers.add(index, aCustomer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCustomerAt(aCustomer, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=customers.size(); i > 0; i--)
    {
      Customer aCustomer = customers.get(i - 1);
      aCustomer.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "streetAddress" + ":" + getStreetAddress()+ "," +
            "aptNumber" + ":" + getAptNumber()+ "," +
            "postalCode" + ":" + getPostalCode()+ "," +
            "city" + ":" + getCity()+ "]";
  }
}