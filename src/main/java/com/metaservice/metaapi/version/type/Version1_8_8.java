package com.metaservice.metaapi.version.type;

import com.metaservice.metaapi.version.Version;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Version1_8_8 implements Version {
    @Override
    public CommandMap getCommandMap() {
        try {
            CraftServer craftServer = (CraftServer) Bukkit.getServer();
            Method method = craftServer.getClass().getMethod("getCommandMap");
            return (CommandMap) method.invoke(craftServer);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ItemStack getItemInHand(Player player) {
        return ((CraftPlayer) player).getItemInHand();
    }

    @Override
    public void setItemInHand(Player player, ItemStack itemStack) {
        ((CraftPlayer) player).setItemInHand(itemStack);
    }

    @Override
    public void sendActionBar(Player player, String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"), (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    @Override
    public ItemStack addGlow(ItemStack itemStack) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = null;
        if (!nmsItem.hasTag()) {
            tag = new NBTTagCompound();
            nmsItem.setTag(tag);
        }
        if (tag == null) {
            tag = nmsItem.getTag();
        }

        NBTTagCompound glowTag = new NBTTagCompound();
        glowTag.set("ench", new net.minecraft.server.v1_8_R3.NBTTagList());
        tag.set("HideFlags", new net.minecraft.server.v1_8_R3.NBTTagInt(1));
        tag.set("ench", new net.minecraft.server.v1_8_R3.NBTTagList());

        nmsItem.setTag(tag);

        return CraftItemStack.asBukkitCopy(nmsItem);
    }
}
