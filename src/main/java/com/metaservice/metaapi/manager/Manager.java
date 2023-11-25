package com.metaservice.metaapi.manager;

import lombok.Getter;
import org.bukkit.plugin.Plugin;

/**
 * Base class for plugin managers.
 */
public abstract class Manager {

    @Getter
    private final Plugin plugin;

    /**
     * Constructs a new Manager.
     *
     * @param plugin The plugin instance.
     */
    public Manager(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Called when the manager is being enabled.
     */
    public void enable() {}

    /**
     * Called when the manager is being disabled.
     */
    public void disable() {}

    /**
     * Called when the manager is being reloaded.
     */
    public void reload() {}
}
