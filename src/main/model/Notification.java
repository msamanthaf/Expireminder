package model;

import org.json.JSONObject;
import persistence.Writable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

// Represents a notification for each item with an input date and local date of current time
public class Notification implements Writable {
    private LocalDate currentTime;
    private LocalDate expiryTime;
    private static Integer MONTHS_BEFORE = 1;
    private Boolean notified = false;
    private Boolean expired = false;
    private Integer monthsLeft;
    private Boolean exception = false;

    // REQUIRES: valid date (guarded in UI)
    // EFFECTS: sets currentTime into current local date, notified is set to the default of false
    public Notification(String date) {
        DateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            expiryTime = inputFormat.parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            exception = true;
            expiryTime = LocalDate.now(); //To prevent null pointer errors
            //Input already guarded in UI
        }
        currentTime = LocalDate.now();
        sendNotification(currentTime, expiryTime);
        expired(currentTime, expiryTime);
    }

    // MODIFIES: monthsLeft
    // EFFECTS: returns how many months left from now to a given date
    public Integer calculateMonth(LocalDate currentDate, LocalDate expiryDate) {
        Period period = Period.between(currentDate, expiryDate);
        monthsLeft = period.getMonths() + (12 * period.getYears()) + (period.getDays() / 31);
        return monthsLeft;
    }

    // MODIFIES: notified
    // EFFECTS: notified is set to true if monthsLeft is less than or equals to MONTHS_BEFORE
    public void sendNotification(LocalDate currentDate, LocalDate expiryDate) {
        notified = calculateMonth(currentDate, expiryDate) <= MONTHS_BEFORE && !currentDate.isAfter(expiryDate);
    }

    // MODIFIES = expired
    // EFFECTS = returns true if expiryDate is in the past
    public void expired(LocalDate currentDate, LocalDate expiryDate) {
        expired = currentDate.isAfter(expiryDate);
    }

    // EFFECTS: returns this as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("notified", notified);
        return json;
    }

    public boolean getNotified() {
        return notified;
    }

    public void setNotified(Boolean notified) {
        this.notified = notified;
    }

    public boolean getExpired() {
        return expired;
    }

    public boolean getException() {
        return exception;
    }

    public int getDifference() {
        return monthsLeft;
    }
}
