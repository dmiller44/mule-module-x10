package com.angrygiant.mule.x10.exception;

import com.angrygiant.mule.x10.X10Address;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/16/12
 * Time: 8:46 PM
 *
 * Generic ExecutorException class that specific executor exceptions can build from.
 */
public class ExecutorException extends Exception {
    protected X10Address address;

    protected String command;

    protected String extendedCommand;

    public ExecutorException(X10Address address, String command, String extendedCommand, String message) {
        super(message);
        this.address = address;
        this.command = command;
        this.extendedCommand = command;
    }

    public ExecutorException(X10Address address, String command, String extendedCommand, String message, Throwable nested) {
        super(message, nested);
        this.address = address;
        this.command = command;
        this.extendedCommand = command;
    }

    public X10Address getAddress() {
        return address;
    }

    public String getCommand() {
        return command;
    }

    public String getExtendedCommand() {
        return extendedCommand;
    }
}
