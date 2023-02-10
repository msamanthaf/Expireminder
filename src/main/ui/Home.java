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
        System.out.println("You have: " + categoryPage.returnCategory().getGoodCondition().size()
                + " items in good condition");
        System.out.println(categoryPage.returnCategory().getExpiringSoon().size() + " items expiring soon");
        System.out.println(categoryPage.returnCategory().getExpired().size() + " items expired");
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
}