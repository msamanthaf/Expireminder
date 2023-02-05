package model;

import java.util.ArrayList;

public class Categories {
    private ArrayList<String> categoryName;
    private ArrayList<String> categoryIndex;
    private ArrayList<Items> categoryItems;

    public Categories() {
        categoryName = new ArrayList<>();
        categoryIndex = new ArrayList<>();
        categoryItems = new ArrayList<>();
    }

    public void add(String name) {
        categoryIndex.add(String.valueOf(categoryName.size() + 1));
        categoryName.add(name);
    }

    public void rename(int i, String name) {
        categoryName.set(i - 1, name);
    }

    public void delete(int index) {
        categoryName.remove(index);
        categoryIndex.remove(getCategoryIndex().size() - 1);
    }

    public ArrayList<String> getCategoryIndex() {
        return categoryIndex;
    }

    public ArrayList<String> getCategoryName() {
        return categoryName;
    }
}