package com.metaservice.metaapi.util;

import com.metaservice.metaapi.MetaAPI;
import org.bukkit.Bukkit;

/**
 * Utility class for scheduling tasks on the Bukkit scheduler.
 */
public class Tasks {

    /**
     * Runs a task on the main server thread.
     *
     * @param runnable The task to run.
     */
    public static void run(Runnable runnable) {
        Bukkit.getScheduler().runTask(MetaAPI.getInstance(), runnable);
    }

    /**
     * Runs a task asynchronously on a separate thread.
     *
     * @param runnable The task to run.
     */
    public static void runAsynchronously(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(MetaAPI.getInstance(), runnable);
    }

    /**
     * Runs a task after a specified number of server ticks.
     *
     * @param runnable The task to run.
     * @param ticks    The number of ticks to wait before running the task.
     */
    public static void runLater(Runnable runnable, int ticks) {
        Bukkit.getScheduler().runTaskLater(MetaAPI.getInstance(), runnable, ticks);
    }

    /**
     * Runs a task asynchronously after a specified number of server ticks.
     *
     * @param runnable The task to run.
     * @param ticks    The number of ticks to wait before running the task.
     */
    public static void runLaterAsynchronously(Runnable runnable, int ticks) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(MetaAPI.getInstance(), runnable, ticks);
    }

    /**
     * Runs a repeating task with a fixed delay between each execution.
     *
     * @param runnable The task to run.
     * @param ticks    The number of ticks between each execution.
     */
    public static void runTimer(Runnable runnable, int ticks) {
        Bukkit.getScheduler().runTaskTimer(MetaAPI.getInstance(), runnable, 0, ticks);
    }

    /**
     * Runs a repeating task asynchronously with a fixed delay between each execution.
     *
     * @param runnable The task to run.
     * @param ticks    The number of ticks between each execution.
     */
    public static void runTimeAsynchronously(Runnable runnable, int ticks) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(MetaAPI.getInstance(), runnable, 0, ticks);
    }
}
