package hu.masterfield.utils;

import hu.masterfield.testcases.BaseTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigData extends Properties {
    protected static Logger logger = LogManager.getLogger(ConfigData.class);
    public ConfigData() {
        InputStream is = BaseTest.class.getResourceAsStream(Consts.CONFIG_PROPERTIES);
        try {
            load(is);
        } catch (IOException ex) {
            logger.warn("Exception:" + ex.getMessage());
        }
        logger.info((Consts.CONFIG_PROPERTIES + " loaded."));
    }
}