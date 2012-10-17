package com.angrygiant.mule.x10;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/16/12
 * Time: 7:45 PM
 *
 * Represents the X10 command that was executed.  Includes the X10Address, command and extended command (if applicable)
 */
public class X10Command {

    private X10Address address;

    private String command;

    private String extendedCommand;

    private int retries;

    public X10Command(X10Address address, String command) {
        this(address, command, null, 1);
    }

    public X10Command(X10Address address, String command, String extendedCommand) {
        this(address, command, extendedCommand, 1);
    }

    public X10Command(X10Address address, String command, String extendedCommand, int retries) {
        this.address = address;
        this.command = command;
        this.extendedCommand = extendedCommand;
        this.retries = retries;
    }

    public X10Address getAddress() {
        return address;
    }

    public void setAddress(X10Address address) {
        this.address = address;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getExtendedCommand() {
        return extendedCommand;
    }

    public void setExtendedCommand(String extendedCommand) {
        this.extendedCommand = extendedCommand;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        X10Command that = (X10Command) o;

        if (retries != that.retries) return false;
        if (!address.equals(that.address)) return false;
        if (!command.equals(that.command)) return false;
        if (extendedCommand != null ? !extendedCommand.equals(that.extendedCommand) : that.extendedCommand != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = address.hashCode();
        result = 31 * result + command.hashCode();
        result = 31 * result + (extendedCommand != null ? extendedCommand.hashCode() : 0);
        result = 31 * result + retries;
        return result;
    }
}
