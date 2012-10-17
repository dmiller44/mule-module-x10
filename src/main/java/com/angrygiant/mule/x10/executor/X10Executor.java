package com.angrygiant.mule.x10.executor;

import com.angrygiant.mule.x10.X10Address;
import com.angrygiant.mule.x10.X10Command;
import com.angrygiant.mule.x10.exception.ExecutorException;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/16/12
 * Time: 7:45 PM
 *
 * All X10 Executor classes must implement this interface to be legitimate.
 */
public interface X10Executor {

    /**
     * Function is responsible for taking all the pieces of an X10 call, and
     * making the call using the appropriate interface.
     *
     * @param address   represents the X10Address object
     * @param command   represents the String X10 command (on,off,etc)
     * @param extendedCommand  represents the extended value (if applicable)
     * @return instance of X10Command executed
     */
    public X10Command execute(X10Address address, String command, String extendedCommand, int repeats) throws ExecutorException;
}
