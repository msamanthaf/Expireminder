package ui;

import model.Account;
import model.Categories;
import model.Items;

import java.util.Scanner;

public class Home {
    private Scanner input = new Scanner(System.in);
    private String button;

    public Home() {
        System.out.println("Hello " + Account.userName + "!");
        System.out.println("Press 'e' to edit profile");
        System.out.println("=========================================================================================");
        System.out.println("You have: " + Items.nc + " items in good condition");
        System.out.println(Items.ok + " items expiring soon");
        System.out.println(Items.ex + " items expired");
        System.out.println("~ Categories ~");
        System.out.println("Press '+' to add new category");
        Categories.showAllCategories();
        button = input.next();
        button();
    }

    private void button() {
        if (button.equals("e")) {
            Expireminder.logIn();
            Expireminder h;
        } else if (button.equals("+")) {
            new Categories();
            new Home();
        } else if (Categories.categoryNumber.contains(button)) {
            Categories.editCategory(button);
        } else {
            button = input.nextLine();
            button();
        }
    }
}
