package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemsTest {
    Items item1;

    @BeforeEach
    void RunBefore(){
        item1 =  new Items("Name", 1, "12/12/2022");
    }

    @Test
    void testSetName(){
        item1.setName("New Name");
        assertEquals("New Name", item1.getName());
    }

    @Test
    void testSetQuantity(){
        item1.setQuantity(10);
        assertEquals(10, item1.getQuantity());
    }

    @Test
    void testSetDate(){
        item1.setDate("01/01/2021");
        assertEquals("01/01/2021", item1.getDate());
    }
}
