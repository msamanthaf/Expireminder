package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a category having a list of names, list of index, and list of items
public class Categories implements Writable {
    private ArrayList<String> categoryName;
    private ArrayList<String> categoryIndex;
    private ArrayList<ArrayList<Items>> categoryItems;
    private ArrayList<Items> goodCondition = new ArrayList<>();
    private ArrayList<Items> expiringSoon = new ArrayList<>();
    private ArrayList<Items> expired = new ArrayList<>();

    // EFFECTS: creates new empty list of names, index, and items
    public Categories() {
        categoryName = new ArrayList<>();
        categoryIndex = new ArrayList<>();
        categoryItems = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds new category name and its index into list along with an empty list of items
    public void add(String name) {
        categoryIndex.add(String.valueOf(categoryName.size() + 1));
        categoryName.add(name);
        categoryItems.add(new ArrayList<>());
        EventLog.getInstance().logEvent(new Event("Category: " + name + " added."));
    }

    // MODIFIES: this
    // EFFECTS: sets the new name of the given element
    public void rename(int i, String name) {
        EventLog.getInstance().logEvent(new Event("Category: " + categoryName.get(i - 1) + " renamed into "
                + name + "."));
        categoryName.set(i - 1, name);
    }

    // MODIFIES: this
    // EFFECTS: removes an index from list of name, index, and items
    public void delete(int index) {
        EventLog.getInstance().logEvent(new Event("Category: " + categoryName.get(index)
                + " and its items are deleted."));
        categoryName.remove(index);
        categoryIndex.remove(getCategoryIndex().size() - 1);
        categoryItems.remove(index);
    }

    // MODIFIES: this
    // EFFECTS: adds given item into list of items
    public void addItem(Items i, Integer category) {
        ArrayList<Items> items1 = categoryItems.get(category - 1);
        items1.add(i);
        categoryItems.set(category - 1, items1);
    }

    // MODIFIES: this
    // EFFECTS: deletes selected item from its category
    public void deleteItem(int categoryIndex, int itemIndex) {
        EventLog.getInstance().logEvent(new Event("Item: "
                + categoryItems.get(categoryIndex).get(itemIndex).getName() + " deleted."));
        ArrayList<Items> arrayOfItems = getCategoryItems().get(categoryIndex);
        arrayOfItems.remove(itemIndex);
    }

    // EFFECTS: returns this as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray nameArray = new JSONArray();
        JSONArray indexArray = new JSONArray();
        JSONArray itemsArray = new JSONArray();

        for (String s : categoryName) {
            nameArray.put(s);
        }
        json.put("category name", nameArray);

        for (String s : categoryIndex) {
            indexArray.put(s);
        }
        json.put("category index", indexArray);

        for (ArrayList<Items> items : categoryItems) {
            JSONArray itemArray = new JSONArray();
            for (Items item : items) {
                itemArray.put(item.toJson());
            }
            itemsArray.put(itemArray);
        }
        json.put("category items", itemsArray);
        return json;
    }

    public ArrayList<String> getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(ArrayList<String> name) {
        categoryName = name;
    }

    public ArrayList<String> getCategoryIndex() {
        return categoryIndex;
    }

    public void setCategoryIndex(ArrayList<String> index) {
        categoryIndex = index;
    }

    public ArrayList<ArrayList<Items>> getCategoryItems() {
        return categoryItems;
    }

    public void setCategoryItems(ArrayList<ArrayList<Items>> items) {
        categoryItems = items;
    }
}