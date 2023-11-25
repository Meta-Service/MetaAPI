package com.metaservice.metaapi.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for formatting time and date-related values.
 */
public class Formatter {

    /**
     * Formats a duration in milliseconds into a human-readable time format.
     *
     * @param milliseconds The duration in milliseconds to format.
     * @return A formatted string representing the duration.
     */
    public static String formatTime(long milliseconds) {
        long seconds = milliseconds / 1000;

        if (seconds >= 60) {
            long minutes = seconds / 60;
            seconds = seconds % 60;
            return String.format("%02d:%02d", minutes, seconds);
        } else {
            return String.format("%.1fs", (double) seconds);
        }
    }

    /**
     * Formats a LocalDateTime object into a string using the pattern "yyyy-MM-dd HH:mm:ss".
     *
     * @param dateTime The LocalDateTime object to format.
     * @return A formatted string representing the date and time.
     */
    public static String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(dateTime);
    }
}