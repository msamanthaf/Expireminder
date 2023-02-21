package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriesTest {
    Categories category1;
    Categories category2;

    @BeforeEach
    void runBefore() {
        category1 = new Categories();
        category2 = new Categories();
    }

    @Test
    void testAdd() {
        category1.add("Category 1");
        assertEquals(1, category1.getCategoryName().size());
        assertEquals(1, category1.getCategoryIndex().size());
        assertEquals("Category 1", category1.getCategoryName().get(0));
        assertEquals("1", category1.getCategoryIndex().get(0));
    }

    @Test
    void testRename() {
        category1.add("Category 1");
        category1.rename(1, "Category");
        assertEquals(1, category1.getCategoryName().size());
        assertEquals(1, category1.getCategoryIndex().size());
        assertEquals("Category", category1.getCategoryName().get(0));
        assertEquals("1", category1.getCategoryIndex().get(0));
    }

    @Test
    void testDelete() {
        category1.add("Category 1");
        category1.delete(0);
        assertEquals(0, category1.getCategoryName().size());
        assertEquals(0, category1.getCategoryIndex().size());

        category2.add("Category 1");
        category2.add("Category 2");
        category2.delete(1);
        assertEquals(1, category2.getCategoryName().size());
        assertEquals(1, category2.getCategoryIndex().size());
        assertEquals("Category 1", category2.getCategoryName().get(0));
        assertEquals("1", category2.getCategoryIndex().get(0));
    }

    @Test
    void testAddItem() {
        String name = "Name";
        Integer quantity = 1;
        String date = "02/04/2023";
        Items item = new Items(name, quantity, date);
        ArrayList<Items> listOfItems = new ArrayList<>();
        ArrayList<Items> empty = new ArrayList<>();
        listOfItems.add(item);
        category1.add("Category 1");
        category1.add("Category 2");
        category1.addItem(item, 1);
        assertEquals(2, category1.getCategoryItems().size());
        assertEquals(listOfItems, category1.getCategoryItems().get(0));
        assertEquals(empty, category1.getCategoryItems().get(1));
    }

    @Test
    void testAddStatus() {
        String name = "Name";
        Integer quantity = 1;
        String date2 = "02/04/2002";
        String date1 = "03/30/2023";
        String date3 = "02/04/2024";
        Items item1 = new Items(name, quantity, date1);
        Items item2 = new Items(name, quantity, date2);
        Items item3 = new Items(name, quantity, date3);
        category1.addStatus(item1);
        category1.addStatus(item2);
        category1.addStatus(item3);
        assertEquals(1, category1.getExpired().size());
        assertEquals(1, category1.getExpiringSoon().size());
        assertEquals(1, category1.getGoodCondition().size());
    }
}
