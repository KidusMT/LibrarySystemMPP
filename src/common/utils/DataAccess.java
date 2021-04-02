package common.utils;

import models.*;

import java.util.HashMap;
import java.util.List;

public interface DataAccess {
    HashMap<String, Book> readBooksMap();

    HashMap<String, CheckoutRecord> readCheckoutRecordMap();

    HashMap<String, CheckoutEntity> readCheckoutEntityMap();

    HashMap<String, User> readUserMap();

    HashMap<String, LibraryMember> readMemberMap();

    void saveNewMember(LibraryMember member);

    void saveNewBook(Book book);

    void saveNewCheckoutRecord(CheckoutRecord record);

    void saveNewCheckoutEntity(CheckoutEntity entity);

    void loadMembers(List<LibraryMember> memberList);

    void loadBooks(HashMap<String, Book> bookList);

    void loadCheckoutRecords(HashMap<String, CheckoutRecord> recordHashMap);

    void loadCheckoutEntities(HashMap<String, CheckoutEntity> entityHashMap);

    void clearMembers();

    void clearCheckoutRecords();

    void clearCheckoutEntities();

    void clearBooks();
}
