package persistence;

import model.Account;
import model.Categories;
import model.Items;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    @Test
    void testInvalidFile() {
        JsonReader reader = new JsonReader("./data/nonexistent.json");
        assertNull(reader.readAccount());
        assertNull(reader.readCategories());
    }

    @Test
    void testValidFile() {
        Account originalAccount = new Account("name", "email");
        new JsonWriter("./data/accountData.json", originalAccount);
        JsonReader reader = new JsonReader("./data/accountData.json");
        assertEquals("name", reader.readAccount().getName());
        assertEquals("email", reader.readAccount().getEmail());
    }

    @Test
    void testLoadingCategories() {
        Categories originalCategories = new Categories();
        originalCategories.add("Category 1");
        Items item1 = new Items("Item 1", 1, "12/12/2022");
        Items item2 = new Items("Item 2", 2, "01/01/2024");
        originalCategories.addItem(item1, 1);
        originalCategories.addItem(item2, 1);
        originalCategories.add("Category 2");

        new JsonWriter("./data/categoriesData.json", originalCategories);
        JsonReader reader = new JsonReader("./data/categoriesData.json");

        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("Category 1");
        nameList.add("Category 2");
        assertEquals(nameList,reader.readCategories().getCategoryName());

        ArrayList<String> indexList = new ArrayList<>();
        indexList.add("1");
        indexList.add("2");
        assertEquals(indexList,reader.readCategories().getCategoryIndex());

        ArrayList<Items> categoryItems1 = new ArrayList<>();
        categoryItems1.add(item1);
        categoryItems1.add(item2);
        ArrayList<Items> categoryItems2 = new ArrayList<>();
        assertEquals("Item 1",reader.readCategories().getCategoryItems().get(0).get(0).getName());
        assertEquals(2,reader.readCategories().getCategoryItems().get(0).get(1).getQuantity());
        assertEquals(categoryItems2,reader.readCategories().getCategoryItems().get(1));
    }
}