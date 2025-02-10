package edu.eci.cvds.tdd.library.loan;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

/**
 * Unit tests for the Loan class
 *
 * @author: Mayerlly Suárez, Jesús Pinzón & José Castillo
 * @version 1.1 (2024/02/05)
 */
public class LoanTest {

    /*
     * Verifies that a loan is correctly initialized with the provided book, user, and loan date.
     */
    @Test
    public void shouldReturnCorrectLoanDetails() {
        Book book = new Book("Java Programming", "James Gosling", "1234567890");
        User user = new User("Juan Pérez", "juan@email.com");
        LocalDateTime loanDate = LocalDateTime.now();
        
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(loanDate);
        
        assertEquals(book, loan.getBook());
        assertEquals(user, loan.getUser());
        assertEquals(loanDate, loan.getLoanDate());
    }

    /*
     * Verifies that a loan is not valid if a user is not set.
     */
    @Test
    public void notshouldReturnCorrectLoanDetailsIfUserIsNull() {
        Book book = new Book("Java Programming", "James Gosling", "1234567890");
        LocalDateTime loanDate = LocalDateTime.now();
        
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setLoanDate(loanDate);
        
        assertNull(loan.getUser());
    }

    /*
     * Verifies that a loan is not valid if a book is not set.
     */
    @Test
    public void notshouldReturnCorrectLoanDetailsIfBookIsNull() {
        User user = new User("Juan Pérez", "juan@email.com");
        LocalDateTime loanDate = LocalDateTime.now();
        
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setLoanDate(loanDate);
        
        assertNull(loan.getBook());
    }

    /*
     * Verifies that a loan has the correct status after it is set.
     */
    @Test
    public void shouldReturnCorrectLoanStatus() {
        Loan loan = new Loan();
        LoanStatus status = LoanStatus.ACTIVE;
        loan.setStatus(status);
        
        assertEquals(status, loan.getStatus());
    }

    /*
     * Verifies that a loan cannot have a null return date if the status is ACTIVE.
     */
    @Test
    public void notshouldReturnNullReturnDateIfStatusIsActive() {
        Loan loan = new Loan();
        loan.setStatus(LoanStatus.ACTIVE);
        
        assertNull(loan.getReturnDate());
    }

    /*
     * Verifies that a loan has the correct return date when the return date is set.
     */
    @Test
    public void shouldReturnCorrectReturnDate() {
        Loan loan = new Loan();
        LocalDateTime returnDate = LocalDateTime.now().plusDays(7);
        loan.setReturnDate(returnDate);
        
        assertEquals(returnDate, loan.getReturnDate());
    }
}
