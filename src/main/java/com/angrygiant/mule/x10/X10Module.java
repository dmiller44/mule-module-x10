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

import com.angrygiant.mule.x10.exception.ExecutorException;
import com.angrygiant.mule.x10.exception.ExecutorNotFoundException;
import com.angrygiant.mule.x10.exception.LookupServiceException;
import com.angrygiant.mule.x10.exception.LookupServiceNotFoundException;
import com.angrygiant.mule.x10.executor.ExecutorFactory;
import com.angrygiant.mule.x10.executor.X10Executor;
import com.angrygiant.mule.x10.lookup.LookupService;
import com.angrygiant.mule.x10.lookup.LookupServiceFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mule.api.annotations.Module;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;
import org.mule.api.annotations.param.Payload;

import javax.annotation.PostConstruct;

/**
 * Generic module
 *
 * @author MuleSoft, Inc.
 */
@Module(name="x10", schemaVersion="0.1")
public class X10Module
{
    private static final Logger logger = Logger.getLogger(X10Module.class);
    /**
     * Configurable
     */
    @Configurable
    @Optional
    @Default(X10ModuleConstants.EXECUTOR_STDOUT)
    private String executor;

    private X10Executor configuredExecutor;

    @PostConstruct
    public void initializeModule() throws ExecutorNotFoundException {
        this.configuredExecutor = ExecutorFactory.findExecutor(executor);
    }

    /**
     * Execute processor
     *
     * <p>Capable of executing X10 commands using the configured executor class.  Multiple variations exist.</p>
     *
     * {@sample.xml ../../../doc/X10-connector.xml.sample x10:execute}
     *
     * @param command Command to be executed (on, off, bright, dim)
     * @param retries # of retries the executor should execute
     * @param houseCode convenience attribute to set the house code without creating an X10Address object
     * @param unitCode convenience attribute to set the unit code without creating an X10Address object
     * @param extendedCommand extended command (if applicable)
     * @param messagePayload should be instance of X10Address, otherwise houseCode & unitCode must be set.
     * @return instance of X10Command
     */
    @Processor
    public X10Command execute(
            String command,
            @Optional @Default("1") int retries,
            @Optional String houseCode,
            @Optional Integer unitCode,
            @Optional String extendedCommand,
            @Payload Object messagePayload) throws ExecutorException
    {
        X10Address address = null;

        logger.info("Verify that the payload, or the house code & unit code, are properly set");
        if (!(messagePayload instanceof X10Address) && (StringUtils.isBlank(houseCode) || unitCode == null)) {
            throw new ExecutorException(null, null, null, "No X10Address found in Mule payload, and the houseCode and/or unitCode attributes were not set!!!");
        } else if (!(messagePayload instanceof X10Address)) {
            address = new X10Address(houseCode, unitCode);
        } else {
            address = (X10Address) messagePayload;
        }

        logger.info("Checking for not-null executor instance...");
        if (this.configuredExecutor == null) {
            throw new ExecutorException(address, command, extendedCommand, "Instance of executor class was found as null");
        }

        logger.info("Turn over control to the executor class");
        return this.configuredExecutor.execute(address, command, extendedCommand, retries);
    }

    /**
     * Execute X10Address lookup
     *
     * <p>Capable of looking up X10 addresses using a given store kind.  Multiple variations exist.</p>
     *
     * {@sample.xml ../../../doc/X10-connector.xml.sample x10:lookup}
     *
     * @param store   The kind of store you want to lookup the address from
     * @param location   The location string for the store kind (represents something specific to store kind)
     * @param key       The key to lookup
     *
     * @throws LookupServiceException  Generic exception that occurs when store misbehaves
     *
     * @return instance of X10Address
     */
    @Processor
    public X10Address lookup(@Optional @Default("properties") String store,@Optional @Default("/etc/x10/lookup.properties") String location, String key) throws LookupServiceException {
        X10Address address = null;

        LookupService service = null;
        try {
            service = LookupServiceFactory.findLookupService(store);
        } catch (LookupServiceNotFoundException e) {
            logger.error("Error using factory to retrieve lookup service");
            throw new LookupServiceException("Could not locate service with key " + store, e);
        }

        address = service.lookupDevice(location, key);

        return address;
    }

    /*
        Getters and setters
     */

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }
}
