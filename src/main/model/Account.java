package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an account having a name and email
public class Account implements Writable {
    private String name;
    private String email;

    // EFFECTS: sets name to userName and email to userEmail
    public Account(String userName, String userEmail) {
        name = userName;
        email = userEmail;
        EventLog.getInstance().logEvent(new Event("Account created."));
    }

    // MODIFIES: this
    // EFFECTS: sets name to new userName and email to new userEmail
    public void setAccount(String userName, String userEmail) {
        this.name = userName;
        this.email = userEmail;
        EventLog.getInstance().logEvent(new Event("Account modified."));
    }

    // EFFECTS: returns this as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        return json;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}