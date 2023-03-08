package persistence;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of  to file
public class JsonWriter {
    private static final int TAB = 4;

    // EFFECTS: writes string to file destination
    public boolean write(String destination, Writable classToSave) {
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
