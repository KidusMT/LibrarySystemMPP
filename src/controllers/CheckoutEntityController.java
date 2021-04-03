package controllers;

import common.utils.DataAccess;
import common.utils.DataAccessFacade;
import daos.CheckoutEntityDAO;
import models.BookCopy;
import models.CheckoutEntity;

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
    public void newCheckoutEntity(String entryId, String memberId, LocalDate date, java.time.LocalDate dueDate, BookCopy bookCopy) {
//        String entryId, LocalDate date, java.time.LocalDate dueDate, BookCopy bookCopy, CheckoutRecord checkoutRecord
        CheckoutEntity entity = new CheckoutEntity(entryId, memberId, date, dueDate, bookCopy);
        bookCopy.changeAvailability();
        dataAccess.saveNewCheckoutEntity(entity);
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

//    public List<CheckoutEntity> getCheckoutEntries(String memberId) {
//        List<CheckoutEntity> entries = new ArrayList<>();
//        DataAccess da = new DataAccessFacade();
//        HashMap<String, CheckoutEntity> e = da.readCheckoutEntityMap();
//        Set<String> keys = e.keySet();
//        for(String k : keys) {
//            CheckoutEntity entry = e.get(k);
//            if(entry.getMemberId().equals(memberId)) {
//                entries.add(entry);
//            }
//        }
//        return entries;
//    }

    public void printCheckoutEntry(String memberId) {
        List<CheckoutEntity> entries = getCheckoutEntries(memberId);
        if(entries.isEmpty()) {
            System.out.println("No Entry");
        }
        System.out.println("\nMember ID\tBook Title\tCheckout Date\tDue Date");
        System.out.println("-----------------------------------------------------------");

        for(CheckoutEntity e : entries) {
            System.out.println(e.getMemberId() + "\t\t" + e.getBookCopy().getBook().getTitle() + "\t" + e.getDate()
                    + "\t" + e.getDueDate());
        }
    }
    public List<CheckoutEntity>  getOverDueBookCopy() {
        List<CheckoutEntity> entries = new ArrayList<>();
        DataAccess da = new DataAccessFacade();
        HashMap<String, CheckoutEntity> e = da.readCheckoutEntityMap();
        Set<String> keys = e.keySet();
        for(String k : keys) {
            CheckoutEntity entry = e.get(k);
            if(LocalDate.now().compareTo(entry.getDueDate()) > 0) {
                entries.add(entry);
            }
        }
        return entries;
    }
}
