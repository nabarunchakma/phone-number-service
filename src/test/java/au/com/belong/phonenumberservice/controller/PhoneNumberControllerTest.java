package au.com.belong.phonenumberservice.controller;

import au.com.belong.phonenumberservice.exception.NotFoundException;
import au.com.belong.phonenumberservice.fixture.PhoneNumberServiceFixture;
import au.com.belong.phonenumberservice.model.PhoneNumber;
import au.com.belong.phonenumberservice.service.PhoneNumberService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

/**
 * Created by nabarunchakma on 24/07/22.
 */
@ExtendWith(MockitoExtension.class)
class PhoneNumberControllerTest {
  @InjectMocks
  private PhoneNumberController controller;

  @Mock
  private PhoneNumberService service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAll() throws NotFoundException {
    Mockito.when(service.getAllPhoneNumbers()).thenReturn(PhoneNumberServiceFixture.mockedPhoneNumbers());
    List<PhoneNumber> phoneNumbers = controller.getAll(null);
    assertEquals(PhoneNumberServiceFixture.mockedPhoneNumbers().size(), phoneNumbers.size());
    verify(service).getAllPhoneNumbers();
  }

  @Test
  void getAllByCustomerId() throws NotFoundException {
    Mockito.when(service.getAllPhoneNumbersByCustomerId("1")).thenReturn(PhoneNumberServiceFixture.mockedPhoneNumbers());
    List<PhoneNumber> phoneNumbers = controller.getAll("1");
    assertEquals(PhoneNumberServiceFixture.mockedPhoneNumbers().size(), phoneNumbers.size());
    verify(service).getAllPhoneNumbersByCustomerId(ArgumentMatchers.argThat(x -> {
      assertEquals("1", x);
      return true;
    }));
  }

  @Test
  void activate() throws NotFoundException {
    Mockito.when(service.activate("0400000001")).thenReturn(PhoneNumberServiceFixture.mockedPhoneNumbers().get(0));
    PhoneNumber phoneNumber = controller.activate("0400000001", "activate");
    assertEquals("0400000001", phoneNumber.getNumber());
  }

  @Test
  void activate_whenPhoneNumberNotFound() throws NotFoundException {
    Mockito.when(service.activate("0400000001")).thenThrow(new NotFoundException());
    Exception exception = assertThrows(NotFoundException.class, () -> {
      controller.activate("0400000001", "activate");
    });
    assertEquals("Not Found", exception.getMessage());
  }

  @Test
  void activate_whenInvalidAction() {
    Exception exception = assertThrows(ResponseStatusException.class, () -> {
      controller.activate("0400000001", "InvalidAction");
    });
    assertEquals("400 BAD_REQUEST \"Query Parameter \"action\" with value \"activate\" is required\"", exception.getMessage());
  }
}