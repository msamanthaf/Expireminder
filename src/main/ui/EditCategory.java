package ui;

import model.Categories;

import java.util.ArrayList;
import java.util.Scanner;

public class EditCategory {
    private Scanner input = new Scanner(System.in);
    private Categories category;
    private Home home;

    public EditCategory(Home home) {
        this.home = home;
        category = new Categories(this);
    }

    public void showAllCategories() {
        for (int i = 0; i < category.getCategoryList().size(); i++) {
            System.out.println(category.getCategoryNumber().get(i) + ")" + " " + category.getCategoryList().get(i));
        }
        if (category.getCategoryList().size() > 0) {
            System.out.println("Enter category number to edit the category");
        }
    }

    public void addCategory() {
        System.out.println("Category name:");
        String categoryName = input.nextLine();
        category.add(categoryName);
    }

    public void modifyCategory(String i) {
        System.out.println("Press 'r' to rename, 'x' to delete, 'i' to add a new item, or any key to go back");
        String edit = input.nextLine();
        switch (edit) {
            case "r":
                System.out.println("Rename category to: ");
                String rename = input.nextLine();
                category.rename(Integer.parseInt(i), rename);
                home.greetings();
            case "x":
                int ind = Integer.parseInt(i) - 1;
                category.getCategoryList().remove(ind);
                category.getCategoryNumber().remove(ind);
                home.greetings();
            default:
                home.greetings();
        }
    }

    public void invalidCategory(String name) {
        while (name.equals("") || category.getCategoryList().contains(name)) {
            if (name.equals("")) {
                System.out.println("A category name cannot be blank");
            } else if (category.getCategoryList().contains(name)) {
                System.out.println("Category name cannot be the same as existing ones");
            }
            System.out.println("Category name:");
            name = input.nextLine();
        }
    }

    public ArrayList<String> getCategory() {
        return category.getCategoryNumber();
    }
}
