package edu.eci.cvds.tdd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class libraryTest {
    @Test
    void shouldCreateBook() {
        Library library = new Library()
        Book book = new Book("libro1","jesus","1");
        library.addBook(book);
        Map books = library.getBooks();
        assertTrue(books.containsKey(book));
        assertTrue(books.get(book) == 1);
    }

    @Test
    void shouldAddBook() {
        Library library = new Library()
        Book book = new Book("libro1","jesus","1");
        library.addBook(book);
        library.addBook(book);
        Map books = library.getBooks();
        assertTrue(books.containsKey(book));
        assertTrue(books.get(book) == 2);
    }
}