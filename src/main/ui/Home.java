package ui;

import model.Account;

import java.util.Scanner;

public class Home {
    private Scanner input = new Scanner(System.in);
    private String edit;

    public Home() {
        System.out.println("Hello " + Account.userName + "!");
        System.out.println("Press 'e' To edit profile");
        System.out.println("=========================================================================================");
        edit = input.nextLine();
        editProfile();
    }

    private void editProfile() {
        if (edit.equals("e")) {
            Expireminder.logIn();
            Expireminder h;
        }
    }
}
