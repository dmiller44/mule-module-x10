package com.angrygiant.mule.x10.exception;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/17/12
 * Time: 7:28 AM
 *
 * Exception class for generic lookup exceptions issues.
 */
public class LookupServiceException extends Exception {

    public LookupServiceException(String message) {
        super(message);
    }

    public LookupServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
