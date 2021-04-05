package views.admin.bookDetail;

import models.Book;

public class BookSingleton {
    private static BookSingleton instance;
    private Book book;

    private BookSingleton(Book book) {
        this.book = book;
    }


    public static BookSingleton createInstance(Book book) {
        if (instance == null) {
            instance = new BookSingleton(book);
        }
        return instance;
    }

    public static void destroySession() {
        instance = null;
    }

    public static BookSingleton getInstance() {
        return instance;
    }

    public Book getBook() {
        return book;
    }
}
