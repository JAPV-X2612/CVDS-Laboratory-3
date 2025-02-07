package edu.eci.cvds.tdd.library.book;
/**
 * Represents a book with a title, author, and ISBN.
 */
public class Book {
    private final String tittle;
    private final String author;
    private final String isbn;

    /**
     * Constructs a Book instance with the specified title, author, and ISBN.
     * 
     * @param tittle The title of the book.
     * @param author The author of the book.
     * @param isbn The ISBN of the book.
     */
    public Book(String tittle, String author, String isbn) {
        this.tittle = tittle;
        this.author = author;
        this.isbn = isbn;
    }

    /**
     * Returns the title of the book.
     * 
     * @return The title of the book.
     */
    public String getTittle() {
        return tittle;
    }

    /**
     * Returns the author of the book.
     * 
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }
    /**
     * Returns the ISBN of the book.
     * 
     * @return The ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Compares this book to another object. Two books are considered equal
     * if they have the same ISBN.
     * 
     * @param obj The object to compare this book with.
     * @return true if the books are the same (i.e., have the same ISBN), false otherwise.
     * @throws ClassCastException If the object is not an instance of Book.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // If both objects are the same
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // If the object is null or not of the same class
        }
        Book otherBook = (Book) obj;
        return isbn != null && isbn.equals(otherBook.isbn); // Safe comparison of ISBN
    }
}