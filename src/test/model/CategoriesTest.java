package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
