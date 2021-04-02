package common.utils;

import models.*;
import models.User;

import java.util.HashMap;
import java.util.List;

public interface DataAccess {
    HashMap<String, Book> readBooksMap();

    HashMap<String, User> readUserMap();

    HashMap<String, LibraryMember> readMemberMap();

    void saveNewMember(LibraryMember member);

    void saveNewBook(Book book);


    public void loadMembers(List<LibraryMember> memberList); 
	public void clearMembers();
}
