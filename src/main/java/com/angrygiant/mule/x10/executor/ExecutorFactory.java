package com.angrygiant.mule.x10.executor;

import com.angrygiant.mule.x10.X10ModuleConstants;
import com.angrygiant.mule.x10.exception.ExecutorNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/16/12
 * Time: 9:32 PM
 *
 * Factory class capable of reading an executor "type" and returning that class
 */
public class ExecutorFactory {
    public static X10Executor findExecutor(String type) throws ExecutorNotFoundException {
        if (type.equalsIgnoreCase(X10ModuleConstants.EXECUTOR_STDOUT)) {
            return StdOutX10Executor.getInstance();
        } else if (type.equalsIgnoreCase(X10ModuleConstants.EXECUTOR_HEYU)) {
            return HeyuCM17AExecutor.getInstance();
        } else {
            throw new ExecutorNotFoundException("Can not find Executor of type " + type);
        }
    }
}
