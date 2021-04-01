package models;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntity implements Serializable {
    private String entryId;
    private LocalDate date;
    private LocalDate due_date;
    private BookCopy bookCopy;
    private CheckoutRecord checkoutRecord;

    public CheckoutEntity(String entryId, LocalDate date, LocalDate due_date, BookCopy bookCopy, CheckoutRecord checkoutRecord) {
        this.entryId = entryId;
        this.date = date;
        this.due_date = due_date;
        this.bookCopy = bookCopy;
        this.checkoutRecord = checkoutRecord;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }

    public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
        this.checkoutRecord = checkoutRecord;
    }
}
