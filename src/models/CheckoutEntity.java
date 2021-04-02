package models;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntity implements Serializable {
    private String entryId;
    private LocalDate date;
    private LocalDate dueDate;
    private BookCopy bookCopy;
    private CheckoutRecord checkoutRecord;

    public CheckoutEntity(String entryId, LocalDate date, LocalDate dueDate, BookCopy bookCopy, CheckoutRecord checkoutRecord) {
        this.entryId = entryId;
        this.date = date;
        this.dueDate = dueDate;
        this.bookCopy = bookCopy;
        this.checkoutRecord = checkoutRecord;
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


    public LocalDate getDue_date() {
        return dueDate;
    }


    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }

}
