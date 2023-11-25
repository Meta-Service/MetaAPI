package com.metaservice.metaapi.util;

import com.metaservice.metaapi.MetaAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Utility class for building ItemStacks with various properties.
 */
public class ItemBuilder {

    private Material material;
    private ItemMeta itemMeta;
    private boolean glowing = false;

    /**
     * Constructs an ItemBuilder for a specific Material type.
     *
     * @param material The Material type of the ItemStack to be built.
     */
    public ItemBuilder(Material material) {
        this.material = material;
        this.itemMeta = new ItemStack(material).getItemMeta();
    }

    /**
     * Sets the display name of the ItemStack being built.
     *
     * @param displayName The display name to set.
     * @return The ItemBuilder instance for method chaining.
     */
    public ItemBuilder setDisplayName(String displayName) {
        itemMeta.setDisplayName(Color.translate(displayName));
        return this;
    }

    /**
     * Adds a line to the lore of the ItemStack being built.
     *
     * @param line The lore line to add.
     * @return The ItemBuilder instance for method chaining.
     */
    public ItemBuilder addLoreLine(String line) {
        List<String> lore = itemMeta.getLore();
        lore.add(Color.translate(line));
        itemMeta.setLore(lore);
        return this;
    }

    /**
     * Sets the lore of the ItemStack being built.
     *
     * @param lore The list of lore lines to set.
     * @return The ItemBuilder instance for method chaining.
     */
    public ItemBuilder setLore(List<String> lore) {
        itemMeta.setLore(lore);
        return this;
    }

    /**
     * Adds a glowing effect to the ItemStack being built.
     *
     * @return The ItemBuilder instance for method chaining.
     */
    public ItemBuilder addGlow() {
        glowing = true;
        return this;
    }

    /**
     * Builds the ItemStack with the specified properties.
     *
     * @return The final ItemStack with applied properties.
     */
    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material);
        itemStack.setItemMeta(itemMeta);

        // If glowing is enabled, apply the glow effect based on the server version
        return glowing ? MetaAPI.getInstance().getVersionManager().getVersion().addGlow(itemStack) : itemStack;
    }
}
