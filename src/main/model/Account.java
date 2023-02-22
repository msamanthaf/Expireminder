package model;

// Represents an account having a name and email
public class Account {
    private String name;
    private String email;

    // EFFECTS: sets name to userName and email to userEmail
    public Account(String userName, String userEmail) {
        name = userName;
        email = userEmail;
    }

    // MODIFIES: this
    // EFFECTS: sets name to new userName and email to new userEmail
    public void setAccount(String userName, String userEmail) {
        this.name = userName;
        this.email = userEmail;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
