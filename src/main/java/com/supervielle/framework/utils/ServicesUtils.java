package com.supervielle.framework.utils;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.supervielle.framework.tdm.EndpointProvider;
import com.supervielle.framework.tdm.EnvironmentProvider;

public class ServicesUtils {

    private static final String ENVIRONMENT = AutomationProperties.getString("environment").replaceAll(" ", "");
    public static String ONBOARDING_APPLICATION = "onboarding";
    private static final String ENDPOINT_INDEX = "endpoint";

    public static String getEnvironment() {
        return ENVIRONMENT;
    }

    public static String getEndpoint(String app)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        System.out.println("---");
        System.out.println("ENVIRONMENT: " + EnvironmentProvider.getUrl(getEnvironment()));
        return EndpointProvider.getUrl(app + "." + ENDPOINT_INDEX + "." + getEnvironment());
    }

}
