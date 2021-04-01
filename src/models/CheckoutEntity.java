package models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntity implements Serializable {
    private String entryId;
    private ObjectProperty<LocalDate> date;
    private ObjectProperty<LocalDate> due_date;
    private ObjectProperty<BookCopy> bookCopy;
    private ObjectProperty<CheckoutRecord> checkoutRecord;

    public CheckoutEntity(String entryId, LocalDate date, LocalDate due_date, BookCopy bookCopy, CheckoutRecord checkoutRecord) {
        this.entryId = entryId;
        this.date = new SimpleObjectProperty<>(date);
        this.due_date = new SimpleObjectProperty<>(due_date);
        this.bookCopy = new SimpleObjectProperty<>(bookCopy);
        this.checkoutRecord = new SimpleObjectProperty<>(checkoutRecord);
    }

    public BookCopy getBookCopy() {
        return bookCopy.get();
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy.set(bookCopy);
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public LocalDate getDue_date() {
        return due_date.get();
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date.set(due_date);
    }

    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord.get();
    }

    public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
        this.checkoutRecord.set(checkoutRecord);
    }
}
