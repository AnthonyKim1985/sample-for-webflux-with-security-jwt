package com.example.sampleforwebfluxwithsecurityjwt.exception;

/**
 * @author Anthony Jinhyuk Kim
 * @version 1.0.0
 * @since 2018-10-08
 */
public class FailureToIssueTokenException extends ObjectNotFoundException {
    public FailureToIssueTokenException() {
        super("Fail to issue the token for authorization.");
    }
}
