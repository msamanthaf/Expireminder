package ui;

import model.Account;

import java.util.Scanner;

// Expiry date notifier application
public class Expireminder {
    private static Scanner input = new Scanner(System.in);
    private static String inputName = null;
    private static String inputEmail = null;

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
        Account.userName = inputName;
        Account.userEmail = inputEmail;
        new Home();
    }

    public static void logIn() {
        System.out.println("New Name:");
        inputName = input.nextLine();
        System.out.println("New Email address:");
        inputEmail = input.nextLine();
        checkValid(inputName, inputEmail);
        Account.userName = inputName;
        Account.userEmail = inputEmail;
        new Home();
    }

    // REQUIRES: A non-empty string
    // MODIFIES: name & email
    // EFFECTS: Re-obtains user input if input string is empty
    private static void checkValid(String name, String email) {
        while (name.equals("") || email.equals("")) {
            System.out.println("Please enter a valid name and/or email address");
            name = input.nextLine();
            email = input.nextLine();
        }
        inputName = name;
        inputEmail = email;
    }
}
