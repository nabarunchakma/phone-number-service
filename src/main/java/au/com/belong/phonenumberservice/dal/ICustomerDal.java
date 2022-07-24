package au.com.belong.phonenumberservice.dal;

import au.com.belong.phonenumberservice.exception.NotFoundException;
import au.com.belong.phonenumberservice.model.Customer;
import java.util.List;

/**
 * It interacts with the data storage where Customer Data is stored
 * Created by nabarunchakma on 24/07/22.
 */
public interface ICustomerDal {
  /**
   * find the customer record by Id
   * @param id The Id of the record
   * @return a Customer
   * @throws NotFoundException when no record found
   */
  Customer findById(String id) throws NotFoundException;

  /**
   * get all customer records
   * @return a List of Customer records. An empty list when no Customer records found
   */
  List<Customer> getAll();
}
