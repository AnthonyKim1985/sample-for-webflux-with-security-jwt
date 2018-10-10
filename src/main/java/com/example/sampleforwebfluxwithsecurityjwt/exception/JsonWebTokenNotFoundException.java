package com.example.sampleforwebfluxwithsecurityjwt.exception;

/**
 * @author Anthony Jinhyuk Kim
 * @version 1.0.0
 * @since 2018-10-05
 */
public class JsonWebTokenNotFoundException extends ObjectNotFoundException {
    public JsonWebTokenNotFoundException(String token) {
        super(String.format("The Json Web Token cannot be found: %s", token));
    }
}
