package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an item inside a category with a name, quantity, date, and notification
public class Items implements Writable {
    private String name;
    private int quantity;
    private String date;
    private Notification notification;

    // REQUIRES: valid name, quantity, and date (guarded in ui)
    // EFFECTS: stores name, quantity, and date, creates new notification
    public Items(String name, Integer quantity, String date) {
        this.name = name;
        this.quantity = quantity;
        this.date = date;
        notification = new Notification(date);
    }

    // EFFECTS: returns this as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("quantity", quantity);
        json.put("date", date);
        json.put("notification", notification.toJson());
        return json;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Boolean notified) {
        notification.setNotified(notified);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}