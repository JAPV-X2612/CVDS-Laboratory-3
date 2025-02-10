package edu.eci.cvds.tdd.library.user;

/**
 * Represents a library user.
 * 
 * @author: Mayerlly Suárez, Jesús Pinzón & José Castillo
 * @version 1.1 (2024/02/04)
 */
public class User {
    private String name;
    private String id;

    /**
     * Creates a new user with the given name and ID.
     * 
     * @param name The user's name.
     * @param id   The user's unique identifier.
     */
    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    /**
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The user's unique identifier.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the user's unique identifier.
     * @param id The new identifier.
     */
    public void setId(String id) {
        this.id = id;
    }
}