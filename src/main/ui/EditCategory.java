package ui;

import model.Categories;
import model.Items;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

// User interaction with category area
public class EditCategory {
    private Scanner input = new Scanner(System.in);
    private Categories category;
    private Home home;
    private String dateFormat;
    private int goodCondition;
    private int expiringSoon;
    private int expired;

    // REQUIRES: the previous Home page
    // EFFECTS: initialize a new area for storing categories and items
    public EditCategory(Home home) {
        this.home = home;
        category = new Categories();
    }

    // EFFECTS: shows the number of items in the list so far
    public void showAllCategories() {
        for (int i = 0; i < category.getCategoryName().size(); i++) {
            System.out.println(category.getCategoryIndex().get(i) + ")" + " " + category.getCategoryName().get(i));
            if (category.getCategoryItems().size() > 0 && outOfBounds(i)) {
                ArrayList<Items> listOfItems = category.getCategoryItems().get(i);
                for (Items item : listOfItems) {
                    int indexPosition = listOfItems.indexOf(item);
                    System.out.println("    " + (indexPosition + 1) + ". " + item.getName() + "  x" + item.getQuantity()
                            + " ~ Expires in " + item.getDate());
                }
            }
        }
        if (category.getCategoryName().size() > 0) {
            System.out.println("Enter category number to edit the category");
        }
    }

