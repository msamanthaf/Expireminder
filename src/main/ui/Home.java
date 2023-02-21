package ui;

import java.util.Scanner;

public class Home {
    private Scanner input = new Scanner(System.in);
    private String button;
    private Expireminder currentExpireminder;
    private EditCategory categoryPage;

    public Home(Expireminder current) {
        this.currentExpireminder = current;
        this.categoryPage = new EditCategory(this);
    }

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

    public Expireminder getCurrentExpireminder() {
        return currentExpireminder;
    }
}