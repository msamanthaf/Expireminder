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
        System.out.println("Press 'a' to add new category");
        showAllCategories();
        button = input.nextLine();
        button();
    }

    private void showAllCategories() {
        for (int i = 0; i < Categories.categoryList.size(); i++) {
            System.out.println(Categories.categoryList.get(i));
        }
    }

    private void button() {
        if (button.equals("e")) {
            Expireminder.logIn();
        } else if (button.equals("a")) {
            new Categories();
            new Home();
        }

        while (!button.equals("a") || !button.equals("e")) {
            button = input.nextLine();
            button();
        }
    }
}
