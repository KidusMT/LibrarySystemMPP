package controllers;

import common.utils.DataAccess;
import common.utils.DataAccessFacade;
import daos.CheckoutEntityDAO;
import models.Address;
import models.BookCopy;
import models.CheckoutEntity;
import models.LibraryMember;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CheckoutEntityController {
    private CheckoutEntityDAO bookDAO;
    private final DataAccess dataAccess;

    public CheckoutEntityController() {
        dataAccess = new DataAccessFacade();
    }

    // add new checkout record with 1 copy #NEW
    public void newCheckoutEntity(String entryId, String memberId, LocalDate borrowedDate, LocalDate dueDate,
                                  LocalDate returnDate, BookCopy bookCopy, double fAmount, LocalDate pDate, long odue) {
        CheckoutEntity entity = new CheckoutEntity(entryId, memberId, borrowedDate, dueDate, returnDate, bookCopy, fAmount,
                pDate, odue);

        bookCopy.changeAvailability();
        dataAccess.saveNewCheckoutEntity(entity);
    }

    public void updateCheckoutEntity(String entryId, String memberId, LocalDate borrowedDate, LocalDate dueDate,
                                     LocalDate returnDate, BookCopy bookCopy, double fAmount, LocalDate pDate, long odue){
        CheckoutEntity entity = new CheckoutEntity(entryId, memberId, borrowedDate, dueDate, returnDate, bookCopy, fAmount,
                pDate, odue);
        HashMap<String, CheckoutEntity> entityMap = dataAccess.readCheckoutEntityMap();
        Set<String> keys = entityMap.keySet();
        for(String k : keys) {
            if(k.equals(entryId)) {
                dataAccess.saveNewCheckoutEntity(entity);
                break;
            }
        }
    }

    public List<CheckoutEntity> getCheckoutEntries(String memberId) {
        List<CheckoutEntity> entries = new ArrayList<>();
        HashMap<String, CheckoutEntity> e = dataAccess.readCheckoutEntityMap();
        if (e != null) {
            Set<String> keys = e.keySet();
            for (String k : keys) {
                CheckoutEntity entry = e.get(k);
                if (entry.getMemberId().equals(memberId)) {
                    entries.add(entry);
                }
            }
        }
        return entries;
    }

    // retrieve all checkout records #NEW
    public List<CheckoutEntity> getAllCheckoutEntities() {
        List<CheckoutEntity> entities = new ArrayList<>();
        HashMap<String, CheckoutEntity> entityMap = dataAccess.readCheckoutEntityMap();
        // handling NPE
        if (entityMap != null) {
            Set<String> keys = entityMap.keySet();
            for (String k : keys) {
                CheckoutEntity lb = entityMap.get(k);
                entities.add(lb);
            }
        }

        return entities;
    }

    public void printCheckoutEntry(String memberId) {
        List<CheckoutEntity> entries = getCheckoutEntries(memberId);
        if(entries.isEmpty()) {
            System.out.println("No Entry");
        }
        System.out.println("Member ID\tBook Title\tCheckout Date\tDue Date");
        System.out.println("-----------------------------------------------------------");

        for(CheckoutEntity e : entries) {
            System.out.println(e.getMemberId() + "\t\t" + e.getBookCopy().getBook().getTitle() + "\t" + e.getBorrowedDate()
                    + "\t" + e.getDueDate());
        }
    }
}
