package com.metaservice.metaapi.version;

import com.metaservice.metaapi.MetaAPI;
import com.metaservice.metaapi.manager.Manager;
import com.metaservice.metaapi.version.type.Version1_13;
import com.metaservice.metaapi.version.type.Version1_7_10;
import com.metaservice.metaapi.version.type.Version1_8_8;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the compatibility of the plugin with different server versions.
 */
public class VersionManager extends Manager {

    /**
     * The version-specific implementation of the plugin.
     */
    @Getter
    private Version version;

    /**
     * Creates a new VersionManager instance for the specified plugin.
     *
     * @param plugin The plugin instance.
     */
    public VersionManager(Plugin plugin) {
        super(plugin);
        this.enable();
    }

    /**
     * Enables the VersionManager by determining the server version and initializing
     * the corresponding Version implementation.
     */
    @Override
    public void enable() {
        // Get the NMS version from the server class package
        String nmsVer = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        int version = Integer.parseInt(nmsVer.split("_")[1]);

        // Map server versions to Version implementations
        Map<Integer, Version> versionMap = new HashMap<>();
        versionMap.put(13, new Version1_13());
        versionMap.put(8, new Version1_8_8());
        versionMap.put(7, new Version1_7_10());

        // Retrieve the corresponding Version implementation based on the server version
        this.version = versionMap.get(version);

        // Check if a Version implementation is found for the server version
        if (this.version == null) {
            Bukkit.getConsoleSender().sendMessage("[MetaApi] No version file found for version " + nmsVer + "!");
            Bukkit.getPluginManager().disablePlugin(MetaAPI.getInstance());
        }
    }
}
