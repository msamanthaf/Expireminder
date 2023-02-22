package model;

// Represents an item inside a category with a name, quantity, date, and notification
public class Items {
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

    public Notification getNotification() {
        return notification;
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

