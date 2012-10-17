package com.angrygiant.mule.x10.lookup;

import com.angrygiant.mule.x10.X10Address;
import com.angrygiant.mule.x10.exception.LookupServiceException;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/17/12
 * Time: 7:10 AM
 *
 * Classes implement this interface if they're capable of looking up key-based device names for X10Address objects
 */

public interface LookupService {
    public X10Address lookupDevice(String location, String key) throws LookupServiceException;
}
