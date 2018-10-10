package com.example.sampleforwebfluxwithsecurityjwt.exception;

/**
 * @author  Anthony Jinhyuk Kim
 * @version 1.0.0
 * @since   2018-09-14
 */
public class UnauthorizedUserException extends ObjectNotFoundException {
    public UnauthorizedUserException(String userId) {
        super(String.format("The unauthorized user cannot be accessible. (userId: %s)", userId));
    }
}
