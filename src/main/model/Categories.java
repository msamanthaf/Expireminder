package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Categories {
    private static Scanner input = new Scanner(System.in);
    public String categoryName = "";
    public static ArrayList categoryList = new ArrayList();

    public Categories() {
        System.out.println("Category name:");
        categoryName = input.nextLine();
        checkValid(categoryName);
    }

    private static void checkValid(String name) {
        while (name.equals("")) {
            System.out.println("A category name cannot be blank");
            System.out.println("Category name:");
            name = input.nextLine();
        }
        categoryList.add(name);
    }
}
