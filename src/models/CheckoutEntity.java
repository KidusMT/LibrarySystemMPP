package models;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntity implements Serializable {
    private String entryId;
    private LocalDate date;
    private LocalDate due_date;
    private CheckoutRecord checkoutRecord;

    public CheckoutEntity(String entryId, LocalDate date, LocalDate due_date, CheckoutRecord checkoutRecord) {
        this.entryId = entryId;
        this.date = date;
        this.due_date = due_date;
        this.checkoutRecord = checkoutRecord;
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
