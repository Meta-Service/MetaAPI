package com.metaservice.metaapi.util.file;

import com.metaservice.metaapi.MetaAPI;
import com.metaservice.metaapi.util.Color;
import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a YAML configuration file for plugin settings.
 * Extends Bukkit's YamlConfiguration for configuration handling.
 */
public class ConfigFile extends YamlConfiguration {

    /**
     * The file associated with this configuration.
     */
    @Getter
    private final File file;

    /**
     * Constructs a ConfigFile instance with the specified parent and child path.
     * If the file does not exist, it is created by copying from the plugin resources.
     *
     * @param parent The parent directory path.
     * @param child  The child file path.
     * @throws RuntimeException if there is an issue loading the configuration.
     */
    public ConfigFile(String parent, String child) throws RuntimeException {
        this.file = new File(parent, child);

        if (!this.file.exists()) {
            MetaAPI.getInstance().saveResource(file.getPath(), false);
        }

        try {
            this.load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException("Failed to load configuration file: " + file.getPath(), e);
        }
    }

    /**
     * Saves the configuration to the associated file.
     */
    public void save() {
        try {
            this.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets an integer from the configuration, with a default value of 0 if not found.
     *
     * @param path The path to the configuration section.
     * @return The integer value.
     */
    @Override
    public int getInt(String path) {
        return super.getInt(path, 0);
    }

    /**
     * Gets a double from the configuration, with a default value of 0.0 if not found.
     *
     * @param path The path to the configuration section.
     * @return The double value.
     */
    @Override
    public double getDouble(String path) {
        return super.getDouble(path, 0.0);
    }

    /**
     * Gets a boolean from the configuration, with a default value of false if not found.
     *
     * @param path The path to the configuration section.
     * @return The boolean value.
     */
    @Override
    public boolean getBoolean(String path) {
        return super.getBoolean(path, false);
    }

    /**
     * Gets a string from the configuration, translating color codes, with a default value of an empty string if not found.
     *
     * @param path The path to the configuration section.
     * @return The translated string value.
     */
    @Override
    public String getString(String path) {
        return Color.translate(super.getString(path, ""));
    }

    /**
     * Gets a list of strings from the configuration, translating color codes, with a default value of an empty list if not found.
     *
     * @param path The path to the configuration section.
     * @return The translated list of strings.
     */
    @Override
    public List<String> getStringList(String path) {
        return super.getStringList(path).stream().map(Color::translate).collect(Collectors.toList());
    }
}
