package au.com.belong.phonenumberservice.fixture;

import au.com.belong.phonenumberservice.model.Customer;
import au.com.belong.phonenumberservice.model.PhoneNumber;
import au.com.belong.phonenumberservice.model.Status;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nabarunchakma on 24/07/22.
 */
public class PhoneNumberServiceFixture {

  public static List<PhoneNumber> mockedPhoneNumbers() {
    List<PhoneNumber> phoneNumbers = new ArrayList<>();
    PhoneNumber phoneNumber = new PhoneNumber("test-a");
    phoneNumber.setCustomerId("c-1");
    phoneNumber.setNumber("0400000001");
    phoneNumber.setStatus(Status.InActive);
    phoneNumbers.add(phoneNumber);
    phoneNumber = new PhoneNumber("test-b");
    phoneNumber.setCustomerId("c-1");
    phoneNumber.setNumber("0400000002");
    phoneNumber.setStatus(Status.Active);
    phoneNumbers.add(phoneNumber);

    return phoneNumbers;
  }

  public static List<Customer> mockedCustomers() {
    List<Customer> customers = new ArrayList<>();
    Customer customer = new Customer("c-1");
    customers.add(customer);
    customer = new Customer("c-2");
    customers.add(customer);
    return customers;
  }
}
