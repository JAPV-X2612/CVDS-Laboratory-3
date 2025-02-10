package edu.eci.cvds.tdd.library.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the User class.
 *
 * @author: Mayerlly Suárez, Jesús Pinzón & José Castillo
 * @version 1.1 (2024/02/05)
 */
public class UserTest {

    /**
     * Verifies that a user can be created with the correct name and ID.
     */
    @Test
    public void shouldReturnCorrectUserDetails() {
        User user = new User("Juan Pérez", "U123");
        
        assertEquals("Juan Pérez", user.getName());
        assertEquals("U123", user.getId());
    }

    /**
     * Verifies that the name of the user can be updated.
     */
    @Test
    public void shouldUpdateUserName() {
        User user = new User("Juan Pérez", "U123");
        user.setName("Carlos Gómez");
        
        assertEquals("Carlos Gómez", user.getName());
    }

    /**
     * Verifies that the ID of the user can be updated.
     */
    @Test
    public void shouldUpdateUserId() {
        User user = new User("Juan Pérez", "U123");
        user.setId("U456");
        
        assertEquals("U456", user.getId());
    }
}