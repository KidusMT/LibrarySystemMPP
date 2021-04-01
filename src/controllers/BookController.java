package controllers;

import daos.BookDAO;
import models.Book;

import java.util.List;

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
}
