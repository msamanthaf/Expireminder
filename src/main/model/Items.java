package model;

public class Items {
    private String name;
    private int quantity;
    private String date;
    private Notification notification;

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

