package com.angrygiant.mule.x10.executor;

import com.angrygiant.mule.x10.X10Address;
import com.angrygiant.mule.x10.X10Command;
import com.angrygiant.mule.x10.X10ModuleConstants;
import com.angrygiant.mule.x10.exception.ExecutorException;
import com.angrygiant.mule.x10.exception.HeyuCM17AException;
import com.angrygiant.mule.x10.exception.InvalidX10AddressException;
import com.angrygiant.mule.x10.exception.InvalidX10CommandException;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/16/12
 * Time: 8:28 PM
 *
 * Heyu CM17A Executor:  Executor class that can talk to the Heyu BASH command line call.
 */

public class HeyuCM17AExecutor implements X10Executor {
    private static final Logger logger = Logger.getLogger(HeyuCM17AExecutor.class);

    private static HeyuCM17AExecutor ourInstance = new HeyuCM17AExecutor();

    private static final String FIRECRACKER_ON = "fon";
    private static final String FIRECRACKER_OFF = "foff";
    private static final String FIRECRACKER_BRIGHT = "fbright";
    private static final String FIRECRACKER_DIM = "fdim";

    private static final String DEFAULT_HEYU_CLI = "heyu";

    public static HeyuCM17AExecutor getInstance() {
        return ourInstance;
    }

    private HeyuCM17AExecutor() {
    }

    @Override
    public X10Command execute(X10Address address, String command, String extendedCommand, int repeats) throws ExecutorException {
        logger.debug("Validating X10Address...");
        if (!address.isValid()) {
            throw new HeyuCM17AException(address, command, extendedCommand, "Unable to execute X10 call from Heyu CM17A Executor", new InvalidX10AddressException(address, "Invalid X10 Address detected."));
        }

        String firecrackerCommand = null;
        try {
            firecrackerCommand = this.convertX10CommandToFirecracker(command);
        } catch (InvalidX10CommandException ice) {
            logger.error("Received InvalidX10CommandException for command " + command, ice);
            throw new HeyuCM17AException(address, command, extendedCommand, "Unable to convert X10 command to Firecracker equivalent", ice);
        }

        if (repeats <= 0) {
            logger.warn("You need to at least execute once - setting retries to '1'...");
            repeats = 1;
        }
        if (repeats > 5) {
            logger.warn("WARNING!!! Don't you think that " + repeats + " repeats is kind of excessive?");
        }

        X10Command x10Command = prepareCommand(address, firecrackerCommand, extendedCommand, repeats);

        logger.info("Executing call via Heyu & CM17A...");
        for (int i = 0; i < x10Command.getRetries(); i++) {
            logger.trace("Running command for the " + i + " time");
            int rc = executeHeyu(x10Command);

            if (rc != 0) {
                logger.error("I received a non-zero return code (" + rc + ") for try #" + i);
                throw new HeyuCM17AException(x10Command.getAddress(), x10Command.getCommand(), x10Command.getExtendedCommand(), "Heyu returned a non-zero return code...assuming failure");
            }
        }

        return x10Command;
    }

    private int executeHeyu(X10Command x10Command) throws ExecutorException{
        ProcessBuilder processBuilder = null;

        if (x10Command.getExtendedCommand() != null) {
            logger.info("Setting process with extended command..");
            processBuilder = new ProcessBuilder(DEFAULT_HEYU_CLI, x10Command.getCommand(), x10Command.getAddress().toString(), x10Command.getExtendedCommand());
            processBuilder.redirectErrorStream(true);
        } else {
            logger.info("Setting process with simple command...");
            processBuilder = new ProcessBuilder(DEFAULT_HEYU_CLI, x10Command.getCommand(), x10Command.getAddress().toString());
            processBuilder.redirectErrorStream(true);
        }

        int rc = -1;

        try {
            rc = processBuilder.start().waitFor();
        } catch (InterruptedException e) {
            logger.error(e);
            throw new HeyuCM17AException(x10Command.getAddress(), x10Command.getCommand(), x10Command.getExtendedCommand(), "Issues waiting for execution of bash process", e);
        } catch (IOException e) {
            logger.error(e);
            throw new HeyuCM17AException(x10Command.getAddress(), x10Command.getCommand(), x10Command.getExtendedCommand(), "Issues creating bash process for heyu", e);
        }

        logger.debug("I got a return code from heyu of " + rc);
        return rc;
    }

    private X10Command prepareCommand(X10Address address, String command, String extendedCommand, int repeats) {
        return new X10Command(address, command, extendedCommand, repeats);
    }

    private String convertX10CommandToFirecracker(String command) throws InvalidX10CommandException {
        if (command.equalsIgnoreCase(X10ModuleConstants.COMMAND_ON)) {
            return FIRECRACKER_ON;
        } else if (command.equalsIgnoreCase(X10ModuleConstants.COMMAND_OFF)) {
            return FIRECRACKER_OFF;
        } else if (command.equalsIgnoreCase(X10ModuleConstants.COMMAND_BRIGHT)) {
            return FIRECRACKER_BRIGHT;
        } else if (command.equalsIgnoreCase(X10ModuleConstants.COMMAND_DIM)) {
            return FIRECRACKER_DIM;
        } else {
            throw new InvalidX10CommandException(command, "Invalid X10 Command attempted.");
        }
    }
}
