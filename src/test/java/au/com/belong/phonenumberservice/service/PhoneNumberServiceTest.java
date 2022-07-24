package au.com.belong.phonenumberservice.service;

import au.com.belong.phonenumberservice.dal.ICustomerDal;
import au.com.belong.phonenumberservice.dal.IPhoneNumberDal;
import au.com.belong.phonenumberservice.exception.NotFoundException;
import au.com.belong.phonenumberservice.fixture.PhoneNumberServiceFixture;
import au.com.belong.phonenumberservice.model.PhoneNumber;
import au.com.belong.phonenumberservice.model.Status;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by nabarunchakma on 24/07/22.
 */
@ExtendWith(MockitoExtension.class)
class PhoneNumberServiceTest {
  @InjectMocks
  private IPhoneNumberService phoneNumberService = new PhoneNumberService();

  @Mock
  private IPhoneNumberDal phoneNumberDal;

  @Mock
  private ICustomerDal customerDal;

  @Captor
  ArgumentCaptor<String> idCaptor;

  @Captor
  ArgumentCaptor<Status> statusCaptor;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAllPhoneNumbers() {
    Mockito.when(phoneNumberDal.getAll()).thenReturn(PhoneNumberServiceFixture.mockedPhoneNumbers());
    List<PhoneNumber> phoneNumbers = phoneNumberService.getAllPhoneNumbers();
    assertEquals(2, phoneNumbers.size());
    assertEquals("test-a", phoneNumbers.get(0).getId());
  }

  @Test
  void getAllPhoneNumbersByCustomerId() throws NotFoundException {
    Mockito.when(customerDal.findById("c-1")).thenReturn(PhoneNumberServiceFixture.mockedCustomers().get(0));
    Mockito.when(phoneNumberDal.findByCustomerId("c-1")).thenReturn(PhoneNumberServiceFixture.mockedPhoneNumbers());
    List<PhoneNumber> phoneNumbers = phoneNumberService.getAllPhoneNumbersByCustomerId("c-1");
    assertEquals(2, phoneNumbers.size());
    verify(customerDal).findById(ArgumentMatchers.argThat(x -> {
      assertEquals("c-1", x);
      return true;
    }));
    verify(phoneNumberDal).findByCustomerId(ArgumentMatchers.argThat(x -> {
      assertEquals("c-1", x);
      return true;
    }));
  }

  @Test
  void getAllPhoneNumbersByCustomerId_whenCustomerDoesNotExist() throws NotFoundException {
    Mockito.when(customerDal.findById("c-2")).thenThrow(new NotFoundException());
    Exception exception = assertThrows(NotFoundException.class, () -> {
      phoneNumberService.getAllPhoneNumbersByCustomerId("c-2");
    });
    assertEquals("Not Found", exception.getMessage());
    verify(customerDal).findById(ArgumentMatchers.argThat(x -> {
      assertEquals("c-2", x);
      return true;
    }));
    verify(phoneNumberDal, never()).findByCustomerId(ArgumentMatchers.any());
  }

  @Test
  void activate_whenExistingRecordInActive() throws NotFoundException {
    Mockito.when(phoneNumberDal.getAll()).thenReturn(PhoneNumberServiceFixture.mockedPhoneNumbers());
    Mockito.when(phoneNumberDal.findById("test-a")).thenReturn(PhoneNumberServiceFixture.mockedPhoneNumbers().get(0));
    PhoneNumber phoneNumber = phoneNumberService.activate("0400000001");
    assertEquals(PhoneNumberServiceFixture.mockedPhoneNumbers().get(0).getNumber(), phoneNumber.getNumber());
    verify(phoneNumberDal, times(1)).updateStatus(idCaptor.capture(), statusCaptor.capture());
    assertEquals("test-a", idCaptor.getValue());
    assertEquals(Status.Active, statusCaptor.getValue());
  }

  @Test
  void activate_whenExistingRecordActive() throws NotFoundException {
    Mockito.when(phoneNumberDal.getAll()).thenReturn(PhoneNumberServiceFixture.mockedPhoneNumbers());
    Mockito.when(phoneNumberDal.findById("test-b")).thenReturn(PhoneNumberServiceFixture.mockedPhoneNumbers().get(0));
    PhoneNumber phoneNumber = phoneNumberService.activate("0400000002");
    assertEquals(PhoneNumberServiceFixture.mockedPhoneNumbers().get(0).getNumber(), phoneNumber.getNumber());
    verify(phoneNumberDal, times(0)).updateStatus(ArgumentMatchers.any(), ArgumentMatchers.any());
  }

  @Test
  void activate_whenInvalidPhoneNumber() throws NotFoundException {
    Mockito.when(phoneNumberDal.getAll()).thenReturn(PhoneNumberServiceFixture.mockedPhoneNumbers());
    Exception exception = assertThrows(NotFoundException.class, () -> {
      phoneNumberService.activate("0400000003");
    });
    assertEquals("Not Found", exception.getMessage());
  }
}