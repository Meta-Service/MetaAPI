package com.metaservice.metaapi;

import com.google.gson.Gson;
import com.metaservice.metaapi.version.VersionManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class MetaAPI extends JavaPlugin {

    @Getter
    private static MetaAPI instance;
    private Gson gson;
    private VersionManager versionManager;
    @Override
    public void onEnable() {
        instance = this;
        this.gson = new Gson();
        this.versionManager = new VersionManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
