package did.lemonaid.solution.common.exception;

public class InvalidValueException extends BusinessException{
  public InvalidValueException() {
    super(ErrorCode.INVALID_INPUT_VALUE);
  }

  public InvalidValueException(ErrorCode errorCode){
    super(errorCode.getMessage(), errorCode);
  }
  public InvalidValueException(String addMessage, ErrorCode errorCode){
    super(errorCode.getMessage() + addMessage, errorCode);
  }

  public InvalidValueException(String value) {
        super(value, ErrorCode.INVALID_INPUT_VALUE);
    }

//    public InvalidValueException(String value, ErrorCode errorCode) {
//        super(value, errorCode);
//    }

    public InvalidValueException(String message, ErrorCode errorCode, ErrorResponse.FieldError fieldError) {
        super(message, errorCode, fieldError );
    }


}
