package com.example.sampleforwebfluxwithsecurityjwt.exception;

/**
 * @author Anthony Jinhyuk Kim
 * @version 1.0.0
 * @since 2018-10-08
 */
public class InvalidAuthRequestException extends ObjectNotFoundException {
    public InvalidAuthRequestException(){
        super("Invalid AuthRequest is found.");
    }
}
