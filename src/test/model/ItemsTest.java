package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemsTest {
    Items item1;
    Items item2;
    Items item3;

    @BeforeEach
    void RunBefore() {
        item1 = new Items("Name", 1, "12/12/2022");
        item2 = new Items("Name", 1, "05/15/2023");
        item3 = new Items("Name", 1, "03/15/2024");
    }

    @Test
    void testSetName() {
        item1.setName("New Name");
        assertEquals("New Name", item1.getName());
    }

    @Test
    void testSetQuantity() {
        item1.setQuantity(10);
        assertEquals(10, item1.getQuantity());
    }

    @Test
    void testSetDate() {
        item1.setDate("01/01/2021");
        assertEquals("01/01/2021", item1.getDate());
    }

    @Test
    void testModifyItem(){
        assertEquals("Name", item1.getName());
        assertEquals(1, item1.getQuantity());
        assertEquals("12/12/2022", item1.getDate());
        item1.modifyItem("New Name", 2, "01/01/2023");
        assertEquals("New Name", item1.getName());
        assertEquals(2, item1.getQuantity());
        assertEquals("01/01/2023", item1.getDate());
    }

    @Test
    void testDeleteItem() {
        Categories category1 = new Categories();
        category1.add("category 1");
        category1.addItem(item1, 1);
        assertEquals(1, category1.getCategoryItems().get(0).size());
        category1.deleteItem(0, 0);
        assertEquals(0, category1.getCategoryItems().get(0).size());
    }

    @Test
    void testGetNotification() {
        assertFalse(item1.getNotification().getNotified());
        assertTrue(item2.getNotification().getNotified());
        assertFalse(item3.getNotification().getNotified());
    }
}