package com.example.demo.customexception.book;

public class NoBookCategoryException extends RuntimeException{

    public NoBookCategoryException() {
    }

    public NoBookCategoryException(String message) {
        super(message);
    }

    public NoBookCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoBookCategoryException(Throwable cause) {
        super(cause);
    }

    public NoBookCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
