package ui;

import model.Categories;
import model.Items;

import java.util.Scanner;

public class Home {
    private Scanner input = new Scanner(System.in);
    private String button;
    private Expireminder currentExpireminder;
    private Categories category;

    public Home(Expireminder current) {
        this.currentExpireminder = current;
        this.category = new Categories(this);
    }

    public void greetings() {
        System.out.println("Hello " + currentExpireminder.getAccount().getName() + "!");
        System.out.println("Press 'e' to edit profile");
        System.out.println("=========================================================================================");
        System.out.println("You have: " + Items.nc + " items in good condition");
        System.out.println(Items.ok + " items expiring soon");
        System.out.println(Items.ex + " items expired");
        System.out.println("~ Categories ~");
        System.out.println("Press '+' to add new category");
        category.showAllCategories();
        button();
    }

    public void button() {
        button = input.next();
        if (button.equals("e")) {
            currentExpireminder.editProfile();
        } else if (button.equals("+")) {
            category.addCategory();
            greetings();
        } else if (category.getCategoryNumber().contains(button)) {
            category.editCategory(button);
        } else {
            button = input.nextLine();
            button();
        }
    }
}
