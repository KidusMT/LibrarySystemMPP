package controllers;

import java.util.List;

import models.Author;
import models.Book;
import models.LibraryMember;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	
	public void newMember(LibraryMember member);
//	public List<LibraryMember> getAllMembers(); 
	
	public void addNewMember(String memberId, String fname, String lname, String tel, 
			String street, String city, String state, String zip);
	public boolean deleteMember(String memberId); 
	public LibraryMember getMember(String memberId);
	public List<LibraryMember> getAllMembers();
	public void editMember(String memberId, String fname, String lname, String tel, 
			String street, String city, String state, int zip);
	
	
	public void newBook(String isbn, String title, int maxCheckoutLength, List<Author> authors);
	public List<Book> getAllBooks();
	public Book getBook(String isbn);
	public int addBookCopy(String isbn);

}
