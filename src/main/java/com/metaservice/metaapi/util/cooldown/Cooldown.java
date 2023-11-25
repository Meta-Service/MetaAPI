package com.metaservice.metaapi.util.cooldown;

import com.metaservice.metaapi.util.Formatter;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A class for managing cooldowns for players based on their UUID.
 */
@Getter
@Setter
public class Cooldown {

    /**
     * A map that stores the cooldown end times for each player UUID.
     */
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    /**
     * The default cooldown duration in milliseconds.
     */
    private long defaultCooldownMillis;

    /**
     * Constructs a new Cooldown manager with a default cooldown duration.
     *
     * @param defaultCooldownMillis The default cooldown duration in milliseconds.
     */
    public Cooldown(long defaultCooldownMillis) {
        this.defaultCooldownMillis = defaultCooldownMillis;
    }

    /**
     * Sets a custom cooldown for a specific player UUID.
     *
     * @param uuid            The UUID of the player for whom to set the cooldown.
     * @param cooldownMillis  The custom cooldown duration in milliseconds.
     */
    public void setCooldown(UUID uuid, long cooldownMillis) {
        long currentTime = System.currentTimeMillis();
        long cooldownEndTime = currentTime + cooldownMillis;
        cooldowns.put(uuid, cooldownEndTime);
    }

    /**
     * Sets the default cooldown for a specific player UUID.
     *
     * @param uuid The UUID of the player for whom to set the default cooldown.
     */
    public void setDefaultCooldown(UUID uuid) {
        setCooldown(uuid, defaultCooldownMillis);
    }

    /**
     * Checks if a specific player UUID is currently on cooldown.
     *
     * @param uuid The UUID of the player to check for cooldown.
     * @return True if the player is on cooldown, false otherwise.
     */
    public boolean isOnCooldown(UUID uuid) {
        if (!cooldowns.containsKey(uuid)) {
            return false;
        }

        long currentTime = System.currentTimeMillis();
        long cooldownEndTime = cooldowns.get(uuid);

        return currentTime < cooldownEndTime;
    }

    /**
     * Gets the remaining time in milliseconds until a specific player's cooldown ends.
     *
     * @param uuid The UUID of the player for whom to get the remaining time.
     * @return The remaining time in milliseconds, or 0 if not on cooldown.
     */
    public long getRemainingTime(UUID uuid) {
        if (!cooldowns.containsKey(uuid)) {
            return 0;
        }

        long currentTime = System.currentTimeMillis();
        long cooldownEndTime = cooldowns.get(uuid);

        return Math.max(0, cooldownEndTime - currentTime);
    }

    /**
     * Gets the remaining time in a formatted string (e.g., "1m 30s") until a specific player's cooldown ends.
     *
     * @param uuid The UUID of the player for whom to get the remaining formatted time.
     * @return The formatted remaining time string.
     */
    public String getRemainingFormatted(UUID uuid) {
        return Formatter.formatTime(getRemainingTime(uuid));
    }

    /**
     * Clears the cooldown for a specific player UUID.
     *
     * @param uuid The UUID of the player for whom to clear the cooldown.
     */
    public void clearCooldown(UUID uuid) {
        cooldowns.remove(uuid);
    }
}
