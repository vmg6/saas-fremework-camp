package base.core;

import base.core.helpers.ResourceUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by @v.matviichenko
 */
public class TestProperties {
    private static final Logger LOGGER = Logger.getLogger(TestProperties.class);
    private static final String SUFIX = ".properties";
    private Properties serverProperties = loadProperties("config/server", System.getProperty("servers"));
    private Properties commonProperties = loadProperties("config/common", "common");
    private static TestProperties TEST_PROPERTIES = new TestProperties();


    private TestProperties() {

    }

    public static TestProperties getInstance(){
        return TEST_PROPERTIES;
    }

    public Properties getServerProperties() {

        return serverProperties;
    }

    public Properties getCommonProperties() {

        return commonProperties;
    }

    public String getServerProperty(final String parameterName, final String defaultValue) {
        return serverProperties.getProperty(parameterName, defaultValue);
    }
    public String getServerProperty(final String parameterName) {
        return serverProperties.getProperty(parameterName);
    }

    public String getCommonProperty(final String parameterName, final String defaultValue) {
        return commonProperties.getProperty(parameterName, defaultValue);
    }
    public String getCommonProperty(final String parameterName) {
        return commonProperties.getProperty(parameterName);
    }

    private Properties loadProperties(String path, String configName){
        try {
            return loadPropertiesException(path, configName);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException("Error load property file '" + configName);
        }
    }

    private Properties loadPropertiesException(String path, String configName) throws IOException {
//        if (configName == null || configName.isEmpty()) {
//            LOGGER.warn("configName is empty");
//            return null;
//        }

        LOGGER.info("load Config " + configName);
        String configFile = String.format("%s/%s%s",
                path, configName, SUFIX);
        Properties properties = new java.util.Properties();

        properties.load(ResourceUtil.getResourceAsStream(configFile));

        return properties;
    }

}
