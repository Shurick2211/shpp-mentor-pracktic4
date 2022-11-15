package com.nimko.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class for gets properties from file.
 */
public class LoadProperties {
    /**The logger for this class*/
    private static final Logger log = LoggerFactory.getLogger(LoadProperties.class);

    /**The Properties for reading*/
    private final Properties props;

    /**
     * Constructor for loads property for fileName.
     * @param fileName the name property's file.
     */
    public LoadProperties(String fileName) {
        props = new Properties();
        InputStream rootPath = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(fileName);
        try {
            props.load(rootPath);
        } catch (IOException e) {
            log.error("File {} not found! ", fileName, e);
            throw new MyRuntimeException(e);
        }
        log.debug("File {} was reading!", fileName);
    }

    /**
     * The method for get property's value for an input key.
     * @param inProp - the input key.
     * @return the value.
     */
    public String getProperty(String inProp) {
        log.debug("Return property: {}", inProp);
        return props.getProperty(inProp);
    }
}
