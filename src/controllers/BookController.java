package controllers;

import daos.BookDAO;
import models.Author;
import models.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import common.utils.DataAccess;
import common.utils.DataAccessFacade;

public class BookController {

    private BookDAO bookDAO;

    public BookController() {
        bookDAO = new BookDAO();
    }

    public void createBook(Book book) {
        bookDAO.create(book);
    }

    public List<Book> getBookList() {
        return bookDAO.getAll();
    }

    public Book getBookByISBN(String isbn) {
        return bookDAO.getByIsbn(isbn);
    }
    
    // add new book with 1 copy #NEW
 	public void newBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
 		Book book = new Book(isbn, title, maxCheckoutLength, authors);
		DataAccess da = new DataAccessFacade();
		da.saveNewBook(book);
	}
 	
 	// retrieve all books #NEW
 	public List<Book> getAllBooks() {
 		List<Book> books = new ArrayList<>();
 		DataAccess da = new DataAccessFacade();
 		HashMap<String, Book> b = da.readBooksMap();
 		Set<String> keys = b.keySet();
 		for(String k : keys) {
 			  Book lb = b.get(k);
 			  books.add(lb);	  
 		} 
 		return books;
 	}
 	
 	// search book 
 	public Book getBook(String isbn) {
 		Book book = null;
 		DataAccess da = new DataAccessFacade();
 		HashMap<String, Book> books = da.readBooksMap();
 		if(!(books.containsKey(isbn))) {
 			return null;
 		}
 		Set<String> keys = books.keySet();
 		for(String k : keys) {
 			if(k.equals(isbn)) {
 			  book = books.get(k);
 			}
 		} 
 		return book;
 	}
 	
 	// add book copy 
 	public int addBookCopy(String isbn) {
 		Book book = getBook(isbn);
 		if(book == null) {
 			return -1;
 		}
 		return 1;
 	}
}
