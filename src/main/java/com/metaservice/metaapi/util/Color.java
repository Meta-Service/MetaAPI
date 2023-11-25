package com.metaservice.metaapi.util;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for translating color codes in strings.
 */
public class Color {

    /**
     * Translates color codes in a string using '&' as the color code character.
     *
     * @param s The string to translate.
     * @return The translated string with color codes applied.
     */
    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    /**
     * Translates color codes in a list of strings using '&' as the color code character.
     *
     * @param strings The list of strings to translate.
     * @return The translated list of strings with color codes applied.
     */
    public static List<String> translate(List<String> strings) {
        // Use stream and map to apply translation to each string in the list
        return strings.stream().map(Color::translate).collect(Collectors.toList());
    }
}
