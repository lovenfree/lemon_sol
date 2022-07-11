package did.lemonaid.solution.common.exception;

public class EntityNotFoundException extends BusinessException{
  public EntityNotFoundException() {
    super(ErrorCode.ENTITY_NOT_FOUND.getMessage(), ErrorCode.ENTITY_NOT_FOUND);
  }
  public EntityNotFoundException(ErrorCode errorCode){
    super(errorCode.getMessage(), errorCode);
  }
  public EntityNotFoundException(String addMessage, ErrorCode errorCode){
    super(errorCode.getMessage() + addMessage, errorCode);
  }

    public EntityNotFoundException(String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }

}


