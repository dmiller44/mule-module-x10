package com.angrygiant.mule.x10.exception;

import com.angrygiant.mule.x10.X10Address;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/16/12
 * Time: 8:33 PM
 *
 * Generic exception thrown from HeyuCM17A executor
 */
public class HeyuCM17AException extends ExecutorException {
    public HeyuCM17AException(X10Address address, String command, String extendedCommand, String message) {
        super(address, command, extendedCommand, message);
        this.address = address;
        this.command = command;
        this.extendedCommand = command;
    }

    public HeyuCM17AException(X10Address address, String command, String extendedCommand, String message, Throwable nested) {
        super(address, command, extendedCommand, message, nested);
        this.address = address;
        this.command = command;
        this.extendedCommand = command;
    }
}
