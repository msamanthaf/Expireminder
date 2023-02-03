package model;

public class Account {
    private String name;
    private String email;

    public Account(String userName, String userEmail) {
        name = userName;
        email = userEmail;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setAccount(String userName, String userEmail) {
        this.name = userName;
        this.email = userEmail;
    }
}
