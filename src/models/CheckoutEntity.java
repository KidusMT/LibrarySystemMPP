package models;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntity implements Serializable {
    private String entryId;
    private LocalDate date;
    private LocalDate dueDate;
    private BookCopy bookCopy;
    private String memberId;
    private static final long serialVersionUID = 5543070115257205802L;
    public CheckoutEntity(String entryId, String mId, LocalDate date, LocalDate dueDate, BookCopy bookCopy) {
        this.entryId = entryId;
        this.date = date;
        this.dueDate = dueDate;
        this.bookCopy = bookCopy;
        this.memberId = mId;
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

    public LocalDate getDate() {
        return date;
    }
}
