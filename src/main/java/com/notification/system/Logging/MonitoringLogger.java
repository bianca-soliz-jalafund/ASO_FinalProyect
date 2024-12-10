package com.notification.system.Logging;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MonitoringLogger {
    private static final Logger logger = Logger.getLogger(MonitoringLogger.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("monitoring.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (Exception e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    public static void log(String message) {
        logger.info(message);
    }
}
