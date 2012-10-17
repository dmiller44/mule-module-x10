package com.angrygiant.mule.x10;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/16/12
 * Time: 8:03 PM
 *
 * Unit test of X10Address object
 */
public class X10AddressTest {
    @Test
    public void testGetHouseCode() throws Exception {
        X10Address address = new X10Address();
        address.setHouseCode(X10ModuleConstants.HOUSECODE_B);

        Assert.assertEquals(address.getHouseCode(), X10ModuleConstants.HOUSECODE_B);
    }

    @Test
    public void testSetHouseCode() throws Exception {
        X10Address address = new X10Address();

        Assert.assertNotNull(address.getHouseCode());
        Assert.assertEquals(address.getHouseCode(), X10ModuleConstants.HOUSECODE_A);

        address.setHouseCode(X10ModuleConstants.HOUSECODE_C);
        Assert.assertEquals(address.getHouseCode(), X10ModuleConstants.HOUSECODE_C);
    }

    @Test
    public void testGetUnitCode() throws Exception {
        X10Address address = new X10Address();
        address.setUnitCode(X10ModuleConstants.UNITCODE_10);

        Assert.assertEquals(address.getUnitCode(), X10ModuleConstants.UNITCODE_10);
    }

    @Test
    public void testSetUnitCode() throws Exception {
        X10Address address = new X10Address();

        Assert.assertNotNull(address.getUnitCode());
        Assert.assertEquals(address.getUnitCode(), X10ModuleConstants.UNITCODE_1);

        address.setUnitCode(X10ModuleConstants.UNITCODE_11);
        Assert.assertEquals(address.getUnitCode(), X10ModuleConstants.UNITCODE_11);
    }

    @Test
    public void testIsValid() throws Exception {
        X10Address goodAddress = new X10Address(X10ModuleConstants.HOUSECODE_B, X10ModuleConstants.UNITCODE_12);
        X10Address badAddressHC = new X10Address("Q", X10ModuleConstants.UNITCODE_10);
        X10Address badAddressUC = new X10Address(X10ModuleConstants.HOUSECODE_B, 44);
        X10Address badAddress = new X10Address("Q", 44);

        Assert.assertTrue(goodAddress.isValid());
        Assert.assertFalse(badAddressHC.isValid());
        Assert.assertFalse(badAddressUC.isValid());
        Assert.assertFalse(badAddress.isValid());
    }

    @Test
    public void testEquals() throws Exception {
        X10Address goodAddress = new X10Address(X10ModuleConstants.HOUSECODE_B, X10ModuleConstants.UNITCODE_12);
        X10Address goodAddress2 = new X10Address(X10ModuleConstants.HOUSECODE_B, X10ModuleConstants.UNITCODE_12);
        X10Address differentAddress = new X10Address(X10ModuleConstants.HOUSECODE_B, X10ModuleConstants.UNITCODE_1);

        Assert.assertTrue(goodAddress.equals(goodAddress2));
        Assert.assertFalse(goodAddress.equals(differentAddress));
    }

    @Test
    public void testHashCode() throws Exception {
        X10Address goodAddress = new X10Address(X10ModuleConstants.HOUSECODE_B, X10ModuleConstants.UNITCODE_12);
        X10Address goodAddress2 = new X10Address(X10ModuleConstants.HOUSECODE_B, X10ModuleConstants.UNITCODE_12);
        X10Address differentAddress = new X10Address(X10ModuleConstants.HOUSECODE_B, X10ModuleConstants.UNITCODE_1);

        Assert.assertEquals(goodAddress.hashCode(), goodAddress2.hashCode());
        Assert.assertFalse(goodAddress.hashCode() == differentAddress.hashCode());
    }
}
