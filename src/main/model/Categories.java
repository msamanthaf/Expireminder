package model;

import ui.EditCategory;

import java.util.ArrayList;

public class Categories {
    private ArrayList<String> categoryList = new ArrayList<>();
    private ArrayList<String> categoryNumber = new ArrayList<>();
    private EditCategory page;

    public Categories(EditCategory page) {
        this.page = page;
    }

    public void add(String name) {
        page.invalidCategory(name);
        categoryNumber.add(String.valueOf(categoryList.size() + 1));
        categoryList.add(name);
    }

    public void rename(int i, String name) {
        page.invalidCategory(name);
        categoryList.set(i - 1, name);
    }

    public ArrayList<String> getCategoryNumber() {
        return categoryNumber;
    }

    public ArrayList<String> getCategoryList() {
        return categoryList;
    }
}