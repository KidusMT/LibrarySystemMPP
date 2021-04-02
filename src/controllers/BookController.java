package controllers;

import common.utils.DataAccess;
import common.utils.DataAccessFacade;
import daos.BookDAO;
import models.Author;
import models.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class BookController {

    private BookDAO bookDAO;
    private DataAccess dataAccess;


    public BookController() {
        dataAccess = new DataAccessFacade();
    }


    // add new book with 1 copy #NEW
    public void newBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
        Book book = new Book(isbn, title, maxCheckoutLength, authors);
        dataAccess.saveNewBook(book);
    }

    // retrieve all books #NEW
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        HashMap<String, Book> b = dataAccess.readBooksMap();
        Set<String> keys = b.keySet();
        for (String k : keys) {
            Book lb = b.get(k);
            books.add(lb);
        }
        return books;
    }

    // search book
    public Book getBook(String isbn) {
        Book book = null;
        HashMap<String, Book> books = dataAccess.readBooksMap();
        if (!(books.containsKey(isbn))) {
            return null;
        }
        Set<String> keys = books.keySet();
        for (String k : keys) {
            if (k.equals(isbn)) {
                book = books.get(k);
            }
        }
        return book;
    }

    // add book copy
    public void addBookCopy(Book book) {
        Book newBook = book;
        newBook.addCopy();
        HashMap<String,Book> bookHashMap = dataAccess.readBooksMap();
        for(String isbn: bookHashMap.keySet()){
            if(isbn.equals(newBook.getIsbn())){
                Book oldBook = bookHashMap.get(isbn);
                bookHashMap.replace(isbn,oldBook,newBook);
            }
        }
        dataAccess.clearBooks();
        dataAccess.loadBooks(bookHashMap);
    }
}
