package com.angrygiant.mule.x10.lookup;

import com.angrygiant.mule.x10.X10Address;
import com.angrygiant.mule.x10.X10ModuleConstants;
import com.angrygiant.mule.x10.exception.LookupServiceException;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/17/12
 * Time: 8:27 AM
 *
 * Unit testing PropertiesLookupService functions
 */
public class PropertiesLookupServiceTest {
    private static final String LOOKUP_PROPERTIES_FILE = "./src/test/resources/x10-devices.properties";

    @Test
    public void testGetInstance() throws Exception {
        PropertiesLookupService service = null;
        Assert.assertNull(service);

        service = PropertiesLookupService.getInstance();
        Assert.assertNotNull(service);
    }

    @Test
    public void testLookupDevice() throws Exception {
        PropertiesLookupService service = PropertiesLookupService.getInstance();

        X10Address confirmAddress = new X10Address(X10ModuleConstants.HOUSECODE_A, X10ModuleConstants.UNITCODE_1);

        X10Address address = service.lookupDevice(LOOKUP_PROPERTIES_FILE, "front-light");

        Assert.assertTrue(address.isValid());
        Assert.assertEquals(confirmAddress, address);

        try {
            service.lookupDevice(LOOKUP_PROPERTIES_FILE, "doesnt-exist");
            Assert.assertTrue("Exception did not get thrown!!!", false);
        } catch (LookupServiceException e) {
            e.printStackTrace();
            Assert.assertTrue(true);
        }

        try {
            service.lookupDevice("./nonexistentfile.properties", "doesnt-exist");
            Assert.assertTrue("Exception did not get thrown!!!", false);
        } catch (LookupServiceException e) {
            e.printStackTrace();
            Assert.assertTrue(true);
        }
    }
}
