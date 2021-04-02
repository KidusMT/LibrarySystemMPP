package controllers;

import common.utils.DataAccess;
import daos.BookDAO;
import daos.CheckoutEntityDAO;
import models.BookCopy;
import models.CheckoutEntity;
import models.CheckoutRecord;
import models.LibraryMember;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CheckoutEntityController {
    private CheckoutEntityDAO bookDAO;
    private DataAccess dataAccess;

    // add new checkout record with 1 copy #NEW
    public void newCheckoutEntity(String entryId, LocalDate date, java.time.LocalDate dueDate, BookCopy bookCopy, CheckoutRecord checkoutRecord) {
//        String entryId, LocalDate date, java.time.LocalDate dueDate, BookCopy bookCopy, CheckoutRecord checkoutRecord
        CheckoutEntity entity = new CheckoutEntity(entryId, date, dueDate, bookCopy, checkoutRecord);
        dataAccess.saveNewCheckoutEntity(entity);
    }

    // retrieve all checkout records #NEW
    public List<CheckoutEntity> getAllCheckoutEntities() {
        List<CheckoutEntity> entities = new ArrayList<>();
        HashMap<String, CheckoutEntity> entityMap = dataAccess.readCheckoutEntityMap();
        // handling NPE
        if(entityMap!=null){
            Set<String> keys = entityMap.keySet();
            for (String k : keys) {
                CheckoutEntity lb = entityMap.get(k);
                entities.add(lb);
            }
        }

        return entities;
    }
}
