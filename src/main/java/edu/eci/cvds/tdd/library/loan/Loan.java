package edu.eci.cvds.tdd.library.loan;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;

import java.time.LocalDateTime;

/**
 * Represents a book loan in the library system.
 * 
 * @author: Mayerlly Suárez, Jesús Pinzón & José Castillo
 * @version 1.1 (2024/02/09)
 */
public class Loan {
    private Book book;
    private User user;
    private LocalDateTime loanDate;
    private LoanStatus status;
    private LocalDateTime returnDate;

    /**
     * @return The borrowed book.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the borrowed book.
     * @param book The book to be loaned.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * @return The user who borrowed the book.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who borrowed the book.
     * @param user The user borrowing the book.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return The loan date.
     */
    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    /**
     * Sets the loan date.
     * @param loanDate The date when the loan started.
     */
    public void setLoanDate(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }

    /**
     * @return The current loan status.
     */
    public LoanStatus getStatus() {
        return status;
    }

    /**
     * Sets the loan status.
     * @param status The loan status.
     */
    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    /**
     * @return The expected return date.
     */
    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the return date.
     * @param returnDate The date when the book is expected to be returned.
     */
    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}