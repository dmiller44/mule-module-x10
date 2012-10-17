/**
 * Mule Development Kit
 * Copyright 2010-2011 (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * This file was automatically generated by the Mule Development Kit
 */
package com.angrygiant.mule.x10;

import org.mule.api.MuleEvent;
import org.mule.construct.Flow;
import org.mule.tck.FunctionalTestCase;
import org.mule.tck.AbstractMuleTestCase;

import org.junit.Test;
import org.mule.transport.NullPayload;

public class X10ModuleTest extends FunctionalTestCase
{
    @Override
    protected String getConfigResources()
    {
        return "mule-config.xml";
    }

    @Test
    public void testGenericExecuteCall() throws Exception
    {
        X10Command command = new X10Command(new X10Address("A",1), X10ModuleConstants.COMMAND_ON, null, 2);

        runFlowAndExpect("testGenericExecuteCall", command);
    }

    @Test
    public void testGenericExtendedExecuteCall() throws Exception
    {
        X10Command command = new X10Command(new X10Address("A",1), X10ModuleConstants.COMMAND_BRIGHT, "80", 1);

        runFlowAndExpect("testGenericExtendedExecuteCall", command);
    }

    @Test
    public void testMinimalistExecuteCall() throws Exception
    {
        X10Command command = new X10Command(new X10Address("A",1), X10ModuleConstants.COMMAND_ON, null, 1);

        runFlowAndExpect("testMinimalistExecuteCall", command);
    }

    @Test
    public void testBadAddressCall() throws Exception
    {
        runFlowAndExpect("testBadAddressCall", NullPayload.getInstance());
    }

    @Test
    public void testPropertiesLookupService() throws Exception
    {
        runFlowAndExpect("testPropertiesLookupService", new X10Address(X10ModuleConstants.HOUSECODE_A, X10ModuleConstants.UNITCODE_1));
    }

    @Test
    public void testPropertiesLookupServiceNoDevice() throws Exception
    {
        runFlowAndExpect("testPropertiesLookupServiceNoDevice", NullPayload.getInstance());
    }

    @Test
    public void testPropertiesLookupServiceNoFile() throws Exception
    {
        runFlowAndExpect("testPropertiesLookupServiceNoFile", NullPayload.getInstance());
    }

    @Test
    public void testBadStore() throws Exception
    {
        runFlowAndExpect("testBadStore", NullPayload.getInstance());
    }

    /**
    * Run the flow specified by name and assert equality on the expected output
    *
    * @param flowName The name of the flow to run
    * @param expect The expected output
    */
    protected <T> void runFlowAndExpect(String flowName, T expect) throws Exception
    {
        Flow flow = lookupFlowConstruct(flowName);
        MuleEvent event = AbstractMuleTestCase.getTestEvent(null);
        MuleEvent responseEvent = flow.process(event);

        assertEquals(expect, responseEvent.getMessage().getPayload());
    }

    /**
    * Run the flow specified by name using the specified payload and assert
    * equality on the expected output
    *
    * @param flowName The name of the flow to run
    * @param expect The expected output
    * @param payload The payload of the input event
    */
    protected <T, U> void runFlowWithPayloadAndExpect(String flowName, T expect, U payload) throws Exception
    {
        Flow flow = lookupFlowConstruct(flowName);
        MuleEvent event = AbstractMuleTestCase.getTestEvent(payload);
        MuleEvent responseEvent = flow.process(event);

        assertEquals(expect, responseEvent.getMessage().getPayload());
    }

    /**
     * Retrieve a flow by name from the registry
     *
     * @param name Name of the flow to retrieve
     */
    protected Flow lookupFlowConstruct(String name)
    {
        return (Flow) AbstractMuleTestCase.muleContext.getRegistry().lookupFlowConstruct(name);
    }
}
