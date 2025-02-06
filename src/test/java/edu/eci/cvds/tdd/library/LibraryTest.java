package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;

class libraryTest {

    @Test
    void shouldCreateBook() {
        Library library = new Library();
        Book book = new Book("libro1","jesus","1");
        library.addBook(book);
        @SuppressWarnings("rawtypes")
        Map books = library.getBooks();
        assertTrue(books.containsKey(book));
        assertTrue(books.get(book).equals(1));
    }

    @Test
    void shouldAddBook() {
        Library library = new Library();
        Book book = new Book("libro1","jesus","1");
        library.addBook(book);
        library.addBook(book);
        @SuppressWarnings("rawtypes")
        Map books = library.getBooks();
        assertTrue(books.containsKey(book));
        assertTrue(books.get(book).equals(2));
    }

    private Library library;
    private User user;
    private Book book;

    /**
     * This method is executed before each test to set up the necessary objects.
     */
    @BeforeEach
    public void setUp() {
        library = new Library();
        user = mock(User.class);
        book = mock(Book.class);
    }

    /**
     * Tests the addUser method to ensure it adds a user correctly to the library.
     */
    @Test
    public void testAddUser() {
        assertTrue(library.addUser(user)); // The addUser method should return true.
    }

    /**
     * Tests the addBook method to ensure a new book is added correctly.
     */
    @Test
    public void testAddBook() {
        // Test for adding a new book
        when(book.getIsbn()).thenReturn("12345");
        assertTrue(library.addBook(book));

        // Verify that the book was added correctly
        assertTrue(library.getBooks().containsKey(book));
    }

    /**
     * Tests the addBook method when the book already exists in the library.
     * The quantity of the book should increase by 1.
     */
    @Test
    public void testAddExistingBook() {
        when(book.getIsbn()).thenReturn("12345");
        library.addBook(book);

        // Simulate that the book already exists
        when(book.getIsbn()).thenReturn("12345");
        assertTrue(library.addBook(book)); // The quantity of the book should increase
    }

    /**
     * Tests the loanABook method to ensure a book can be loaned to a user.
     */
    @Test
    public void testLoanABook() {
        // Simulate that the user and book exist
        when(user.getId()).thenReturn("user123");
        when(book.getIsbn()).thenReturn("12345");

        // Add a book and a user
        library.addBook(book);
        library.addUser(user);

        // Test when the book is available and no previous loan exists
        Loan loan = library.loanABook("user123", "12345");

        assertNotNull(loan);
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
    }

    /**
     * Tests the loanABook method when the book is not available.
     */
    @Test
    public void testLoanBookNotAvailable() {
        // Simulate that the book is not available
        when(book.getIsbn()).thenReturn("12345");
        when(user.getId()).thenReturn("user123");

        // No books available
        library.addUser(user);

        Loan loan = library.loanABook("user123", "12345");

        assertNull(loan); // If no books are available, the loan should be null.
    }

    /**
     * Tests that a user cannot loan the same book twice at the same time.
     */
    @Test
    public void testLoanSameBookTwice() {
        // Simulate a loan
        when(user.getId()).thenReturn("user123");
        when(book.getIsbn()).thenReturn("12345");
        library.addUser(user);
        library.addBook(book);

        // First loan
        library.loanABook("user123", "12345");

        // Second loan should fail
        Loan loan = library.loanABook("user123", "12345");

        assertNull(loan); // The loan should be null because the book is already loaned out.
    }

    /**
     * Tests the returnLoan method to ensure the loan is returned correctly.
     * The book quantity should increase, and the loan status should be updated to "RETURNED".
     */
    @Test
    public void testReturnLoan() {
        // Create a loan
        when(user.getId()).thenReturn("user123");
        when(book.getIsbn()).thenReturn("12345");

        library.addUser(user);
        library.addBook(book);

        Loan loan = library.loanABook("user123", "12345");

        assertNotNull(loan);
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());

        // Return the loan
        loan = library.returnLoan(loan);

        assertNotNull(loan);
        assertEquals(LoanStatus.RETURNED, loan.getStatus()); // The status should change to RETURNED
    }

    //------------------- returnLoan ------------------- 
    @Test
    /*
    * Tests the book return functionality. 
    * Adds a book and a user, performs a loan, 
    * and verifies that the loan status is "RETURNED" 
    * and the return date is not null.
    */
    void ShouldReturnLoanedBook(){
        library.addBook(coquito);
        library.addUser(mayerlly);
        Loan loan = library.loadABook(mayerlly.getID(), coquito.getIsbn());
        Load returnedLoad = library.returnLoan(loan); 
        assertEquals(LoanStatus.RETURNED, returnedLoan.getStatus());
        assertNotNull(returnedLoan.getReturnDate());
    }

    @Test
    /*
    * Tests that a nonexistent loan cannot be returned.
    * Creates a loan with an active status and attempts to return it.
    * Verifies that the returned loan is null.
    */
    public void shouldNotReturnNonexistentLoan() {
        Loan loan = new Loan();
        loan.setBook(Clean_Code);
        loan.setUser(mayerllyS);
        loan.setLoanDate(LocalDateTime.now());
        loan.setStatus(LoanStatus.ACTIVE);
        Loan returnedLoan = library.returnLoan(loan);
        assertNull(returnedLoan);
    }
}