package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account account1;
    Account account2;

    @BeforeEach
    void runBefore() {
        account1 = new Account("Sam", "mariesamantha.f@gmail.com");
        account2 = new Account("123", "123");
    }

    @Test
    void testSetAccount() {
        account1.setAccount("Samantha", "mfidelia@student.ubc.id");
        assertEquals("Samantha", account1.getName());
        assertEquals("mfidelia@student.ubc.id", account1.getEmail());

        account2.setAccount("123", "123");
        assertEquals("123", account2.getName());
        assertEquals("123", account2.getEmail());
    }
}