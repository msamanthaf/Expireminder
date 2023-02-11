package model;

import java.util.ArrayList;

public class Categories {
    private ArrayList<String> categoryName;
    private ArrayList<String> categoryIndex;
    private ArrayList<ArrayList<Items>> categoryItems;
    private ArrayList<Items> goodCondition = new ArrayList<>();
    private ArrayList<Items> expiringSoon = new ArrayList<>();
    private ArrayList<Items> expired = new ArrayList<>();

    public Categories() {
        categoryName = new ArrayList<>();
        categoryIndex = new ArrayList<>();
        categoryItems = new ArrayList<>();
    }

    public void add(String name) {
        categoryIndex.add(String.valueOf(categoryName.size() + 1));
        categoryName.add(name);
        categoryItems.add(new ArrayList<>());
    }

    public void rename(int i, String name) {
        categoryName.set(i - 1, name);
    }

    public void delete(int index) {
        categoryName.remove(index);
        categoryIndex.remove(getCategoryIndex().size() - 1);
        categoryItems.remove(index);
    }

    public void addItem(Items i, Integer category) {
        ArrayList<Items> items1 = categoryItems.get(category - 1);
        items1.add(i);
        categoryItems.set(category - 1, items1);
        addStatus(i);
    }

    public void addStatus(Items i) {
        Notification dummy = new Notification(i.getDate());
        if (dummy.getExpired()) {
            expired.add(i);
        } else if (dummy.getNotified()) {
            expiringSoon.add(i);
        } else {
            goodCondition.add(i);
        }
        dummy = null;
    }

    public ArrayList<String> getCategoryIndex() {
        return categoryIndex;
    }

    public ArrayList<String> getCategoryName() {
        return categoryName;
    }

    public ArrayList<ArrayList<Items>> getCategoryItems() {
        return categoryItems;
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