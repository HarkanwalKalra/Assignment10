import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestContact {

	public static void main(String[] args) {
		ContactService service = new ContactService();
		List <Contact> contactList = new ArrayList<>();
		
		try {
		service.readContactsFromFile(contactList, "C:\\Users\\hkhar\\eclipse-workspace\\assignment10\\src\\Contacts.txt");
		for(Contact contact:contactList)
			System.out.println(contact);
		Set <Contact> dbContacts = service.populateContactFromDb();
		service.addContacts(contactList, dbContacts);
		for(Contact contact:contactList)
			System.out.println(contact);
		service.sortContactsByName(contactList);
		System.out.println("Contacts sorted by name : ");
		for(Contact contact:contactList)
			System.out.println(contact);

//		System.out.println(service.searchContactByName("Radha", contactList));
		for(Contact c : service.SearchContactByNumber("345", contactList))
			System.out.println(c);
		service.addContactNumber(3, "1234", contactList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Contact contact:contactList)
			System.out.println(contact);
	}

}
