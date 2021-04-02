package views.admin.updateMember;

import models.LibraryMember;

public class MemberSingleton {
    private static MemberSingleton instance;
    private LibraryMember libraryMember;

    private MemberSingleton(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
    }


    public static MemberSingleton createInstance(LibraryMember member) {
        if (instance == null) {
            instance = new MemberSingleton(member);
        }
        return instance;
    }

    public static void destroySession() {
        instance = null;
    }

    public static MemberSingleton getInstance() {
        return instance;
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }
}
