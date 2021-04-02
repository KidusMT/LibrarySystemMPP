package controllers;

import daos.MemberDAO;
import models.Address;
import models.LibraryMember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import common.utils.DataAccess;
import common.utils.DataAccessFacade;


public class MemberController {
    private MemberDAO memberDAO;

    public MemberController() {
        memberDAO = new MemberDAO();
    }

    public void createMember(LibraryMember member) {
        memberDAO.create(member);
    }

//    public List<LibraryMember> getAllMembers() {
//        return memberDAO.getAll();
//    }
    
 // retrieve all members returns as a list to the view controller #NEW
 	public List<LibraryMember> getAllMembers() { 
 		List<LibraryMember> membersList = new ArrayList<>();
 		DataAccess da = new DataAccessFacade();
 		HashMap<String, LibraryMember> members = da.readMemberMap();
 		// handle NPE here as well
		if(members!=null){
			Set<String> keys = members.keySet();
			for(String k : keys) {
				LibraryMember lb = members.get(k);
				membersList.add(lb);
			}
		}

 		return membersList;
 	}
 	
 	// add new member #NEW
 	public void addNewMember(String memberId, String fname, String lname, String tel, 
 			String street, String city, String state, int zip) { 
 		
 		DataAccess da = new DataAccessFacade();
 		LibraryMember lb = new LibraryMember(memberId, fname, lname, tel, new Address(street, city, state, zip));
 		da.saveNewMember(lb);
 		
 	}
 	
 	// search and return member #NEW
 	public LibraryMember getMember(String memberId) {
 		DataAccess da = new DataAccessFacade();
 		LibraryMember lb = null;
 		HashMap<String, LibraryMember> members = da.readMemberMap();
 		Set<String> keys = members.keySet();
 		for(String k : keys) {
 			if(k.equals(memberId)) {
 			  lb = members.get(k); 
 			}
 		} 
 		return lb;
 	}
 		
 	// delete member #NEW
 	public boolean deleteMember(String memberId) {
 		LibraryMember lb;
 		DataAccess da = new DataAccessFacade();
 		HashMap<String, LibraryMember> members = da.readMemberMap();
 		if(!(members.containsKey(memberId))) {
 			return false;
 		}
 						
 		List<LibraryMember> newMembersList = new ArrayList<>();
 		Set<String> keys = members.keySet();
 		for(String k : keys) {
 			if(k.equals(memberId)) {
 			  members.remove(memberId);
 			  da.clearMembers();
 			}
 		}
 		
 		Set<String> keySet = members.keySet();
 		for(String k : keySet) {
 			 lb = members.get(k);
 			 newMembersList.add(lb);
 		} 
 		da.loadMembers(newMembersList);				
 		
 		return true;
 	}
 	
 	// update member #NEW 
 	public void editMember(String memberId, String fname, String lname, String tel, 
 			String street, String city, String state, int zip) {
 		LibraryMember lb;
 		DataAccess da = new DataAccessFacade();
 		HashMap<String, LibraryMember> members = da.readMemberMap();
 		Set<String> keys = members.keySet();
 		for(String k : keys) {
 			if(k.equals(memberId)) {
 				lb = members.get(k);
 				deleteMember(memberId);
 				lb = new LibraryMember(memberId, fname, lname, tel, new Address(street, city, state, zip));
 				da.saveNewMember(lb);
 			}
 		} 
 	}
}
