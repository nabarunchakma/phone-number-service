package au.com.belong.phonenumberservice.model;

/**
 * A model for a Phone Number
 *
 * Created by nabarunchakma on 24/07/22.
 */
public class PhoneNumber {
  /**
   * unique id of the record
   */
  private String id;
  /**
   * telephone number
   */
  private String number;
  /**
   * status of the telephone number
   */
  private Status status;
  /**
   * The phone number belong to this customer
   */
  private String customerId;

  public PhoneNumber(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }
}
