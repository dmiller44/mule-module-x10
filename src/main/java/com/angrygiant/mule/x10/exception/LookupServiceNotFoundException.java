package com.angrygiant.mule.x10.exception;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/16/12
 * Time: 9:37 PM
 *
 * Typically thrown if the "store" attribute on <x10:lookup /> is set improperly.
 */
public class LookupServiceNotFoundException extends Exception {

    public LookupServiceNotFoundException(String message) {
        super(message);
    }
}
