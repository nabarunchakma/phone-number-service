package au.com.belong.phonenumberservice.dal;

import au.com.belong.phonenumberservice.exception.NotFoundException;
import au.com.belong.phonenumberservice.model.PhoneNumber;
import au.com.belong.phonenumberservice.model.Status;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Created by nabarunchakma on 24/07/22.
 */
@Component
public class PhoneNumberDal implements IPhoneNumberDal {
  private List<PhoneNumber> phoneNumbers = Collections.synchronizedList(new ArrayList<>());
  public PhoneNumberDal() {
    PhoneNumber phoneNumber = new PhoneNumber("1");
    phoneNumber.setCustomerId("1");
    phoneNumber.setNumber("0412556879");
    phoneNumber.setStatus(Status.Active);
    phoneNumbers.add(phoneNumber);

    phoneNumber = new PhoneNumber("2");
    phoneNumber.setCustomerId("1");
    phoneNumber.setNumber("0412556870");
    phoneNumber.setStatus(Status.Active);
    phoneNumbers.add(phoneNumber);

    phoneNumber = new PhoneNumber("3");
    phoneNumber.setCustomerId("1");
    phoneNumber.setNumber("0412556871");
    phoneNumber.setStatus(Status.InActive);
    phoneNumbers.add(phoneNumber);

    phoneNumber = new PhoneNumber("4");
    phoneNumber.setCustomerId("2");
    phoneNumber.setNumber("0412556861");
    phoneNumber.setStatus(Status.Active);
    phoneNumbers.add(phoneNumber);

    phoneNumber = new PhoneNumber("5");
    phoneNumber.setCustomerId("2");
    phoneNumber.setNumber("0412556862");
    phoneNumber.setStatus(Status.InActive);
    phoneNumbers.add(phoneNumber);
  }

  @Override
  public PhoneNumber findById(String id) throws NotFoundException {
    Optional<PhoneNumber> phoneNumber = phoneNumbers.stream().filter(phoneNumber1 -> phoneNumber1.getId().equals(id)).findFirst();
    if (!phoneNumber.isPresent()) {
      throw new NotFoundException();
    }
    return phoneNumber.get();
  }

  @Override
  public List<PhoneNumber> findByCustomerId(String customerId) {
    List<PhoneNumber> customerPhoneNumbers = phoneNumbers.stream()
        .filter(phoneNumber1 -> phoneNumber1.getCustomerId().equals(customerId)).collect(Collectors.toList());
    return customerPhoneNumbers;
  }

  @Override
  public List<PhoneNumber> getAll() {
    return phoneNumbers;
  }

  @Override
  public void updateStatus(String id, Status status) throws NotFoundException {
    PhoneNumber phoneNumber = findById(id);
    int index = phoneNumbers.indexOf(phoneNumber);
    phoneNumber.setStatus(status);
    phoneNumbers.remove(index);
    phoneNumbers.add(phoneNumber);
  }
}
