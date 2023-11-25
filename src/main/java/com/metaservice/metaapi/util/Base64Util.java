package com.metaservice.metaapi.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * Utility class for converting ItemStack or ItemStack arrays to and from Base64 strings.
 */
public class Base64Util {

    /**
     * Converts an ItemStack to a Base64-encoded string.
     *
     * @param itemStack The ItemStack to convert.
     * @return A Base64-encoded string representing the ItemStack.
     */
    public static String toBase64(ItemStack itemStack) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeObject(itemStack);

            dataOutput.close();

            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts an array of ItemStacks to a Base64-encoded string.
     *
     * @param itemStackArray The array of ItemStacks to convert.
     * @return A Base64-encoded string representing the array of ItemStacks.
     */
    public static String toBase64(ItemStack[] itemStackArray) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(itemStackArray.length);

            for (ItemStack itemStack : itemStackArray) {
                dataOutput.writeObject(itemStack);
            }

            dataOutput.close();

            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts a Base64-encoded string to an ItemStack.
     *
     * @param encoded The Base64-encoded string.
     * @return An ItemStack representing the decoded string.
     */
    public static ItemStack fromBase64(String encoded) {
        try {
            byte[] data = Base64.getDecoder().decode(encoded);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            ItemStack itemStack = (ItemStack) dataInput.readObject();

            dataInput.close();

            return itemStack;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converts a Base64-encoded string to an array of ItemStacks.
     *
     * @param encoded The Base64-encoded string.
     * @return An array of ItemStacks representing the decoded string.
     */
    public static ItemStack[] arrayFromBase64(String encoded) {
        try {
            byte[] data = Base64.getDecoder().decode(encoded);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            int arrayLength = dataInput.readInt();

            ItemStack[] itemStackArray = new ItemStack[arrayLength];
            for (int i = 0; i < arrayLength; i++) {
                itemStackArray[i] = (ItemStack) dataInput.readObject();
            }

            dataInput.close();

            return itemStackArray;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