    // EFFECTS: checks whether the i-th element exists or not
    private boolean outOfBounds(int i) {
        try {
            category.getCategoryItems().get(i);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: creates new category and adds it into category list
    public void addCategory() {
        System.out.println("Category name:");
        String categoryName = input.nextLine();
        String finalName = invalidCategory(categoryName);
        category.add(finalName);
    }

    // MODIFIES: this
    // EFFECTS: prints the available options for the chosen category and takes user input
    public void modifyCategory(String i) {
        System.out.println("Press 'r' to rename, 'x' to delete, '+' to add a new item, other keys to go back");
        if (!category.getCategoryItems().isEmpty()) {
            System.out.println("or Enter item number to view and modify");
        }
        String edit = input.nextLine();
        inputCases(i, edit);
    }

    // EFFECTS: run tasks based on user input
    private void inputCases(String i, String edit) {
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
                addItem(Integer.parseInt(i));
            default:
                ArrayList<Items> listOfItems = category.getCategoryItems().get(Integer.parseInt(i) - 1);
                if (edit.equals("")) {
                    home.greetings();
                } else if (isInteger(edit, edit.length()) && listOfItems.size() >= Integer.parseInt(edit)) {
                    editItem(Integer.valueOf(i), Integer.valueOf(edit));
                } else {
                    home.greetings();
                }
        }
    }

    // MODIFIES: this
    // EFFECTS: prints the item info and takes user input to modify item info
    private void editItem(Integer theCategory, Integer item) {
        ArrayList<Items> arrayOfItems = category.getCategoryItems().get(theCategory - 1);
        Items selected = arrayOfItems.get(item - 1);
        System.out.println("Item: " + selected.getName());
        System.out.println("Quantity: " + selected.getQuantity());
        System.out.println("Expires in: " + selected.getDate());
        System.out.println("                                         ");
        System.out.println("Press 'o' to edit item info, 'x' to delete item, '<' to go back");
        String enter = input.nextLine();
        enterCases(enter, theCategory, item);
    }

    // EFFECTS: run tasks based on user input
    private void enterCases(String enter, Integer theCategory, Integer theItem) {
        switch (enter) {
            case "o":
                renameItem(theCategory, theItem);
                break;
            case "x":
                deleteItem(theCategory, theItem);
                break;
            case "<":
                home.greetings();
                break;
            default:
                editItem(theCategory, theItem);
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes theItem from theCategory
    private void deleteItem(Integer theCategory, Integer theItem) {
        ArrayList<Items> arrayOfItems = category.getCategoryItems().get(theCategory - 1);
        arrayOfItems.remove(theItem - 1);
        home.greetings();
    }

    // MODIFIES: this
    // EFFECTS: prints the options of changing certain information for theItem from theCategory
    private void renameItem(Integer theCategory, Integer theItem) {
        ArrayList<Items> arrayOfItems = category.getCategoryItems().get(theCategory - 1);
        Items selected = arrayOfItems.get(theItem - 1);
        System.out.println("Enter the number of what you would like to modify: 1. Name 2. Quantity 3. Expiry Date");
        String rename = input.nextLine();
        renameSelection(theCategory, theItem, selected, rename);
    }

    // MODIFIES: this
    // EFFECTS: takes new user input for theItem from theCategory
    private void renameSelection(Integer theCategory, Integer theItem, Items selected, String rename) {
        switch (rename) {
            case "1":
                System.out.println("Enter new item name:");
                String newName = input.nextLine();
                String validName = invalidString(newName);
                selected.setName(validName);
                editItem(theCategory, theItem);
            case "2":
                System.out.println("Enter new item quantity:");
                String newQuantity = input.nextLine();
                Integer validQuantity = invalidQuantity(newQuantity);
                selected.setQuantity(validQuantity);
                editItem(theCategory, theItem);
            case "3":
                System.out.println("Enter new item expiry date:");
                String newDate = input.nextLine();
                String validDate = invalidDate(newDate);
                selected.setDate(validDate);
                editItem(theCategory, theItem);
            default:
                String enter = input.nextLine();
                enterCases(enter, theCategory, theItem);
        }
    }

    // MODIFIES: name
    // EFFECTS: checks if name has been used as another category label
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

    // MODIFIES: this
    // EFFECTS: prints instructions, takes user input, and stores data to category
    public void addItem(Integer i) {
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
        category.addItem(item, i);
    }

    // MODIFIES: name
    // EFFECTS: prints comments if input is blank
    public String invalidString(String name) {
        while (name.equals("")) {
            System.out.println("An item name cannot be blank");
            System.out.println("Item name:");
            name = input.nextLine();
        }
        return name;
    }

    // MODIFIES: quantity
    // EFFECTS: retakes user input if input is empty or not a number greater than 0
    public Integer invalidQuantity(String quantity) {
        while (quantity.equals("") || (!isInteger(quantity, quantity.length())) || (Integer.parseInt(quantity) <= 0)) {
            System.out.println("Please enter a number > 0");
            System.out.println("Quantity:");
            quantity = input.nextLine();
        }
        return Integer.parseInt(quantity);
    }

    // EFFECTS: checks whether input is fully an integer
    public boolean isInteger(String str, int n) {
        for (int i = 0; i < n; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // MODIFIES: date
    // EFFECTS: prints comments and retakes user input if input is not a valid date
    private String invalidDate(String date) {
        while (!dateFormat(date)) {
            System.out.println("Please enter a valid date (MM/dd/yyyy)");
            System.out.println("Expiry Date:");
            date = input.nextLine();
        }
        return date;
    }

    // EFFECTS: checks the validity of the input date
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

    // MODIFIES: this
    // EFFECTS: sorts every item present in category and display the number items inside each group
    public void checkStatus() {
        category.getGoodCondition().clear();
        category.getExpiringSoon().clear();
        category.getExpired().clear();
        for (ArrayList<Items> listOfItems : category.getCategoryItems()) {
            for (Items item : listOfItems) {
                category.addStatus(item);
            }
        }
        goodCondition = category.getGoodCondition().size();
        expiringSoon = category.getExpiringSoon().size();
        expired = category.getExpired().size();
    }

    public ArrayList<String> getCategory() {
        return category.getCategoryIndex();
    }

    public int getGoodCondition() {
        return goodCondition;
    }

    public int getExpiringSoon() {
        return expiringSoon;
    }

    public int getExpired() {
        return expired;
    }
}