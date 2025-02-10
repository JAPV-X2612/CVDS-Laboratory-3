package edu.eci.cvds.tdd.library.book;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Book class that validates various methods such as equality comparison,
 * title retrieval, author handling and ISBN validation.
 * 
 * @author: Mayerlly Suárez, Jesús Pinzón & José Castillo
 * @version 1.1 (2024/02/05)
 */
public class BookTest {

    /*
     * Verifies that two books with the same ISBN are considered equal.
     */
    @Test
    public void shouldReturnTrueForBooksWithSameIsbn() {
        Book book1 = new Book("El Quijote", "Miguel de Cervantes", "123456");
        Book book2 = new Book("Don Quijote de la Mancha", "Miguel de Cervantes", "123456");
        assertTrue(book1.equals(book2));
    }

    /*
     * Verifies that two books with different ISBNs are not considered equal.
     */
    @Test
    public void shouldReturnFalseForBooksWithDifferentIsbn() {
        Book book1 = new Book("Cálculo Integral", "Juan Pérez", "111222");
        Book book2 = new Book("Cálculo Diferencial", "Carlos Gómez", "333444");
        assertFalse(book1.equals(book2));
    }

    /*
     * Verifies that comparing a book to null returns false.
     */
    @Test
    public void shouldReturnFalseWhenComparedWithNull() {
        Book book = new Book("Teoría de Juegos", "David Jaramillo", "555555");
        assertFalse(book.equals(null));
    }

    /*
     * Verifies that two books with the same ISBN are considered equal
     * even if their titles or authors differ.
     */
    @Test
    public void shouldReturnTrueWhenBooksHaveIdenticalIsbn() {
        Book book1 = new Book("El Código de la Mente", "Suárez", "9876");
        Book book2 = new Book("El Código de la Mente", "Suárez", "9876");
        assertEquals(book1, book2);
    }

    /*
     * Verifies that the book's title is correctly retrieved.
     */
    @Test
    public void shouldReturnBookTitleCorrectly() {
        Book book = new Book("Tecnologías Emergentes", "Sebastián", "456789");
        assertEquals("Tecnologías Emergentes", book.getTittle());
    }

    /*
     * Verifies that the book's ISBN is correctly retrieved.
     */
    @Test
    public void shouldReturnCorrectIsbn() {
        Book book = new Book("Inteligencia Artificial", "Mayerlly Suárez", "123654");
        assertEquals("123654", book.getIsbn());
    }

    /*
     * Verifies that the book title does not match a different title.
     */
    @Test
    public void shouldReturnFalseWhenBookTitleIsDifferent() {
        Book book = new Book("Diseño de Software", "Sebastián", "7890");
        assertNotEquals("Poesía Contemporánea", book.getTittle());
    }

    /*
     * Verifies that the book's author is correctly retrieved when not empty.
     */
    @Test
    public void shouldReturnAuthorWhenAuthorIsNotEmpty() {
        Book book = new Book("Estructuras de Datos", "Carlos Pérez", "54321");
        assertEquals("Carlos Pérez", book.getAuthor());
    }

    /*
     * Verifies that the book returns an empty string when the author is empty.
     */
    @Test
    public void shouldReturnEmptyStringWhenAuthorIsEmpty() {
        Book book = new Book("Algoritmos", "", "67890");
        assertEquals("", book.getAuthor());
    }

    /*
     * Verifies that the book returns null when the author is null.
     */
    @Test
    public void shouldReturnNullWhenAuthorIsNull() {
        Book book = new Book("Redes de Computadoras", null, "987654");
        assertNull(book.getAuthor());
    }

    /*
     * Verifies that a Book object is not considered equal to a different object type.
     */
    @Test
    public void shouldReturnFalseWhenComparedWithDifferentObjectType() {
        Book book = new Book("Biología Molecular", "Ana López", "888999");
        String notABook = "This is not a book";
        assertFalse(book.equals(notABook));
    }

    /*
     * Verifies that comparing a book with null ISBN returns false.
     */
    @Test
    public void shouldReturnFalseWhenIsbnIsNull() {
        Book book1 = new Book("Redes Neuronales", "Luis Gómez", null);
        Book book2 = new Book("Deep Learning", "Michael Jordan", "112233");
        assertFalse(book1.equals(book2));
    }

    /*
     * Verifies that comparing the same instance of a book returns true.
     */
    @Test
    public void shouldReturnTrueWhenComparingTheSameBookInstance() {
        Book book = new Book("Física Cuántica", "Isabel Ruiz", "123123");
        assertTrue(book.equals(book));
    }
}
