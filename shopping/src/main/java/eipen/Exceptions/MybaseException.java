package eipen.Exceptions;

public class MybaseException extends RuntimeException{
    public MybaseException() {
    }

    public MybaseException(String message) {
        super(message);
    }

    public MybaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public MybaseException(Throwable cause) {
        super(cause);
    }

    public MybaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
