package com.angrygiant.mule.x10.exception;

import com.angrygiant.mule.x10.X10Address;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/16/12
 * Time: 8:41 PM
 *
 * Represents invalid X10 addresses.
 */
public class InvalidX10AddressException extends Exception {
    private X10Address address;

    public InvalidX10AddressException(X10Address address, String message) {
        super(message);
        this.address = address;
    }

    public X10Address getAddress() {
        return address;
    }
}
