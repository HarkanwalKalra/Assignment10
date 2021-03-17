import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContactTester {

	public static void main(String[] args) throws ContactNotFoundException {
		// TODO Auto-generated method stub
		ContactService service = new ContactService();
		List <Contact> contactList = new ArrayList<>();
		service.readContactsFromFile(contactList, "Contacts.txt");
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
		
		try {
			service.searchContactByName("Radha", contactList);
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
