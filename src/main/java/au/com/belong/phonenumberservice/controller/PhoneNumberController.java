package au.com.belong.phonenumberservice.controller;

import au.com.belong.phonenumberservice.exception.NotFoundException;
import au.com.belong.phonenumberservice.model.PhoneNumber;
import au.com.belong.phonenumberservice.service.IPhoneNumberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Created by nabarunchakma on 24/07/22.
 */
@RestController
public class PhoneNumberController {
  @Autowired
  private IPhoneNumberService phoneNumberService;

  @GetMapping("/phone-numbers")
  public List<PhoneNumber> getAll(@RequestParam(required = false) String customerId) throws NotFoundException {
    if(customerId == null) {
      return phoneNumberService.getAllPhoneNumbers();
    }

    return phoneNumberService.getAllPhoneNumbersByCustomerId(customerId);
  }

  @PutMapping("/phone-numbers/{phoneNumber}")
  public PhoneNumber activate(@PathVariable("phoneNumber") String phoneNumber, @RequestParam String action) throws NotFoundException {
    if("activate".equalsIgnoreCase(action)) {
      return phoneNumberService.activate(phoneNumber);
    }

    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Query Parameter \"action\" with value \"activate\" is required");
  }
}
