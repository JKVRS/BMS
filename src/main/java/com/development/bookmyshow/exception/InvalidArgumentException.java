package com.development.bookmyshow.exception;

import org.aspectj.bridge.Message;

public class InvalidArgumentException extends Exception {

    public InvalidArgumentException(String message) {
        super(message);
    }
}
