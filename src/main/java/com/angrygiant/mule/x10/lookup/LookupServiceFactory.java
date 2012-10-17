package com.angrygiant.mule.x10.lookup;

import com.angrygiant.mule.x10.X10ModuleConstants;
import com.angrygiant.mule.x10.exception.LookupServiceNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/17/12
 * Time: 7:34 AM
 *
 * Class capable of finding LookupServices based on "store" string
 */
public class LookupServiceFactory {
    public static LookupService findLookupService(String store) throws LookupServiceNotFoundException {
        if (store.equalsIgnoreCase(X10ModuleConstants.LOOKUP_STORE_PROPERTIES)) {
            return PropertiesLookupService.getInstance();
        } else {
            throw new LookupServiceNotFoundException("Can not find lookup service with store type of " + store);
        }
    }
}
