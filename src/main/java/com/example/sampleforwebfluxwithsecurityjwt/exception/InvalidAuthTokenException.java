package com.example.sampleforwebfluxwithsecurityjwt.exception;

/**
 * @author Anthony Jinhyuk Kim
 * @version 1.0.0
 * @since 2018-10-05
 */
public class InvalidAuthTokenException extends ObjectNotFoundException {
    public InvalidAuthTokenException(String token) {
        super(String.format("Invalid token: %s", token));
    }
}
