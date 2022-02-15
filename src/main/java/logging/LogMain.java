package logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

public class LogMain {

    public static void main(String[] args) {

        //log4j2
        Logger logger = LogManager.getLogger(LogMain.class);
        //java utill logging
        java.util.logging.Logger julLogger = java.util.logging.Logger.getLogger(LogMain.class.getName());

        //Apache commone logging
        Log jclLogger = LogFactory.getLog(LogMain.class);

        //slf4j + slf4j-simple-binding
        org.slf4j.Logger slfLogger = LoggerFactory.getLogger(LogMain.class);

    }

}
