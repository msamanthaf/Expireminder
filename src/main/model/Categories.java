package model;

import ui.Home;

import java.util.ArrayList;
import java.util.Scanner;

public class Categories {
    private Scanner input = new Scanner(System.in);
    private ArrayList<String> categoryList = new ArrayList<>();
    private ArrayList<String> categoryNumber = new ArrayList<>();
    private Home home;

    public Categories(Home home) {
        this.home = home;
    }

    public void addCategory() {
        System.out.println("Category name:");
        String categoryName = input.nextLine();
        checkValid(categoryName);
    }

    private void checkValid(String name) {
        while (name.equals("") || categoryList.contains(name)) {
            if (name.equals("")) {
                System.out.println("A category name cannot be blank");
            } else if (categoryList.contains(name)) {
                System.out.println("Category name cannot be the same as existing ones");
            }
            System.out.println("Category name:");
            name = input.nextLine();
        }
        categoryNumber.add(String.valueOf(categoryList.size() + 1));
        categoryList.add(name);
    }

    public void showAllCategories() {
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println(categoryNumber.get(i) + ")" + " " + categoryList.get(i));
        }
        if (categoryList.size() > 0) {
            System.out.println("Enter category number to edit the category");
        }
    }

    public void editCategory(String i) {
        System.out.println("Press 'r' to rename, 'x' to delete, 'i' to add a new item, or any key to go back");
        String edit = input.nextLine();
        switch (edit) {
            case "r":
                System.out.println("Rename category to: ");
                String rename = input.nextLine();
                checkRename(Integer.parseInt(i), rename);
            case "x":
                int ind = Integer.parseInt(i) - 1;
                categoryList.remove(ind);
                categoryNumber.remove(ind);
                home.greetings();
            default:
                home.greetings();
        }
    }

    private void checkRename(int i, String name) {
        while (name.equals("") || categoryList.contains(name)) {
            if (name.equals("")) {
                System.out.println("A category name cannot be blank");
            } else if (categoryList.contains(name)) {
                System.out.println("Category name cannot be the same as existing ones");
            }
            System.out.println("Category name:");
            name = input.nextLine();
        }
        categoryList.set(i - 1, name);
        home.greetings();
    }

    public ArrayList<String> getCategoryNumber() {
        return categoryNumber;
    }
}