package com.angrygiant.mule.x10.exception;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/16/12
 * Time: 8:38 PM
 *
 * Used anytime an invalid command is attempted from an executor
 *
 */
public class InvalidX10CommandException extends Exception {

    private String command;

    public InvalidX10CommandException(String command, String message) {
        super(message);
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
