package com.angrygiant.mule.x10;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/16/12
 * Time: 8:21 PM
 *
 * Unit tests for X10Command object
 */
public class X10CommandTest {
    @Test
    public void testEquals() throws Exception {
        X10Command command = new X10Command(new X10Address(X10ModuleConstants.HOUSECODE_B, X10ModuleConstants.UNITCODE_1), X10ModuleConstants.COMMAND_ON);
        X10Command commandTwo = new X10Command(new X10Address(X10ModuleConstants.HOUSECODE_B, X10ModuleConstants.UNITCODE_1), X10ModuleConstants.COMMAND_ON);
        X10Command commandThree = new X10Command(new X10Address(X10ModuleConstants.HOUSECODE_B, X10ModuleConstants.UNITCODE_1), X10ModuleConstants.COMMAND_ON, null, 2);

        Assert.assertTrue(command.equals(commandTwo));
        Assert.assertFalse(command.equals(commandThree));
    }

    @Test
    public void testHashCode() throws Exception {
        X10Command command = new X10Command(new X10Address(X10ModuleConstants.HOUSECODE_B, X10ModuleConstants.UNITCODE_1), X10ModuleConstants.COMMAND_ON);
        X10Command commandTwo = new X10Command(new X10Address(X10ModuleConstants.HOUSECODE_B, X10ModuleConstants.UNITCODE_1), X10ModuleConstants.COMMAND_ON);
        X10Command commandThree = new X10Command(new X10Address(X10ModuleConstants.HOUSECODE_B, X10ModuleConstants.UNITCODE_1), X10ModuleConstants.COMMAND_OFF, null, 2);

        Assert.assertTrue(command.hashCode() == commandTwo.hashCode());
        Assert.assertFalse(command.hashCode() == commandThree.hashCode());
    }
}
