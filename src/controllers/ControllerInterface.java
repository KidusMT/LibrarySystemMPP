package controllers;

import java.util.List;

import models.LibraryMember;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	
	public void newMember(LibraryMember member);
	public List<LibraryMember> getAllMembers();

}
