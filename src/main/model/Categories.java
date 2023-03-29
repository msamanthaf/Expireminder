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

    private int goodItems;

    public int getGoodItems() {
        return goodItems;
    }

    public void setGoodItems(int goodItems) {
        this.goodItems = goodItems;
    }

    private int expiringItems;

    public int getExpiringItems() {
        return expiringItems;
    }

    public void setExpiringItems(int expiringItems) {
        this.expiringItems = expiringItems;
    }

    private int expiredItems;

    public int getExpiredItems() {
        return expiredItems;
    }

    public void setExpiredItems(int expiredItems) {
        this.expiredItems = expiredItems;
    }

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
    }

    // MODIFIES: this
    // EFFECTS: sets the new name of the given element
    public void rename(int i, String name) {
        categoryName.set(i - 1, name);
    }

    // MODIFIES: this
    // EFFECTS: removes an index from list of name, index, and items
    public void delete(int index) {
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
        addStatus(i);
    }

    // MODIFIES: expired, expiringSoon, goodCondition
    // EFFECTS: creates new notification for this item and sort it into a group
    public void addStatus(Items i) {
        Notification dummy = new Notification(i.getDate());
        if (dummy.getExpired()) {
            expired.add(i);
        } else if (dummy.getNotified()) {
            expiringSoon.add(i);
        } else {
            goodCondition.add(i);
        }
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
        for (ArrayList<Items> items : categoryItems) {
            for (Items item : items) {
                addStatus(item);
            }
        }
        json.put("good", getGoodCondition().size());
        json.put("soon", getExpiringSoon().size());
        json.put("exp", getExpired().size());
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

    public ArrayList<Items> getGoodCondition() {
        return goodCondition;
    }

    public ArrayList<Items> getExpiringSoon() {
        return expiringSoon;
    }

    public ArrayList<Items> getExpired() {
        return expired;
    }
}