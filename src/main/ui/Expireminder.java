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

    // EFFECTS: Runs the first window
    public Expireminder() {
        System.out.println("Welcome! Let's get started");
        signUp();
    }

    // First window to input name and email address
    // MODIFIES: this
    // EFFECTS: Gets user input (name and email)
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

    public void editProfile() {
        System.out.println("New Name:");
        inputName = input.nextLine();
        System.out.println("New Email address:");
        inputEmail = input.nextLine();
        checkValid(inputName, inputEmail);
        currentAccount.setAccount(inputName, inputEmail);
        home.greetings();
    }

    // REQUIRES: A non-empty string
    // MODIFIES: name & email
    // EFFECTS: Re-obtains user input if input string is empty
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
