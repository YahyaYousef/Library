package com.example.demo.customexception;

public final class InvalidRequestException extends RuntimeException {


    public InvalidRequestException() {
        super();
    }

    public InvalidRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidRequestException(final String message) {
        super(message);
    }

    public InvalidRequestException(final Throwable cause) {
        super(cause);
    }

}