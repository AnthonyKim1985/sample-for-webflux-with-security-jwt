package com.example.sampleforwebfluxwithsecurityjwt.constant;

import lombok.Getter;

/**
 * @author  Anthony Jinhyuk Kim
 * @version 1.0.0
 * @since   2018-09-04
 */
@Getter
public enum ResponseStatus {
    /**
     * Success Status Codes
     */
    OK(2000, "SUCCESS"),
    OK_TOKEN_ISSUED(2001, "The token has been successfully issued."),
    OK_SIGN_OUT(2002, "The sign out has been successful."),
    OK_TOKEN_IS_ALIVE(2003, "The token is alive."),
    OK_ACCOUNT_IS_ALIVE(2004, "The account is alive."),
    OK_TOKEN_AND_ACCOUNT_ARE_ALIVE(2005, "Both token and account are alive."),

    /**
     * Failure Status Codes
     */
    BAD_REQUEST(4000, "FAILURE"),
    BAD_REQUEST_UNAUTHORIZED(4001, "The unauthorized system cannot be accessible."),
    BAD_REQUEST_ACCOUNT_EXPIRED(4002, "The account expires."),
    BAD_REQUEST_TOKEN_EXPIRED(4003, "The token expires."),
    BAD_REQUEST_TOKEN_AND_ACCOUNT_EXPIRED(4004, "Both token and account expire.");

    Integer code;
    String description;

    ResponseStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
