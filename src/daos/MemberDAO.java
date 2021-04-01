package daos;

import common.interfaces.DAO;
import common.utils.FileStorage;
import models.LibraryMember;

import java.util.List;

public class MemberDAO implements DAO<LibraryMember> {
    private FileStorage<LibraryMember> memberFileStorage;

    public MemberDAO() {
        memberFileStorage = new FileStorage<>();
    }

    @Override
    public LibraryMember get(String id) {
        return null;
    }

    @Override
    public List<LibraryMember> getAll() {
        List<LibraryMember> memberList = memberFileStorage.listAll(FileStorage.StorageType.MEMBER);
        return memberList;
    }

    public void create(LibraryMember entity) {
        memberFileStorage.save(FileStorage.StorageType.MEMBER, entity);
    }

    @Override
    public LibraryMember update(LibraryMember entity) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }
}
