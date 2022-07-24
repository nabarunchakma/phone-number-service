package au.com.belong.phonenumberservice.model;

/**
 * A model for a Customer
 *
 * Created by nabarunchakma on 24/07/22.
 */
public class Customer {
  /**
   * unique id of the record
   */
  private String id;

  /**
   * The customer's name
   */
  private String name;

  /**
   * The customer's address
   */

  private String address;

  public Customer(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
