package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationTest {
    Notification notification1;
    Notification notification2;
    Notification notification3;
    Notification notification4;
    Notification notification5;

    @BeforeEach
    void RunBefore() {
        notification1 = new Notification("12/12/2023");
        notification2 = new Notification("2/12/2024");
        notification3 = new Notification("12/1/2022");
        notification4 = new Notification("3/31/2023");
        notification5 = new Notification("12345");
    }

    @Test
    void testNotificationException() {
        assertFalse(notification1.getException());
        assertFalse(notification2.getException());
        assertFalse(notification3.getException());
        assertFalse(notification4.getException());
        assertTrue(notification5.getException());
    }

    @Test
    void testCalculateMonth() {
        LocalDate currentDate = LocalDate.of(2023, 3, 3);
        LocalDate date1 = LocalDate.of(2023, 12, 12);
        notification1.calculateMonth(currentDate, date1);
        LocalDate date2 = LocalDate.of(2024, 2, 12);
        notification2.calculateMonth(currentDate, date2);
        LocalDate date3 = LocalDate.of(2022, 12, 1);
        notification3.calculateMonth(currentDate, date3);
        LocalDate date4 = LocalDate.of(2023, 3, 31);
        notification4.calculateMonth(currentDate, date4);

        assertEquals(9, notification1.getDifference());
        assertEquals(11, notification2.getDifference());
        assertEquals(-3, notification3.getDifference());
        assertEquals(0, notification4.getDifference());
    }

    @Test
    void testSendNotification() {
        assertFalse(notification1.getNotified());
        assertFalse(notification2.getNotified());
        assertFalse(notification3.getNotified());
        assertTrue(notification4.getNotified());
    }

    @Test
    void testExpired() {
        assertFalse(notification1.getExpired());
        assertFalse(notification2.getExpired());
        assertTrue(notification3.getExpired());
        assertFalse(notification4.getExpired());
    }
}