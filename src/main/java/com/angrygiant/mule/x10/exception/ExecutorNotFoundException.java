package com.angrygiant.mule.x10.exception;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/16/12
 * Time: 9:37 PM
 *
 * Typically thrown if the executor attribute of <x10:config /> is set improperly.
 */
public class ExecutorNotFoundException extends Exception {

    public ExecutorNotFoundException(String message) {
        super(message);
    }
}
