package model;

import ui.EditCategory;

import java.util.ArrayList;

public class Items {
    private EditCategory page;
    private Categories category;
    private ArrayList<String> itemIndex = new ArrayList<>();
    private ArrayList<String> itemName = new ArrayList<>();
    private ArrayList<Integer> itemQuantity = new ArrayList<>();
    private ArrayList<String> itemDate = new ArrayList<>();

    private String name;
    private int quantity;
    private String date;

    public Items(String name, Integer quantity, String date) {
        this.name = name;
        this.quantity = quantity;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

