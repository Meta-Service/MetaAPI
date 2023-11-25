package com.metaservice.metaapi.manager;

import com.metaservice.metaapi.MetaAPI;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;

/**
 * Abstract base class for event handlers in the plugin.
 * Subclasses should extend this class and handle specific events.
 */
public abstract class Handler implements Listener {

    /**
     * The manager responsible for handling version-specific functionality.
     */
    @Getter
    private Manager manager;

    /**
     * Default constructor without a manager. Subclasses using this constructor
     * must set the manager explicitly before any event registration or usage.
     */
    public Handler() {
        this.registerListeners();
    }

    /**
     * Constructor with a manager.
     *
     * @param manager The manager responsible for handling version-specific functionality.
     */
    public Handler(Manager manager) {
        this.manager = manager;
        this.registerListeners();
    }

    /**
     * Registers event listeners for methods annotated with {@code @EventHandler}.
     * The events are registered with the Bukkit plugin manager.
     */
    private void registerListeners() {
        // Get all methods in the class
        Method[] methods = this.getClass().getMethods();

        // Iterate through methods
        for (Method method : methods) {
            // Check if the method has the @EventHandler annotation
            if (!method.isAnnotationPresent(EventHandler.class)) continue;

            // Register the class as an event listener with the Bukkit plugin manager
            Bukkit.getPluginManager().registerEvents(this, MetaAPI.getInstance());
        }
    }

    /**
     * Removes the specified amount from the item currently in the player's hand.
     * If the resulting amount is 1, sets the player's hand to AIR.
     *
     * @param player The player whose hand is being modified.
     * @param amount The amount to subtract from the item in the player's hand.
     */
    public void takeItemInHand(Player player, int amount) {
        ItemStack hand = getItemInHand(player);

        if (hand != null && hand.getAmount() > 1) {
            hand.setAmount(hand.getAmount() - amount);
            setItemInHand(player, hand);
        } else {
            setItemInHand(player, new ItemStack(Material.AIR));
        }
    }

    /**
     * Retrieves the item currently in the player's hand.
     *
     * @param player The player whose hand is being queried.
     * @return The item currently in the player's hand, or null if the hand is empty.
     */
    public ItemStack getItemInHand(Player player) {
        return MetaAPI.getInstance().getVersionManager().getVersion().getItemInHand(player);
    }

    /**
     * Sets the item in the player's hand to the specified ItemStack.
     *
     * @param player     The player whose hand is being modified.
     * @param itemStack The ItemStack to set in the player's hand.
     */
    public void setItemInHand(Player player, ItemStack itemStack) {
        MetaAPI.getInstance().getVersionManager().getVersion().setItemInHand(player, itemStack);
    }
}
