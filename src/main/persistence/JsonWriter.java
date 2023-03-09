package persistence;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a printer that writes the JSON representation of the desired class to save into file
public class JsonWriter {
    private static final int TAB = 4;
    private static Boolean written;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination, Writable classToSave) {
        PrintWriter printer = write(destination);
        if (printer != null) {
            printer.print(classToSave.toJson().toString(TAB));
            printer.close();
        }
    }

    // EFFECTS: writes string to file destination
    public static PrintWriter write(String destination) {
        try {
            PrintWriter test = new PrintWriter(destination);
            written = true;
            return test;
        } catch (FileNotFoundException e) {
            written = false;
            return null;
        }
    }

    public Boolean getWritten() {
        return written;
    }
}