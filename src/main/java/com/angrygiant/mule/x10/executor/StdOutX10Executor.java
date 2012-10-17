package com.angrygiant.mule.x10.executor;

import com.angrygiant.mule.x10.X10Address;
import com.angrygiant.mule.x10.X10Command;
import com.angrygiant.mule.x10.X10ModuleConstants;
import com.angrygiant.mule.x10.exception.ExecutorException;
import com.angrygiant.mule.x10.exception.InvalidX10AddressException;
import com.angrygiant.mule.x10.exception.InvalidX10CommandException;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/16/12
 * Time: 9:22 PM
 *
 * Class that simply 'System.out.println" an X10 command
 *
 */
public class StdOutX10Executor implements X10Executor {
    private static final Logger logger = Logger.getLogger(StdOutX10Executor.class);

    private static StdOutX10Executor ourInstance = new StdOutX10Executor();

    private static final String delimiter = ";";

    public static StdOutX10Executor getInstance() {
        return ourInstance;
    }

    private StdOutX10Executor() {
    }

    @Override
    public X10Command execute(X10Address address, String command, String extendedCommand, int repeats) throws ExecutorException {
        logger.debug("Validating X10Address...");
        if (!address.isValid()) {
            throw new ExecutorException(address, command, extendedCommand, "Unable to execute X10 call from STDOUT Executor", new InvalidX10AddressException(address, "Invalid X10 Address detected."));
        }

        if (repeats <= 0) {
            logger.warn("You need to at least execute once - setting retries to '1'...");
            repeats = 1;
        }
        if (repeats > 5) {
            logger.warn("WARNING!!! Don't you think that " + repeats + " repeats is kind of excessive?");
        }

        X10Command x10Command = prepareCommand(address, command, extendedCommand, repeats);

        logger.info("Executing call via STDOUT...");
        for (int i = 0; i < x10Command.getRetries(); i++) {
            logger.trace("Running command for the " + i + " time");

            StringBuilder builder = new StringBuilder();
            builder.append("Address: ").append(x10Command.getAddress().toString());
            builder.append(delimiter);
            builder.append("Command: ").append(x10Command.getCommand());
            builder.append(delimiter);
            builder.append("Extended Command: ").append(x10Command.getExtendedCommand());
            builder.append(delimiter);
            builder.append("Retries: ").append(x10Command.getRetries());

            logger.warn("STDOUT X10: " + builder.toString());
        }

        return x10Command;
    }

    private X10Command prepareCommand(X10Address address, String command, String extendedCommand, int repeats) {
        return new X10Command(address, command, extendedCommand, repeats);
    }
}
