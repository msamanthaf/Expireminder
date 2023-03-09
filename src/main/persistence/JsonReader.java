package persistence;

import model.Account;
import model.Categories;
import model.Items;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        JSONArray nameArray = obj.getJSONArray("category name");
        JSONArray indexArray = obj.getJSONArray("category index");
        JSONArray itemsArray = obj.getJSONArray("category items");
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> index = new ArrayList<>();

        for (Object s : nameArray) {
            names.add((String) s);
        }
        categories.setCategoryName(names);

        for (Object s : indexArray) {
            index.add((String) s);
        }
        categories.setCategoryIndex(index);

        parseItems(categories, itemsArray);

        return categories;

    }

    private void parseItems(Categories categories, JSONArray itemsArray) {
        ArrayList<ArrayList<Items>> items = new ArrayList<>();
        for (Object i : itemsArray) {
            ArrayList<Items> item = new ArrayList<>();
            JSONArray listOfItems = (JSONArray) i;
            for (Object l : listOfItems) {
                String name = ((JSONObject) l).getString("name");
                Integer quantity = ((JSONObject) l).getInt("quantity");
                String date = ((JSONObject) l).getString("date");
                JSONObject notification = ((JSONObject) l).getJSONObject("notification");
                Boolean notified = notification.getBoolean("notified");
                Items eachItem = new Items(name, quantity, date);
                eachItem.setNotification(notified);
                item.add(eachItem);
            }
            items.add(item);
        }
        categories.setCategoryItems(items);
    }
}