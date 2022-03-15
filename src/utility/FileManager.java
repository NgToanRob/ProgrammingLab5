package utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import data.Organization;

import com.google.gson.JsonParseException;

/**
 * Operates the file for saving/loading collection.
 */
public class FileManager {
    private Gson gson = new Gson();
    private String envVariable;

    public FileManager(String envVariable) {
        this.envVariable = envVariable;
    }

    /**
     * Writes collection to a file.
     * 
     * @param collection Collection to write.
     */
    public void writeCollection(Collection<?> collection) {
        if (System.getenv().get(envVariable) != null) {
            try (FileWriter collectionFileWriter = new FileWriter(new File(System.getenv().get(envVariable)))) {
                collectionFileWriter.write(gson.toJson(collection));
                Console.println("Collection successfully saved to file!");
            } catch (IOException exception) {
                Console.printerror("The boot file is a directory/cannot be opened!");
            }
        } else Console.printerror("The environment variable with the boot file was not found!");
    }

    /**
     * Reads collection from a file.
     * 
     * @return Readed collection.
     */
    public ArrayList<Organization> readCollection() {
        if (System.getenv().get(envVariable) != null) {
            try (Scanner collectionFileScanner = new Scanner(new File(System.getenv().get(envVariable)))) {
                ArrayList<Organization> collection;
                Type collectionType = new TypeToken<ArrayList<Organization>>() {}.getType();
                collection = gson.fromJson(collectionFileScanner.nextLine().trim(), collectionType);
                Console.println("The collection has been uploaded successfully!");
                return collection;
            } catch (FileNotFoundException exception) {
                Console.printerror("The boot file was not found!");
            } catch (NoSuchElementException exception) {
                Console.printerror("The boot file is empty!");
            } catch (JsonParseException | NullPointerException exception) {
                Console.printerror("The required collection was not found in the boot file!");
            } catch (IllegalStateException exception) {
                Console.printerror("Unexpected error!");
                System.exit(0);
            }
        } else Console.printerror("The environment variable with the boot file was not found!");
        return new ArrayList<Organization>();
    }

    @Override
    public String toString() {
        String string = "FileManager (class for working with the boot file)";
        return string;
    }
}
