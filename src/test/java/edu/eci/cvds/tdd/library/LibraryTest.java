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



    // 1.2. SetUp and TearDown Methods

    /**
     * Sets up a new Library instance before each test.
     */
    @BeforeEach
    public void setUp() {
        library = new Library();
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
     * Test that loanABook should create a loan when the user and book exist and the book is available.
     */
    @Test
    public void shouldLoanBookSuccessfully() {
        // Add a user to the library.
        User user = new User("John Doe", "user1");
        library.addUser(user);

        // Add a book to the library.
        Book book = new Book("Clean Code", "Robert C. Martin", "978-0132350884");
        library.addBook(book);

        // Attempt to loan the book.
        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull(loan, "The loan should be created successfully.");
        assertEquals(LoanStatus.ACTIVE, loan.getStatus(), "The loan status should be ACTIVE.");
        assertEquals(user, loan.getUser(), "The loan should be assigned to the correct user.");
        assertEquals(book, loan.getBook(), "The loan should be for the correct book.");
    }

    /**
     * Test that loanABook should not create a loan if the user does not exist.
     */
    @Test
    public void shouldNotLoanBookForNonexistentUser() {
        // Add a book to the library.
        Book book = new Book("Domain-Driven Design", "Eric Evans", "978-0321125217");
        library.addBook(book);

        // Attempt to loan the book for a user that does not exist.
        Loan loan = library.loanABook("nonexistentUser", book.getIsbn());
        assertNull(loan, "A loan should not be created if the user does not exist.");
    }

    /**
     * Test that loanABook should not create a loan if the book does not exist.
     */
    @Test
    public void shouldNotLoanNonexistentBook() {
        // Add a user to the library.
        User user = new User("Jane Doe", "user2");
        library.addUser(user);

        // Attempt to loan a book that was not added.
        Loan loan = library.loanABook(user.getId(), "nonexistentISBN");
        assertNull(loan, "A loan should not be created if the book does not exist.");
    }

    /**
     * Test that loanABook should not create a loan if there are no available copies of the book
     * or if the user already has an active loan for the same book.
     */
    @Test
    public void shouldNotLoanBookWhenNotAvailableOrAlreadyLoaned() {
        // Add a user to the library.
        User user = new User("Alice", "user3");
        library.addUser(user);

        // Add a book to the library.
        Book book = new Book("The Pragmatic Programmer", "Andrew Hunt", "978-0201616224");
        library.addBook(book); // 1 copy available

        // First loan should succeed.
        Loan firstLoan = library.loanABook(user.getId(), book.getIsbn());
        assertNotNull(firstLoan, "The first loan should be created.");

        // Attempt a second loan for the same user and book should fail (user already has an active loan).
        Loan secondLoan = library.loanABook(user.getId(), book.getIsbn());
        assertNull(secondLoan, "A second loan should not be created if the user already has an active loan for the same book.");
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