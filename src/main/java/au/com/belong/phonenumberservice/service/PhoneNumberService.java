package au.com.belong.phonenumberservice.service;

import au.com.belong.phonenumberservice.dal.ICustomerDal;
import au.com.belong.phonenumberservice.dal.IPhoneNumberDal;
import au.com.belong.phonenumberservice.exception.NotFoundException;
import au.com.belong.phonenumberservice.model.Customer;
import au.com.belong.phonenumberservice.model.PhoneNumber;
import au.com.belong.phonenumberservice.model.Status;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by nabarunchakma on 24/07/22.
 */
@Component
public class PhoneNumberService implements IPhoneNumberService {
  @Autowired
  private ICustomerDal customerDal;
  @Autowired
  private IPhoneNumberDal phoneNumberDal;


  @Override
  public List<PhoneNumber> getAllPhoneNumbers() {
    return phoneNumberDal.getAll();
  }

  @Override
  public List<PhoneNumber> getAllPhoneNumbersByCustomerId(String customerId) throws NotFoundException {
    Customer customer = customerDal.findById(customerId);
    return phoneNumberDal.findByCustomerId(customer.getId());
  }

  @Override
  public PhoneNumber activate(String phoneNumber) throws NotFoundException {
    List<PhoneNumber> phoneNumbers = phoneNumberDal.getAll();
    Optional<PhoneNumber> existing = phoneNumbers.stream().filter(phoneNumber1 -> phoneNumber1.getNumber().equals(phoneNumber)).findFirst();
    if(!existing.isPresent()) {
      throw new NotFoundException();
    }
    PhoneNumber existingPhoneNumber = existing.get();
    if(existingPhoneNumber.getStatus().equals(Status.InActive)) {
      phoneNumberDal.updateStatus(existingPhoneNumber.getId(), Status.Active);
    }
    return phoneNumberDal.findById(existingPhoneNumber.getId());
  }
}
