package com.AutomationExercise.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    public static final String BROWSER_NAME = getPropValues("browser_name");
    public static final String HOME_URL = getPropValues("home_url");
    public static final String REGISTER_LOGIN_URL = getPropValues("register_login_url");
    public static final String PRODUCTS_PAGE_URL = getPropValues("products_url");
    public static final String CART_PAGE_URL = getPropValues("cart_url");
    public static final Boolean REMOTE_RUN = Boolean.valueOf(getPropValues("remote_run"));


    private static Properties getPropValues() throws IOException {
        InputStream inputStream = null;
        Properties props = new Properties();

        try {
            String propFileName = "config.properties";
            inputStream = Configuration.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                props.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            assert inputStream != null;
            inputStream.close();
        }
        return props;
    }

    public static String getPropValues(String key) {
        if (System.getProperty(key) == null || System.getProperty(key).isEmpty()) {
            try {
                return getPropValues().getProperty(key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return System.getProperty(key);
    }
}

