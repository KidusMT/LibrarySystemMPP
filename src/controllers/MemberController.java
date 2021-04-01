package controllers;

import daos.MemberDAO;
import models.LibraryMember;

import java.util.List;

public class MemberController {
    private MemberDAO memberDAO;

    public MemberController() {
        memberDAO = new MemberDAO();
    }

    public void createMember(LibraryMember member) {
        memberDAO.create(member);
    }

    public List<LibraryMember> getAllMembers() {
        return memberDAO.getAll();
    }
}
