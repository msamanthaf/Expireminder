package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class Notification {
    private LocalDate currentTime;
    private LocalDate expiryTime;
    private static Integer MONTHS_BEFORE = 1;
    private Boolean notified = false;
    private Boolean expired = false;
    private Integer monthsLeft;
    private Boolean exception = false;

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

    public Integer calculateMonth(LocalDate currentDate, LocalDate expiryDate) {
        Period period = Period.between(currentDate, expiryDate);
        monthsLeft = period.getMonths() + (12 * period.getYears()) + (period.getDays() / 31);
        return monthsLeft;
    }

    public void sendNotification(LocalDate currentDate, LocalDate expiryDate) {
        notified = calculateMonth(currentDate, expiryDate) <= MONTHS_BEFORE && !currentDate.isAfter(expiryDate);
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

    public boolean getException() {
        return exception;
    }

    public int getDifference() {
        return monthsLeft;
    }
}
