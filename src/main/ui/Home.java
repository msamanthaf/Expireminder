package ui;

import model.Categories;
import ui.EditCategory;
import model.Items;

import java.util.Scanner;

public class Home {
    private Scanner input = new Scanner(System.in);
    private String button;
    private Expireminder currentExpireminder;
    private EditCategory categoryPage;
    private Categories category;

    public Home(Expireminder current) {
        this.currentExpireminder = current;
        this.categoryPage = new EditCategory(this);
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
        categoryPage.showAllCategories();
        button();
    }

    public void button() {
        button = input.next();
        if (button.equals("e")) {
            currentExpireminder.editProfile();
        } else if (button.equals("+")) {
            categoryPage.addCategory();
            greetings();
        } else if (categoryPage.getCategory().contains(button)) {
            categoryPage.modifyCategory(button);
        } else {
            button = input.nextLine();
            button();
        }
    }
}