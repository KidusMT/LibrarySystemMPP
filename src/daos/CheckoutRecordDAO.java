package daos;

import common.interfaces.DAO;
import common.utils.FileStorage;
import models.CheckoutRecord;

import java.util.List;

public final class CheckoutRecordDAO implements DAO<CheckoutRecord> {
    private final FileStorage<CheckoutRecord> checkoutFileStorage;

    public CheckoutRecordDAO() {
        checkoutFileStorage = new FileStorage<>();
    }

    @Override
    public CheckoutRecord get(String id) {
        List<CheckoutRecord> checkoutRecordList = getAll();
        CheckoutRecord checkoutRecord = checkoutRecordList.stream().filter(b -> b.getCheckoutId().equals(id)).findAny()
                .orElse(null);
        return checkoutRecord;
    }

    public CheckoutRecord getByIsbn(String isbn) {
        List<CheckoutRecord> checkoutRecordList = getAll();
        CheckoutRecord checkoutRecord = checkoutRecordList.stream().filter(b -> b.getCheckoutId().equals(isbn)).findAny()
                .orElse(null);
        return checkoutRecord;
    }

    @Override
    public List<CheckoutRecord> getAll() {
        List<CheckoutRecord> checkoutRecordList = checkoutFileStorage.listAll(FileStorage.StorageType.CHECKOUT_RECORD);
        return checkoutRecordList;
    }

    @Override
    public void create(CheckoutRecord entity) {
        checkoutFileStorage.save(FileStorage.StorageType.CHECKOUT_RECORD, entity);
    }

    @Override
    public CheckoutRecord update(CheckoutRecord entity) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
