package did.lemonaid.solution.common.exception;


import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException {

    private ErrorCode errorCode;
    private List<ErrorResponse.FieldError> details = new ArrayList<>();

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(String message, ErrorCode errorCode, ErrorResponse.FieldError detail) {
        super(message);
        this.errorCode = errorCode;
        details.add(detail);
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public List<ErrorResponse.FieldError> getDetails(){
        return details;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
