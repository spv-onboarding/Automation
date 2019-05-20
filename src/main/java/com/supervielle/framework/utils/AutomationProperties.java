package com.supervielle.framework.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AutomationProperties {

    private static Properties properties;

    private static Properties getInstance() throws Exception {
	if (properties == null) {
	    properties = new Properties();
	    InputStream input = AutomationProperties.class.getClassLoader()
		    .getResourceAsStream("automation.properties");
	    try {
		properties.load(input);
	    } catch (NullPointerException | FileNotFoundException e) {
		throw new Exception("Bad Dir of file", e);
	    } catch (IOException e) {
		throw new Exception("Properties not found", e);
	    }
	}
	return properties;
    }

    public static String getString(String propertyName) {
	if (System.getProperty(propertyName) != null && !System.getProperty(propertyName).isEmpty()) {
	    return System.getProperty(propertyName);
	} else {
	    try {
		return getInstance().getProperty(propertyName);
	    } catch (Exception e) {
		System.out.println(e.getMessage());
	    }
	}
	return null;
    }

}
