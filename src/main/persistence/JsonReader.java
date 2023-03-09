package persistence;

import model.Account;
import model.Categories;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads the saved classes from the JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    public Account readAccount() {
        try {
            String jsonData = readFile();
            JSONObject obj = new JSONObject(jsonData);
            return parseAccount(obj);
        } catch (IOException e) {
            return null;
        }
    }

    public Categories readCategories() {
        try {
            String jsonData = readFile();
            JSONObject obj = new JSONObject(jsonData);
            return parseCategories(obj);
        } catch (IOException e) {
            return null;
        }
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    private Account parseAccount(JSONObject obj) {
        String name = obj.getString("name");
        String email = obj.getString("email");
        Account account = new Account(name, email);
        return account;
    }

    private Categories parseCategories(JSONObject obj) {
        Categories categories = new Categories();
        return categories;
    }
}
