package io.everyone.travel.exception;

public class NotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "resource not found";

    public NotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
