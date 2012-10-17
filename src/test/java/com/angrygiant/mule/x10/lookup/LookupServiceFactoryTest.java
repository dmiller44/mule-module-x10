package com.angrygiant.mule.x10.lookup;

import com.angrygiant.mule.x10.X10ModuleConstants;
import com.angrygiant.mule.x10.exception.LookupServiceException;
import com.angrygiant.mule.x10.exception.LookupServiceNotFoundException;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/17/12
 * Time: 8:45 AM
 *
 * Unit testing LookupServiceFactory
 */
public class LookupServiceFactoryTest {
    @Test
    public void testFindLookupService() throws Exception {
        LookupService service = LookupServiceFactory.findLookupService(X10ModuleConstants.LOOKUP_STORE_PROPERTIES);
        Assert.assertNotNull(service);

        try {
            LookupService nonExistentService = LookupServiceFactory.findLookupService("foo");
            Assert.assertTrue("How does foo get properly returned from this factory class?", false);
        } catch (LookupServiceNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(true);
        }
    }
}
