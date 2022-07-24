package au.com.belong.phonenumberservice.dal;

import au.com.belong.phonenumberservice.exception.NotFoundException;
import au.com.belong.phonenumberservice.model.Customer;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by nabarunchakma on 24/07/22.
 */
class CustomerDalTest {
  private CustomerDal customerDal;

  @BeforeEach
  void setup() {
    customerDal = new CustomerDal();
  }

  @Test
  void findById() throws NotFoundException {
    Customer customer = customerDal.findById("1");
    assertNotNull(customer);
    assertEquals(customer.getName(), "Peter Tapscott");
    assertEquals(customer.getAddress(), "1 Clayton Road, Clayton, VIC 3168");
  }

  @Test
  void findById_whenIdNotFound() {
    Exception exception = assertThrows(NotFoundException.class, () -> {
      customerDal.findById("Not_Found_id");
    });

    assertEquals("Not Found", exception.getMessage());
  }

  @Test
  void getAll() {
    List<Customer> customers = customerDal.getAll();
    assertEquals(3, customers.size());
  }
}