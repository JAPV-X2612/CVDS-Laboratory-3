package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {

    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link edu.eci.cvds.tdd.library.book.Book} into the system, the book is store in a Map that contains
     * the {@link edu.eci.cvds.tdd.library.book.Book} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     *
     * @return true if the book was stored false otherwise.
     */
    public boolean addBook(Book book) {
        if (books.containsKey(book)) {
        // If the book exists in the map, increment the count by 1.
        books.put(book, books.get(book) + 1);
    } else {
        // If the book is not in the map, add it with a count of 1.
        books.put(book, 1);
    }
    return true;
    }

    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn book identification.
     *
     * @return The new created loan.
     */
    public Loan loanABook(String userId, String isbn) {
        // Check if the user exists
        User user = findUserById(userId); // Implement this logic to find a user by ID
        if (user == null) {
            throw new IllegalArgumentException("User does not exist");
        }

        // Check if the book exists and is available
        Book book = findBookByIsbn(isbn); // Implement this logic to find a book by ISBN
        if (book == null || books.get(book) <= 0) {
            throw new IllegalArgumentException("Book not available");
        }

        // Check if the user already has the book on loan
        for (Loan loan : loans) {
            if (loan.getUserId().equals(userId) && loan.getBookIsbn().equals(isbn) && loan.getStatus() == LoanStatus.ACTIVE) {
                throw new IllegalArgumentException("User already has this book on loan");
            }
        }

        // Create the loan
        Loan newLoan = new Loan(userId, isbn, LoanStatus.ACTIVE);
        loans.add(newLoan);

        // Decrease the available amount of the book
        books.put(book, books.get(book) - 1);

        return newLoan;
    }

    /**
     * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
     * in the loan list should be {@link edu.eci.cvds.tdd.library.loan.LoanStatus#RETURNED} and the loan return
     * date should be the current date, validate that the loan exist.
     *
     * @param loan loan to return.
     *
     * @return the loan with the RETURNED status.
     */
    public Loan returnLoan(Loan loan) {
        return null;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    @SuppressWarnings("rawtypes")
    public Map getBooks(){
        return books;
    }

    // New Methods by Jesus

    /**
     * Finds a user by their userId.
     *
     * @param userId the id of the user to find.
     * @return the user if found, null otherwise.
     */
    public User findUserById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null; // Return null if the user is not found
    }

    /**
     * Finds a book by its ISBN.
     *
     * @param isbn the ISBN of the book to find.
     * @return the book if found, null otherwise.
     */
    public Book findBookByIsbn(String isbn) {
        for (Book book : books.keySet()) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null; // Return null if the book is not found
    }

}