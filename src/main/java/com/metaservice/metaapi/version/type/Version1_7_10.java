package com.metaservice.metaapi.version.type;

import com.metaservice.metaapi.version.Version;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Version1_7_10 implements Version {
    @Override
    public CommandMap getCommandMap() {
        try {
            CraftServer craftServer = (CraftServer) Bukkit.getServer();
            Method method = craftServer.getClass().getMethod("getCommandMap");
            return (CommandMap) method.invoke(craftServer);
        }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public ItemStack getItemInHand(Player player) {
        return ((CraftPlayer)player).getItemInHand();
    }

    @Override
    public void setItemInHand(Player player, ItemStack itemStack) {
        ((CraftPlayer)player).setItemInHand(itemStack);
    }

    @Override
    public void sendActionBar(Player player, String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + message + "\"}"), (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    @Override
    public ItemStack addGlow(ItemStack itemStack) {
        net.minecraft.server.v1_7_R4.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = null;
        if (!nmsItem.hasTag()) {
            tag = new NBTTagCompound();
            nmsItem.setTag(tag);
        }
        if (tag == null) {
            tag = nmsItem.getTag();
        }

        NBTTagCompound glowTag = new NBTTagCompound();
        glowTag.setShort("id", (short) 16); // Sharpness enchantment ID
        glowTag.setShort("lvl", (short) 32767); // Level 32767 represents a very high level

        tag.set("ench", glowTag);

        nmsItem.setTag(tag);

        return CraftItemStack.asBukkitCopy(nmsItem);
    }

}
