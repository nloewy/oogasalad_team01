package oogasalad.model.api.exception;

/**
 * The InvalidJSONDataException extends RuntimeException to handle any errors related writing JSON
 * files for game configuration
 *
 * @author Judy He
 */
public class InvalidJSONDataException extends RuntimeException {

  /**
   * Initialize a new InvalidJSONDataException given an error message
   *
   * @param message, error message to be displayed by GUI
   */
  public InvalidJSONDataException(String message) {
    super(message);
  }

  /**
   * Initialize a new InvalidJSONDataException given an error message and another exception
   *
   * @param message, error message to be displayed by GUI
   * @param cause,   the exception that prompted the InvalidJSONDataException
   */
  public InvalidJSONDataException(String message, Throwable cause) {
    super(message, cause);
  }

}
