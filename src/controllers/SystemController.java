package controllers;

import common.utils.DataAccess;
import common.utils.DataAccessFacade;
import common.utils.UserSession;
import models.Author;
import models.Book;
import models.LibraryMember;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class SystemController {
    public static UserSession currentAuth = null;

    public void login(String id, String password) throws LoginException {
        DataAccess da = new DataAccessFacade();
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(id)) {
            throw new LoginException("ID " + id + " not found");
        }
        String passwordFound = map.get(id).getPassword();
        if (!passwordFound.equals(password)) {
            throw new LoginException("Password incorrect");
        }
        currentAuth = UserSession.createInstance(map.get(id).getId(), map.get(id).getAuthorization());
    }

    // add new member #NEW
    public void newMember(LibraryMember member) {
        DataAccess da = new DataAccessFacade();
        da.saveNewMember(member);

    }

    // retrieve all members returns as a list to the view controller #NEW
    public List<LibraryMember> getAllMembers() {
        List<LibraryMember> membersList = new ArrayList<>();
        DataAccess da = new DataAccessFacade();
        HashMap<String, LibraryMember> members = da.readMemberMap();
        Set<String> keys = members.keySet();
        for (String k : keys) {
            LibraryMember lb = members.get(k);
            membersList.add(lb);
        }
        return membersList;
    }

    // add new book with 1 copy #NEW
    public void newBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
        Book b = new Book(isbn, title, maxCheckoutLength, authors);
    }

    // retrieve all books #NEW
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        DataAccess da = new DataAccessFacade();
        HashMap<String, Book> b = da.readBooksMap();
        Set<String> keys = b.keySet();
        for (String k : keys) {
            Book lb = b.get(k);
            books.add(lb);
        }
        return books;
    }


    /*@Override
    public List<String> allMemberIds() {
        DataAccess da = new DataAccessFacade();
        List<String> retval = new ArrayList<>();
        retval.addAll(da.readMemberMap().keySet());
        return retval;
    }

    @Override
    public List<String> allBookIds() {
        DataAccess da = new DataAccessFacade();
        List<String> retval = new ArrayList<>();
        retval.addAll(da.readBooksMap().keySet());
        return retval;
    } */


}
