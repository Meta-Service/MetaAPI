package com.metaservice.metaapi.util;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * Utility class for handling item-related operations.
 */
public class ItemUtil {

    /**
     * Gives an ItemStack to a player, either by adding it to their inventory or dropping it on the ground.
     *
     * @param player    The player to give the item to.
     * @param itemStack The ItemStack to give.
     * @param drop      If true, drops the item on the ground if the player's inventory is full.
     */
    public static void giveItem(Player player, ItemStack itemStack, boolean drop) {
        PlayerInventory inventory = player.getInventory();

        // Attempt to add the item to the player's inventory
        if (inventory.addItem(itemStack).isEmpty()) {
            return;
        }

        int remainingAmount = itemStack.getAmount();

        // Loop through the player's inventory to find similar items and add to existing stacks
        for (ItemStack contents : inventory.getContents()) {
            if (contents == null) {
                continue;
            }

            if (contents.isSimilar(itemStack)) {
                int space = contents.getMaxStackSize() - contents.getAmount();
                if (space > 0) {
                    int addAmount = Math.min(space, remainingAmount);
                    contents.setAmount(contents.getAmount() + addAmount);
                    remainingAmount -= addAmount;

                    if (remainingAmount <= 0) {
                        return;
                    }
                }
            }
        }

        // If there's still remaining amount and drop is enabled, drop the remaining items on the ground
        if (drop && remainingAmount > 0) {
            World world = player.getLocation().getWorld();
            world.dropItemNaturally(player.getLocation(), new ItemStack(itemStack.getType(), remainingAmount));
        }
    }
}
