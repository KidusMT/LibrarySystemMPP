package common.utils;

import models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class loads data into the data repository and also
 * sets up the storage units that are used in the application.
 * The main method in this class must be run once (and only
 * once) before the rest of the application can work properly.
 * It will create three serialized objects in the dataaccess.storage
 * folder.
 */
public class
TestData {


    ///////////// DATA //////////////
    List<LibraryMember> members = new ArrayList<LibraryMember>();
    @SuppressWarnings("serial")

    List<Address> addresses = new ArrayList<Address>() {
        {
            add(new Address("51 S. George", "Georgetown", "MI", 65434));
            add(new Address("23 Headley Ave", "Seville", "Georgia", 41234));
            add(new Address("1 N. Baton", "Baton Rouge", "LA", 33556));
            add(new Address("5001 Venice Dr.", "Los Angeles", "CA", 93736));
            add(new Address("1435 Channing Ave", "Palo Alto", "CA", 94301));
            add(new Address("42 Dogwood Dr.", "Fairfield", "IA", 52556));
            add(new Address("501 Central", "Mountain View", "CA", 94707));
        }
    };
    @SuppressWarnings("serial")
    public List<Author> allAuthors = new ArrayList<Author>() {
        {
            add(new Author("Joe", "Thomas", "641-445-2123", addresses.get(0), "A happy man is he."));
            add(new Author("Sandra", "Thomas", "641-445-2123", addresses.get(0), "A happy wife is she."));
            add(new Author("Nirmal", "Pugh", "641-919-3223", addresses.get(1), "Thinker of thoughts."));
            add(new Author("Andrew", "Cleveland", "976-445-2232", addresses.get(2), "Author of childrens' books."));
            add(new Author("Sarah", "Connor", "123-422-2663", addresses.get(3), "Known for her clever style."));
        }
    };
    @SuppressWarnings("serial")
    List<Book> allBooks = new ArrayList<Book>() {
        {
            add(new Book("23-11451", "The Big Fish", 21, Arrays.asList(allAuthors.get(0), allAuthors.get(1))));
            add(new Book("28-12331", "Antartica", 7, Arrays.asList(allAuthors.get(2))));
            add(new Book("99-22223", "Thinking Java", 21, Arrays.asList(allAuthors.get(3))));
            add(new Book("48-56882", "Jimmy's First Day of School", 7, Arrays.asList(allAuthors.get(4))));
        }
    };
    List<CheckoutRecord> allCheckoutRecords = new ArrayList<CheckoutRecord>() {
        {
            add(new CheckoutRecord("1001", new LibraryMember("100", "Hans", "Muster", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1002", new LibraryMember("101", "Ruth", "Mueller", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1003", new LibraryMember("102", "Heinz", "Kurz", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1004", new LibraryMember("103", "Cornelia", "Meier", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1005", new LibraryMember("104", "Werner", "Meyer", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1006", new LibraryMember("105", "Lydia", "Kunz", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1007", new LibraryMember("106", "Anna", "Best", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1008", new LibraryMember("107", "Stefan", "Meier", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
            add(new CheckoutRecord("1009", new LibraryMember("108", "Martin", "Mueller", "123124135", new Address("1000 N. 4th St.", "Fairfield", "IA", 52557))));
        }
    };
    @SuppressWarnings("serial")
    List<User> allUsers = new ArrayList<User>() {
        {
            add(new User("101", "xyz", Authorization.LIBRARIAN));
            add(new User("102", "abc", Authorization.ADMIN));
            add(new User("103", "111", Authorization.BOTH));
        }
    };

    public static void main(String[] args) {
        TestData td = new TestData();
//        td.bookData();
//		td.libraryMemberData();
//        td.userData();
		td.loadUserData();
        DataAccess da = new DataAccessFacade();
//		td.allCheckoutRecords;
//		HashMap<String, CheckoutEntity> hashMap = new HashMap<>();
//		CheckoutEntity ce = new CheckoutEntity("1001", "100", LocalDate.now(), LocalDate.now(),
//				new BookCopy(new Book("23-11451", "The Big Fish", 21,
//						Arrays.asList(new Author("Joe", "Thomas", "641-445-2123",
//				new Address("51 S. George", "Georgetown", "MI", 65434),
//								"A happy man is he."))), 3, true));
//		hashMap.put(ce.getEntryId(), ce);
//		saveToStorage(DataAccessFacade.StorageType.CHECKOUT_ENTITY, hashMap);

//		da.saveNewCheckoutRecord();
        System.out.println("----> " + da.readUserMap());

//		System.out.println(da.readCheckoutRecordMap());
//		System.out.println(da.readBooksMap());
//		System.out.println(da.readUserMap());
//		System.out.println(da.readMemberMap());
    }

    ///create books
    public void bookData() {
        allBooks.get(0).addCopy();
        allBooks.get(0).addCopy();
        allBooks.get(1).addCopy();
        allBooks.get(3).addCopy();
        allBooks.get(2).addCopy();
        allBooks.get(2).addCopy();
        DataAccessFacade.loadBookMap(allBooks);
    }

    public void userData() {
        DataAccessFacade.loadUserMap(allUsers);
    }

    //create library members
    public void libraryMemberData() {
        LibraryMember libraryMember = new LibraryMember("1001", "Andy", "Rogers", "641-223-2211", addresses.get(4));
        members.add(libraryMember);
        libraryMember = new LibraryMember("1002", "Drew", "Stevens", "702-998-2414", addresses.get(5));
        members.add(libraryMember);

        libraryMember = new LibraryMember("1003", "Sarah", "Eagleton", "451-234-8811", addresses.get(6));
        members.add(libraryMember);

        libraryMember = new LibraryMember("1004", "Ricardo", "Montalbahn", "641-472-2871", addresses.get(7));
        members.add(libraryMember);

        DataAccessFacade.loadMemberMap(members);
    }

    public void loadUserData() {
        User firstMember = new User("lib12", "pass", Authorization.LIBRARIAN);
        User secondMember = new User("lib13", "pass", Authorization.LIBRARIAN);
        User admin = new User("admin123", "pass", Authorization.ADMIN);
        User superAdmin = new User("super123", "pass", Authorization.BOTH);
        DataAccessFacade.loadUserMap(new ArrayList<>(Arrays.asList(firstMember, secondMember, admin, superAdmin)));
    }
}
