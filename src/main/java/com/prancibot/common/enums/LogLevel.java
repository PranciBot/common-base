package com.prancibot.common.enums;

public enum LogLevel {

    TRACE(0, "TRACE"),
    DEBUG(10, "DEBUG"),
    INFO(20, "INFO"),
    WARN(30, "WARN"),
    ERROR(40, "ERROR");

    private final int value;

    private final String label;

    LogLevel(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static LogLevel fromValue(int value) {

        for (LogLevel level : values()) {

            if (level.value == value) {
                return level;
            }
        }

        throw new IllegalArgumentException(
                "Unknown log level value: " + value
        );
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}