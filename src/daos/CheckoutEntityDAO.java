package daos;

import common.interfaces.DAO;
import common.utils.FileStorage;
import models.CheckoutEntity;

import java.util.List;

public final class CheckoutEntityDAO implements DAO<CheckoutEntity> {
    private final FileStorage<CheckoutEntity> checkoutFileStorage;

    public CheckoutEntityDAO() {
        checkoutFileStorage = new FileStorage<>();
    }

    @Override
    public CheckoutEntity get(String id) {
        List<CheckoutEntity> checkoutEntityList = getAll();
        CheckoutEntity checkoutEntity = checkoutEntityList.stream().filter(b -> b.getEntryId().equals(id)).findAny()
                .orElse(null);
        return checkoutEntity;
    }

    public CheckoutEntity getByIsbn(String isbn) {
        List<CheckoutEntity> checkoutEntityList = getAll();
        CheckoutEntity checkoutEntity = checkoutEntityList.stream().filter(b -> b.getEntryId().equals(isbn)).findAny()
                .orElse(null);
        return checkoutEntity;
    }

    @Override
    public List<CheckoutEntity> getAll() {
        List<CheckoutEntity> checkoutEntityList = checkoutFileStorage.listAll(FileStorage.StorageType.CHECKOUT_ENTITY);
        return checkoutEntityList;
    }

    @Override
    public void create(CheckoutEntity entity) {
        checkoutFileStorage.save(FileStorage.StorageType.CHECKOUT_ENTITY, entity);
    }

    @Override
    public CheckoutEntity update(CheckoutEntity entity) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
