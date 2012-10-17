package com.angrygiant.mule.x10.lookup;

import com.angrygiant.mule.x10.X10Address;
import com.angrygiant.mule.x10.exception.LookupServiceException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/17/12
 * Time: 7:12 AM
 *
 * Class capable of looking up X10Address' kept in a Properties file
 */
public class PropertiesLookupService implements LookupService {
    private static final Logger logger = Logger.getLogger(PropertiesLookupService.class);

    private Properties properties;

    private File propertiesFile;

    private static PropertiesLookupService ourInstance = new PropertiesLookupService();

    public static PropertiesLookupService getInstance() {
        return ourInstance;
    }

    private PropertiesLookupService() {
        properties = null;
        propertiesFile = null;
    }

    @Override
    public X10Address lookupDevice(String location, String key) throws LookupServiceException {
        try {
            setAndValidateFileLocation(location);
        } catch (FileNotFoundException fnfe) {
            logger.error(fnfe);
            throw new LookupServiceException("Error validating file location", fnfe);
        }

        try {
            ingestPropertiesFile();
        } catch (IOException e) {
            logger.error("Error reading properties file", e);
            throw new LookupServiceException("Error ingesting properties file", e);
        }

        if (!this.properties.containsKey(key)) {
            throw new LookupServiceException("Can not find key '" + key + "' in properties file.");
        }

        String value = properties.getProperty(key);

        logger.info("Found value '" + value + "'");

        String[] splitValue = value.split("\\.");

        return new X10Address(splitValue[0],Integer.parseInt(splitValue[1]));
    }

    private void ingestPropertiesFile() throws IOException {
        properties = new Properties();

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(this.propertiesFile);
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw e;
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }

    private void setAndValidateFileLocation(String location) throws FileNotFoundException {
        logger.info("Current absolute path is " + new File(".").getAbsolutePath());

        propertiesFile = new File(location);

        if (!propertiesFile.exists() || !propertiesFile.canRead()) {
            propertiesFile = null;
            throw new FileNotFoundException("Can not locate or read properties file at " + location);
        }
    }
}
