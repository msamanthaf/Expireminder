package model;

import ui.Home;

import java.util.ArrayList;
import java.util.Scanner;

public class Categories {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<String> categoryList = new ArrayList<>();
    public static ArrayList<String> categoryNumber = new ArrayList<>();

    public Categories() {
        System.out.println("Category name:");
        String categoryName = input.nextLine();
        checkValid(categoryName);
    }

    private static void checkValid(String name) {
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

    public static void showAllCategories() {
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println(categoryNumber.get(i) + ")" + " " + categoryList.get(i));
        }
        System.out.println("Enter category number to edit the category");
    }

    public static void editCategory(String i) {
        System.out.println("Press 'r' to rename, 'x' to delete, 'o' to go back");
        String edit = input.nextLine();
        switch (edit) {
            case "r":
                System.out.println("Rename category to: ");
                String rename = input.nextLine();
                checkRename(Integer.parseInt(i), rename);
            case "x":
                categoryList.remove(Integer.parseInt(i) - 1);
                new Home();
            case "o":
                new Home();
        }
    }
    private static void checkRename(int i, String name) {
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
        new Home();
    }
}