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
        button = input.nextLine();
        button();
    }

    private void button() {
        switch (button) {
            case "a":
                Expireminder.logIn();
            case "+":
                new Categories();
                new Home();
            default:
                button = input.nextLine();
                button();
        }
    }
}
