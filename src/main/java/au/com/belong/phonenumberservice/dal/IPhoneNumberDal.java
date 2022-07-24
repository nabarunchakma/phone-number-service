package au.com.belong.phonenumberservice.dal;

import au.com.belong.phonenumberservice.exception.NotFoundException;
import au.com.belong.phonenumberservice.model.PhoneNumber;
import au.com.belong.phonenumberservice.model.Status;
import java.util.List;

/**
 * It interacts with the data storage where phone number is stored
 * Created by nabarunchakma on 24/07/22.
 */
public interface IPhoneNumberDal {
  /**
   * find a phone number by id
   * @param id The id of the record
   * @return A phone number
   * @throws NotFoundException when no phone number found
   */
  PhoneNumber findById(String id) throws NotFoundException;

  /**
   * get all phone number for a customer
   * @param customerId
   * @return A list of phone number belongs to the customer or an empty list if customer has no phone number
   */
  List<PhoneNumber> findByCustomerId(String customerId);

  /**
   * get all Phone Number records
   * @return A list of phone number or an empty list if no records found
   */
  List<PhoneNumber> getAll();

  /**
   * update the status of a phone number
   * @param id The id of the phone number record
   * @param status The status value to be updated with
   * @throws NotFoundException when no phone number found
   */
  void updateStatus(String id, Status status) throws NotFoundException;
}
