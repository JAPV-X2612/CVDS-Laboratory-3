package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

/**
 * Unit tests for the Library class.
 * 
 * @author: Mayerlly Suárez, Jesús Pinzón & José Castillo
 * @version 1.5 (2024/02/09)
 */
class LibraryTest {

    // 1. Class Attributes and Constructors Tests
    
    // 1.1. Test Class Attributes

    private Library library;
    private Book book;
    private User user;



    // 1.2. SetUp and TearDown Methods

    /**
     * Sets up a new Library instance before each test.
     */
    @BeforeEach
    void setUp() {
        library = new Library();
        book = new Book("Clean Code", "Robert C. Martin", "978-0132350884");
        user = new User("John Doe", "USR001");
        library.addUser(user);
    }





    // 2. Class Methods Tests

    // 2.1. Tests for addBook Method by Jose

    /**
     * Test that addBook should successfully add a valid book to the library.
     */
    @Test
    public void shouldAddNewBook() {
        Book book = new Book("Effective Java", "Joshua Bloch", "978-0134685991");
        boolean result = library.addBook(book);
        assertTrue(result, "The book should be added successfully.");
    }

    /**
     * Test that addBook should not add a null book.
     */
    @Test
    public void shouldNotAddNullBook() {
        boolean result = library.addBook(null);
        assertFalse(result, "A null book should not be added.");
    }
    


    // 2.2. Tests for loanABook Method by Jesus

    /**
     * Test that a user can loan a book after returning a previous loan of the same book.
     * This verifies that only ACTIVE loans prevent new loans, not RETURNED ones.
     */
    @Test
    void shouldAllowLoanAfterReturning() {
        // Add two copies of the book
        library.addBook(book);
        library.addBook(book);
        
        // Create first loan
        Loan firstLoan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull(firstLoan, "First loan should be successful");
        
        // Return the book
        library.returnLoan(firstLoan);
        
        // Try to loan the same book again
        Loan secondLoan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull(secondLoan, "Should allow new loan after returning previous one");
        assertEquals(LoanStatus.ACTIVE, secondLoan.getStatus(), "New loan should be active");
    }

    /**
     * Test that the system correctly identifies and prevents duplicate active loans
     * even when multiple copies of the book are available.
     */
    @Test
    void shouldNotAllowDuplicateActiveLoans() {
        // Add multiple copies of the same book
        library.addBook(book);
        library.addBook(book);
        library.addBook(book);
        
        // Create first loan
        Loan firstLoan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull(firstLoan, "First loan should be successful");
        
        // Attempt to create second loan while first is still active
        Loan secondLoan = library.loanABook(user.getId(), book.getIsbn());
        assertNull(secondLoan, "Should not allow second loan while first is active");
    }

    /**
     * Test that the system correctly handles multiple users trying to loan the same book,
     * ensuring that the active loan check is user-specific.
     */
    @Test
    void shouldAllowDifferentUsersToLoanSameBook() {
        // Add multiple copies of the same book
        library.addBook(book);
        library.addBook(book);
        
        // Create second user
        User secondUser = new User("Jane Doe", "USR002");
        library.addUser(secondUser);
        
        // First user loans the book
        Loan firstUserLoan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull(firstUserLoan, "First user's loan should be successful");
        
        // Second user tries to loan the same book
        Loan secondUserLoan = library.loanABook(secondUser.getId(), book.getIsbn());
        assertNotNull(secondUserLoan, "Second user should be able to loan the same book");
        assertEquals(secondUser, secondUserLoan.getUser(), "Loan should be assigned to correct user");
    }




    
    // 2.3. Tests for returnLoan Method by Mayerlly

    @Test
    /*
    * Tests the book return functionality. 
    * Adds a book and a user, performs a loan, 
    * and verifies that the loan status is "RETURNED" 
    * and the return date is not null.
    */
    void ShouldReturnLoanedBook(){
        Book coquito = new Book("Coquito", "Author", "123");
        User mayerlly = new User("mayerlly", "3425");
        mayerlly.setName("mayerlly");
        library.addBook(coquito);
        library.addUser(mayerlly);
        Loan loan = library.loanABook(mayerlly.getId(), coquito.getIsbn());
        Loan returnedLoan = library.returnLoan(loan);
    
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
        Book cleanCode = new Book("Clean Code", "Robert C. Martin", "9780132350884");
        User mayerllyS = new User("mayerllyS", "1018");
        Loan loan = new Loan();
        loan.setBook(cleanCode);
        loan.setUser(mayerllyS);
        loan.setLoanDate(LocalDateTime.now());
        loan.setStatus(LoanStatus.ACTIVE);
   
        Loan returnedLoan = library.returnLoan(loan);
        assertNull(returnedLoan);
    }
}