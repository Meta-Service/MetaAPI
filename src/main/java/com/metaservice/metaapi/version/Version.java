package com.metaservice.metaapi.version;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public interface Version {

    CommandMap getCommandMap();
    ItemStack getItemInHand(Player player);
    void setItemInHand(Player player, ItemStack itemStack);
    void sendActionBar(Player player, String message);
    ItemStack addGlow(ItemStack itemStack);

}
