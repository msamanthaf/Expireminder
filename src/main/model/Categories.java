package model;

import ui.EditCategory;

import java.util.ArrayList;

public class Categories {
    private ArrayList<String> categoryName = new ArrayList<>();
    private ArrayList<String> categoryIndex = new ArrayList<>();
    private EditCategory page;

    public Categories(EditCategory page) {
        this.page = page;
    }

    public void add(String name) {
        String finalName = page.invalidCategory(name);
        categoryIndex.add(String.valueOf(categoryName.size() + 1));
        categoryName.add(finalName);
    }

    public void rename(int i, String name) {
        String finalName = page.invalidCategory(name);
        categoryName.set(i - 1, finalName);
    }

    public ArrayList<String> getCategoryIndex() {
        return categoryIndex;
    }

    public ArrayList<String> getCategoryName() {
        return categoryName;
    }
}