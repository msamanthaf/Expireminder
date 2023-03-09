package persistence;

import model.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    @Test
    void testInvalidDestination() {
        Account account = new Account("name", "email");
        JsonWriter writer = new JsonWriter("./data\1llegal:fileName.json", account);
        assertFalse(writer.getWritten());
        assertNull(JsonWriter.write("./data\1llegal:fileName.json"));
    }

    @Test
    void testValidDestination() {
        Account account = new Account("name", "email");
        JsonWriter writer = new JsonWriter("./data/accountData.json", account);
        assertTrue(writer.getWritten());
    }

    @Test
    void testSavingAccount() {
        Account originalAccount = new Account("name", "email");
        new JsonWriter("./data/accountData.json", originalAccount);
        JsonReader accountReader = new JsonReader("./data/accountData.json");
        Account readAccount = accountReader.readAccount();

        assertEquals(originalAccount.getName(), readAccount.getName());
        assertEquals(originalAccount.getEmail(), readAccount.getEmail());
    }
}