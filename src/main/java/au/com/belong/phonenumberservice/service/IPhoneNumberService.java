package au.com.belong.phonenumberservice.service;

import au.com.belong.phonenumberservice.exception.NotFoundException;
import au.com.belong.phonenumberservice.model.PhoneNumber;
import java.util.List;

/**
 * A service to manage phone numbers
 * Created by nabarunchakma on 24/07/22.
 */
public interface IPhoneNumberService {
  /**
   * get all phone numbers
   *
   * @return A list of phone numbers
   */
  List<PhoneNumber> getAllPhoneNumbers();

  /**
   * get all phone numbers that belongs to a customer
   * @param customerId The customer id
   * @return A list of phone numbers if customer has numbers, otherwise an empty list
   * @throws NotFoundException if Customer does not exist
   */
  List<PhoneNumber> getAllPhoneNumbersByCustomerId(String customerId) throws NotFoundException;

  /**
   * activate a phone number if the current status is InActive.
   * @param phoneNumber
   * @return The phone number after update or existing if no update happened
   * @throws NotFoundException if the phone number does not exist
   */
  PhoneNumber activate(String phoneNumber) throws NotFoundException;
}
