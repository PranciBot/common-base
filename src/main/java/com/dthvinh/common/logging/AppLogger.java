package com.dthvinh.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;

public class AppLogger {

    private final Logger logger;

    private AppLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public static AppLogger getLogger(Class<?> clazz) {
        return new AppLogger(clazz);
    }

    public void info(String message, Object... args) {
        logger.info(message, args);
    }

    public void debug(String message, Object... args) {
        logger.debug(message, args);
    }

    public void warn(String message, Object... args) {
        logger.warn(message, args);
    }

    public void error(String message, Object... args) {
        logger.error(message, args);
    }

    public void error(String message, Throwable ex, Object... args) {
        logger.error(message, appendException(args, ex));
    }

    public void withContext(Map<String, String> context, Runnable runnable) {
        try {
            context.forEach(MDC::put);
            runnable.run();
        } finally {
            context.keySet().forEach(MDC::remove);
        }
    }

    public void time(String operation, Runnable runnable) {
        long start = System.currentTimeMillis();
        try {
            runnable.run();
        } finally {
            long duration = System.currentTimeMillis() - start;
            logger.info("Operation {} took {} ms", operation, duration);
        }
    }

    public void safeInfo(String message, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(message, args);
        }
    }

    private Object[] appendException(Object[] args, Throwable ex) {
        Object[] newArgs = new Object[args.length + 1];
        System.arraycopy(args, 0, newArgs, 0, args.length);
        newArgs[args.length] = ex;
        return newArgs;
    }
}
