package ui;

import java.util.Scanner;

// Home page where user can see their categories and items
public class Home {
    private Scanner input = new Scanner(System.in);
    private String button;
    private Expireminder currentExpireminder;
    private EditCategory categoryPage;

    // REQUIRES: the previous Expireminder page
    // EFFECTS: runs the home window
    public Home(Expireminder current) {
        this.currentExpireminder = current;
        this.categoryPage = new EditCategory(this);
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
                if (categoryPage.getCategory().contains(button)) {
                    categoryPage.modifyCategory(button);
                } else {
                    button = input.nextLine();
                    button();
                }
                break;
        }
    }
}