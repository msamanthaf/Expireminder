package ui;

import persistence.JsonWriter;

import java.util.Scanner;

// Home page where user can see their categories and items
public class Home {
    private Scanner input = new Scanner(System.in);
    private String button;
    private Expireminder currentExpireminder;
    private EditCategory categoryPage;

    // REQUIRES: the previous Expireminder page
    // EFFECTS: runs the home window
    public Home(Expireminder current, String inputButton) {
        this.currentExpireminder = current;
        this.categoryPage = new EditCategory(this, inputButton);
    }

    // EFFECTS: prints the home page
    public void greetings() {
        System.out.println("Hello " + currentExpireminder.getAccount().getName() + "!");
        System.out.println("Press 'e' to edit profile");
        System.out.println("=========================================================================================");
        categoryPage.checkStatus();
        System.out.println("You have: " + categoryPage.getGoodCondition()
                + " items in good condition");
        System.out.println(categoryPage.getExpiringSoon() + " items expiring soon");
        System.out.println(categoryPage.getExpired() + " items expired");
        System.out.println("~ Categories ~");
        System.out.println("Press '+' to add new category");
        categoryPage.showAllCategories();
        System.out.println("Do you want to save your progress? 'y' : Yes, 'n' : Quit without saving");
        button();
    }

    // EFFECTS: run tasks based on user input
    public void button() {
        button = input.next();
        switch (button) {
            case "e":
                currentExpireminder.editProfile();
                break;
            case "+":
                categoryPage.addCategory();
                greetings();
                break;
            default:
                saveFile(button);
                if (categoryPage.getCategory().contains(button)) {
                    categoryPage.modifyCategory(button);
                } else {
                    button = input.nextLine();
                    button();
                }
                break;
        }
    }

    private void saveFile(String button) {
        switch (button) {
            case "y":
                JsonWriter.write("./data/accountData.json", currentExpireminder.getAccount());
                JsonWriter.write("./data/categoriesData.json", categoryPage.getCategories());
                break;
            case "n":
                System.exit(0);
                break;
        }
    }
}