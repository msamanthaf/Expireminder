package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationTest {
    Notification notification1;
    Notification notification2;
    Notification notification3;
    Notification notification4;

    @BeforeEach
    void RunBefore() {
        notification1 = new Notification("12/12/2023");
        notification2 = new Notification("2/12/2024");
        notification3 = new Notification("12/1/2022");
        notification4 = new Notification("3/31/2023");
    }

    @Test
    void testCalculateMonth() {
        assertEquals(10, notification1.getDifference());
        assertEquals(12, notification2.getDifference());
        assertEquals(-2, notification3.getDifference());
        assertEquals(1, notification4.getDifference());

    }

    @Test
    void testSendNotification(){
        assertFalse(notification1.getNotified());
        assertFalse(notification2.getNotified());
        assertFalse(notification3.getNotified());
        assertTrue(notification4.getNotified());
    }

    @Test
    void testExpired(){
        assertFalse(notification1.getExpired());
        assertFalse(notification2.getExpired());
        assertTrue(notification3.getExpired());
        assertFalse(notification4.getExpired());
    }
}
