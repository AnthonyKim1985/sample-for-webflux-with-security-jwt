package com.example.sampleforwebfluxwithsecurityjwt.exception;

/**
 * @author Anthony Jinhyuk Kim
 * @version 1.0.0
 * @since 2018-10-05
 */
public class HttpHeaderNotFoundException extends ObjectNotFoundException {
    public HttpHeaderNotFoundException(String headerName) {
        super(String.format("The http header %s is not found.", headerName));
    }
}
