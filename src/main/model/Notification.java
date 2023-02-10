package model;

import jdk.dynalink.beans.StaticClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class Notification {
    private LocalDate currentTime;
    private LocalDate expiryTime;
    private Integer monthsBefore = 1;
    private Boolean notified = false;
    private Boolean expired = false;
    private Integer monthsLeft;

    public Notification(String date) {
        DateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            expiryTime = inputFormat.parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception e) {
            //Input already guarded in UI
        }
        currentTime = LocalDate.now();
        sendNotification(currentTime, expiryTime);
        expired(currentTime, expiryTime);
    }

    public Integer calculateMonth(LocalDate currentDate, LocalDate expiryDate) {
        Period period = Period.between(currentDate, expiryDate);
        int month = period.getMonths() + (12 * period.getYears()) + (period.getDays() / 31);
        monthsLeft = month;
        return monthsLeft;
    }

    public Integer getDifference() {
        return monthsLeft;
    }

    public void sendNotification(LocalDate currentDate, LocalDate expiryDate) {
        if (calculateMonth(currentDate, expiryDate) <= monthsBefore && !currentDate.isAfter(expiryDate)) {
            notified = true;
        } else {
            notified = false;
        }
    }

    public void expired(LocalDate currentDate, LocalDate expiryDate) {
        if (currentDate.isAfter(expiryDate)) {
            expired = true;
        } else {
            expired = false;
        }
    }

    public boolean getNotified() {
        return notified;
    }

    public boolean getExpired() {
        return expired;
    }
}
