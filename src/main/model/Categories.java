package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Categories {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<String> categoryList = new ArrayList<>();
    private static ArrayList<Integer> categoryNumber = new ArrayList<>();

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
        categoryNumber.add(categoryList.size() + 1);
        categoryList.add(name);
    }

    public static void showAllCategories() {
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println(categoryNumber.get(i) + ")" + " " + categoryList.get(i));
        }
        System.out.println("Enter category number to edit the category");
    }
}
