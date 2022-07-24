package au.com.belong.phonenumberservice.dal;

import au.com.belong.phonenumberservice.exception.NotFoundException;
import au.com.belong.phonenumberservice.model.Customer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

/**
 * Created by nabarunchakma on 24/07/22.
 */
@Component
public class CustomerDal implements ICustomerDal {
  private List<Customer> customers = Collections.synchronizedList(new ArrayList<>());

  public CustomerDal() {
    Customer customer = new Customer("1");
    customer.setName("Peter Tapscott");
    customer.setAddress("1 Clayton Road, Clayton, VIC 3168");
    customers.add(customer);
    customer = new Customer("2");
    customer.setName("John Howard");
    customer.setAddress("1 Bourke Street, Melbourne, VIC 3000");
    customers.add(customer);
    customer = new Customer("3");
    customer.setName("Kevin Rudd");
    customer.setAddress("1 Spring Street, Melbourne, VIC 3000");
    customers.add(customer);
  }

  @Override
  public Customer findById(String id) throws NotFoundException {
    Optional<Customer> customer = customers.stream().filter(customer1 -> customer1.getId().equals(id)).findFirst();
    if (!customer.isPresent()) {
      throw new NotFoundException();
    }
    return customer.get();
  }

  @Override
  public List<Customer> getAll() {
    return customers;
  }
}
