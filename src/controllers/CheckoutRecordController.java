package controllers;

import common.utils.DataAccess;
import common.utils.DataAccessFacade;
import daos.CheckoutRecordDAO;
import models.CheckoutRecord;
import models.LibraryMember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CheckoutRecordController {

    private final DataAccess dataAccess;
    private CheckoutRecordDAO checkoutRecordDAO;

    public CheckoutRecordController() {
        dataAccess = new DataAccessFacade();
    }

    // add new checkout record with 1 copy #NEW
    public void newCheckoutRecord(String checkoutId, LibraryMember checkedOutBy) {
        CheckoutRecord record = new CheckoutRecord(checkoutId, checkedOutBy);
        dataAccess.saveNewCheckoutRecord(record);
    }

    // retrieve all checkout records #NEW
    public List<CheckoutRecord> getAllCheckoutRecords() {
        List<CheckoutRecord> records = new ArrayList<>();
        HashMap<String, CheckoutRecord> recordMap = dataAccess.readCheckoutRecordMap();
        // handling NPE
        if (recordMap != null) {
            Set<String> keys = recordMap.keySet();
            for (String k : keys) {
                CheckoutRecord lb = recordMap.get(k);
                records.add(lb);
            }
        }

        return records;
    }
}
