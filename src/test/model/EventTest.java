package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Added category");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        Event e2 = new Event("Added item");
        Event e3 = null;
        Event e4 = new Event("Added category");
        assertEquals("Added category", e.getDescription());
        assertFalse(e.equals(e2));
        assertFalse(e.equals(e3));
        assertFalse(e.equals(e4));
        assertTrue(e.equals(e));
        assertFalse(e.equals(new Items("item",1,"01/01/2021")));
        assertEqualDates(d, e.getDate());
    }

    private static final String DATE_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";

    private static void assertEqualDates(Date expected, Date value) {
        DateFormat formatter = new SimpleDateFormat(DATE_PATTERN);
        String strExpected = formatter.format(expected);
        String strValue = formatter.format(value);
        assertEquals(strExpected, strValue);
    }

    @Test
    public void testToString() {
        int HASH_CONSTANT = 13;
        Date dateLogged = e.getDate();
        String description = e.getDescription();
        assertEquals(d.toString() + "\n" + "Added category", e.toString());
        assertEquals((HASH_CONSTANT * dateLogged.hashCode() + description.hashCode()), e.hashCode());
    }
}