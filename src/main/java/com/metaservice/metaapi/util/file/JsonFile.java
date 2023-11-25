package com.metaservice.metaapi.util.file;

import com.metaservice.metaapi.MetaAPI;
import lombok.Getter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a JSON file handler for storing and retrieving data in JSON format.
 */
@Getter
public class JsonFile {

    /**
     * The file associated with this JSON handler.
     */
    private final File file;

    /**
     * The map containing the values loaded from or to be saved to the JSON file.
     */
    private Map<String, Object> values;

    /**
     * Constructs a JsonFile instance with the specified parent and child path.
     * If the file does not exist, it is created.
     *
     * @param parent The parent directory path.
     * @param child  The child file path.
     * @throws IOException if there is an issue creating the file.
     */
    public JsonFile(File parent, String child) throws IOException {
        this.values = new HashMap<>();
        this.file = new File(parent, child);

        if (!parent.exists()) parent.mkdir();

        if (!file.exists()) {
            file.createNewFile();
        }

        this.load();
    }

    /**
     * Loads values from the JSON file into the internal map.
     *
     * @throws IOException if there is an issue reading from the file.
     */
    public void load() throws IOException {
        FileReader reader = new FileReader(file);
        values = MetaAPI.getInstance().getGson().fromJson(reader, Map.class);

        reader.close();
    }

    /**
     * Saves the provided map of values to the JSON file.
     *
     * @param toSave The map of values to save.
     * @throws IOException if there is an issue writing to the file.
     */
    public void save(Map<String, Object> toSave) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8));
        writer.write(MetaAPI.getInstance().getGson().toJson(toSave));

        writer.flush();
        writer.close();
    }

}
