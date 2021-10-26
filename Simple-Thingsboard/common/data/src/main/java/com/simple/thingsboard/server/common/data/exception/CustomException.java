
package com.simple.thingsboard.server.common.data.exception;

public class CustomException extends Exception {

    private static final long serialVersionUID = 1L;

    private CustomErrorCode errorCode;

    public CustomException() {
        super();
    }

    public CustomException(CustomErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public CustomException(String message, CustomErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CustomException(String message, Throwable cause, CustomErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public CustomException(Throwable cause, CustomErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public CustomErrorCode getErrorCode() {
        return errorCode;
    }

}
