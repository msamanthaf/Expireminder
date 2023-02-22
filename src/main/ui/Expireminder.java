package ui;

import model.Account;

import java.util.Scanner;

// Expiry date notifier application
public class Expireminder {
    private Scanner input = new Scanner(System.in);
    private String inputName;
    private String inputEmail;
    private Account currentAccount;
    private Home home;

    // EFFECTS: runs the first window
    public Expireminder() {
        System.out.println("Welcome! Let's get started");
        signUp();
    }

    // MODIFIES: this
    // EFFECTS: stores user input of name and email into Account
    public void signUp() {
        System.out.println("Enter your name:");
        inputName = input.nextLine();
        System.out.println("Enter your email address:");
        inputEmail = input.nextLine();
        checkValid(inputName, inputEmail);
        currentAccount = new Account(inputName, inputEmail);
        home = new Home(this);
        home.greetings();
    }

    // MODIFIES: this, Account
    // EFFECTS: modifies user input of name and email and update the info into Account
    public void editProfile() {
        System.out.println("New Name:");
        inputName = input.nextLine();
        System.out.println("New Email address:");
        inputEmail = input.nextLine();
        checkValid(inputName, inputEmail);
        currentAccount.setAccount(inputName, inputEmail);
        home.greetings();
    }

    // MODIFIES: name, email
    // EFFECTS: re-obtains user input if input string is empty
    private void checkValid(String name, String email) {
        while (name.equals("") || email.equals("")) {
            System.out.println("Please enter a valid name and/or email address");
            name = input.nextLine();
            email = input.nextLine();
        }
        inputName = name;
        inputEmail = email;
    }

    public Account getAccount() {
        return currentAccount;
    }
}
