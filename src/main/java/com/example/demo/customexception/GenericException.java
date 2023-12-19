package com.example.demo.customexception;

public final class GenericException extends RuntimeException {


    public GenericException() {
        super();
    }

    public GenericException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public GenericException(final String message) {
        super(message);
    }

    public GenericException(final Throwable cause) {
        super(cause);
    }

}