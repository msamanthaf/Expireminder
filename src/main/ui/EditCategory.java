package ui;

import model.Categories;
import model.Items;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class EditCategory {
    private Scanner input = new Scanner(System.in);
    private Categories category;
    private Home home;
    private String dateFormat;

    public EditCategory(Home home) {
        this.home = home;
        category = new Categories();
    }

    public void showAllCategories() {
        for (int i = 0; i < category.getCategoryName().size(); i++) {
            System.out.println(category.getCategoryIndex().get(i) + ")" + " " + category.getCategoryName().get(i));
        }
        if (category.getCategoryName().size() > 0) {
            System.out.println("Enter category number to edit the category");
        }
    }

    public void addCategory() {
        System.out.println("Category name:");
        String categoryName = input.nextLine();
        String finalName = invalidCategory(categoryName);
        category.add(finalName);
    }

    public void modifyCategory(String i) {
        System.out.println("Press 'r' to rename, 'x' to delete, '+' to add a new item, or any key to go back");
        String edit = input.nextLine();
        switch (edit) {
            case "r":
                System.out.println("Rename category to: ");
                String rename = input.nextLine();
                String finalName = invalidCategory(rename);
                category.rename(Integer.parseInt(i), finalName);
                home.greetings();
            case "x":
                int index = Integer.parseInt(i) - 1;
                category.delete(index);
                home.greetings();
            case "+":
                addItem();
                home.greetings();
            default:
                home.greetings();
        }
    }

    public String invalidCategory(String name) {
        while (name.equals("") || category.getCategoryName().contains(name)) {
            if (name.equals("")) {
                System.out.println("A category name cannot be blank");
            } else if (category.getCategoryName().contains(name)) {
                System.out.println("Category name cannot be the same as existing ones");
            }
            System.out.println("Category name:");
            name = input.nextLine();
        }
        return name;
    }

    public ArrayList<String> getCategory() {
        return category.getCategoryIndex();
    }

    public void addItem() {
        System.out.println("Item name:");
        String name = input.nextLine();
        String finalName = invalidString(name);

        System.out.println("Quantity:");
        String quantity = input.nextLine();
        Integer finalQuantity = invalidQuantity(quantity);

        System.out.println("Expiry date: (MM/dd/yyyy)");
        String date = input.nextLine();
        String finalDate = invalidDate(date);

        Items item = new Items(finalName, finalQuantity, finalDate);
    }

    public String invalidString(String name) {
        while (name.equals("")) {
            System.out.println("An item name cannot be blank");
            System.out.println("Item name:");
            name = input.nextLine();
        }
        return name;
    }

    public Integer invalidQuantity(String quantity) {
        while (quantity.equals("") || (!isInteger(quantity, quantity.length())) || (Integer.parseInt(quantity) <= 0)) {
            System.out.println("Please enter a number > 0");
            System.out.println("Quantity:");
            quantity = input.nextLine();
        }
        return Integer.parseInt(quantity);
    }

    public boolean isInteger(String str, int n) {
        for (int i = 0; i < n; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private String invalidDate(String date) {
        while (!dateFormat(date)) {
            System.out.println("Please enter a valid date (MM/dd/yyyy)");
            System.out.println("Expiry Date:");
            date = input.nextLine();
        }
        return date;
    }

    private boolean dateFormat(String date) {
        dateFormat = "MM/dd/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}