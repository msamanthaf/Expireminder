package persistence;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a printer that writes the JSON representation of the desired class to save into file
public class JsonWriter {
    private static final int TAB = 4;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination, Writable classToSave) {
        write(destination, classToSave);
    }

    // EFFECTS: writes string to file destination
    public Boolean write(String destination, Writable classToSave) {
        try {
            PrintWriter printer = new PrintWriter(destination);
            printer.print(classToSave.toJson().toString(TAB));
            printer.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
}