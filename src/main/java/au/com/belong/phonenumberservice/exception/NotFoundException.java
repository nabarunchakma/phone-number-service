package au.com.belong.phonenumberservice.exception;

/**
 * Created by nabarunchakma on 24/07/22.
 */
public class NotFoundException extends Exception {
  public NotFoundException() {
    super("Not Found");
  }

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotFoundException(Throwable cause) {
    super(cause);
  }

  protected NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
