package models;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntity implements Serializable {
    public static final double FINE_RATE = 0.25;// $0.25 / day for a fine rate
    private String entryId;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private BookCopy bookCopy;
    private String memberId;
    private double fineAmount;// fine = overDue * FINE_RATE
    private long overdue;//days passed return day
    private LocalDate paidDate;
    private static final long serialVersionUID = 5543070115257205802L;
    public CheckoutEntity(String entryId, String mId, LocalDate borrowedDate, LocalDate dueDate,
                          LocalDate returnDate, BookCopy bookCopy, double fAmount, LocalDate pDate, long odue) {
        this.entryId = entryId;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.bookCopy = bookCopy;
        this.memberId = mId;
        this.returnDate = returnDate;
        this.fineAmount = fAmount;
        this.paidDate = pDate;
        this.overdue = odue;
    }

    public long getOverdue() {
        return overdue;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getMemberId() {
        return memberId;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public String getEntryId() {
        return entryId;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }
}
