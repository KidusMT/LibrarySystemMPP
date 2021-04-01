package daos;

import common.interfaces.DAO;
import common.utils.FileStorage;
import models.Book;

import java.util.List;

public final class BookDAO implements DAO<Book> {
    private FileStorage<Book> bookFileStorage;

    public BookDAO() {
        bookFileStorage = new FileStorage<>();
    }

    @Override
    public Book get(String id) {
        List<Book> bookList = getAll();
        Book book = bookList.stream().filter(b -> b.getIsbn().equals(id)).findAny()
                .orElse(null);
        return book;
    }

    public Book getByIsbn(String isbn) {
        List<Book> bookList = getAll();
        Book book = bookList.stream().filter(b -> b.getIsbn().equals(isbn)).findAny()
                .orElse(null);
        return book;
    }

    @Override
    public List<Book> getAll() {
        List<Book> bookList = bookFileStorage.listAll(FileStorage.StorageType.BOOK);
        return bookList;
    }

    @Override
    public void create(Book entity) {
        bookFileStorage.save(FileStorage.StorageType.BOOK, entity);
    }

    @Override
    public Book update(Book entity) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
