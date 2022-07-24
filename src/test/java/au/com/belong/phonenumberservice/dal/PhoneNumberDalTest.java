package au.com.belong.phonenumberservice.dal;

import au.com.belong.phonenumberservice.exception.NotFoundException;
import au.com.belong.phonenumberservice.model.PhoneNumber;
import au.com.belong.phonenumberservice.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by nabarunchakma on 24/07/22.
 */
class PhoneNumberDalTest {
  private PhoneNumberDal phoneNumberDal;

  @BeforeEach
  void setup() {
    phoneNumberDal = new PhoneNumberDal();
  }

  @Test
  void findById() throws NotFoundException {
    PhoneNumber phoneNumber = phoneNumberDal.findById("1");
    assertEquals(phoneNumber.getNumber(), "0412556879");
    assertEquals("1", phoneNumber.getCustomerId());
    assertEquals(Status.Active, phoneNumber.getStatus());
  }

  @Test
  void findById_whenNoRecordFound() {
    Exception exception = assertThrows(NotFoundException.class, () -> {
      phoneNumberDal.findById("Unknown Id");
    });
    assertEquals("Not Found", exception.getMessage());
  }

  @Test
  void findByCustomerId() {
    assertEquals(3, phoneNumberDal.findByCustomerId("1").size());
    assertEquals(2, phoneNumberDal.findByCustomerId("2").size());
    assertEquals(0, phoneNumberDal.findByCustomerId("3").size());
    assertEquals(0, phoneNumberDal.findByCustomerId("Some_Unknown_ID").size());
  }

  @Test
  void getAll() {
    assertEquals(5, phoneNumberDal.getAll().size());
  }

  @Test
  void updateStatus() throws NotFoundException {
    PhoneNumber phoneNumber = phoneNumberDal.findById("3");
    assertEquals(Status.InActive, phoneNumber.getStatus());
    phoneNumberDal.updateStatus("3", Status.Active);
    phoneNumber = phoneNumberDal.findById("3");
    assertEquals(Status.Active, phoneNumber.getStatus());
  }

  @Test
  void updateStatus_whenNoRecordFound() {
    Exception exception = assertThrows(NotFoundException.class, () -> {
      phoneNumberDal.updateStatus("Incorrect_Id", Status.Active);
    });
    assertEquals("Not Found", exception.getMessage());
  }
}