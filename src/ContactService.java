import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

class ContactNotFoundException extends Exception{
	public ContactNotFoundException() {
		super();
		this.getMessage();
		this.printStackTrace();
		// TODO Auto-generated constructor stub
	}
}
public class ContactService {
	public void addContact(Contact contact,List<Contact> contacts) {
		if(contacts.contains(contact)) {
			System.out.println("Contact already exist in the list");
		}
		else {
			contacts.add(contact);
			System.out.println("Contact added successfully");
		}
	}
	
	public void removeContact(Contact contact, List<Contact> contacts)throws ContactNotFoundException{
		if(contacts.contains(contact)) {
			contacts.remove(contact);
			System.out.println("Contact removed successfully");
		}
		else {
			throw new ContactNotFoundException();
		}
	}
	
	public void searchContactByName(String name, List<Contact> contact) throws ContactNotFoundException{
		for(Contact cont: contact) {
			if(cont.getContactName().equals(name)) {
				System.out.println("Contact Found");
			}
		}
		throw new ContactNotFoundException();
	}
	
	public List<Contact> SearchContactByNumber(String number, List<Contact> contact) throws ContactNotFoundException{
		List<Contact> res=new ArrayList<Contact>();
		for(Contact c: contact) {
			List<String> cList=c.getContactNumber();
			for(String curr:cList) {
				if(curr.contains(number))
					res.add(c);
			}
		}
		
		if(res.size()==0)
			throw new ContactNotFoundException();
		return res;
	}
	
	public void addContactNumber(int contactId, String contactNo, List<Contact> contacts) {
		for(Contact c:contacts) {
			if(c.getContactId()==contactId) {
				if(c.getContactNumber() != null) {
					c.getContactNumber().add(contactNo);
					System.out.println("Number added successfully");
					return;
				}else {
					List<String> num = new ArrayList<>();
					num.add(contactNo);
					c.setContactNumber(num);
					return;
				}
			}
		}
	}
	public void sortContactsByName(List<Contact> contacts) {
		Collections.sort(contacts);
	}
	
	public void readContactsFromFile(List<Contact> contacts, String fileNAme) {
		File  file=new File(fileNAme);
		try {
			Scanner sc=new Scanner(file);
			while(sc.hasNextLine()) {
				String arr[]=sc.nextLine().split(",");
				Contact c=new Contact(Integer.parseInt(arr[0]),arr[1],arr[2],new ArrayList<String>());
				contacts.add(c);
			}
			sc.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void serializeContactDetails(List<Contact> contacts , String filename){
		File file =new File(filename);
		try {
			FileOutputStream fos=new FileOutputStream(file);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(contacts);
			oos.close();
			fos.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<Contact> deserializeContact(String fileName){
		List<Contact> contacts = new ArrayList<>();
		File file = new File(fileName);
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			contacts = (List<Contact>) ois.readObject();
			
			fis.close();
			ois.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contacts;
	}
	
	public Set<Contact> populateContactFromDb(){
		return ContactsDao.populateContactUtil();
	}
	
	Boolean addContacts(List<Contact> existingContact,Set<Contact> newContacts) throws ContactNotFoundException{
		if(newContacts.size() == 0) {
			System.out.println("Nothing to add");
			throw new ContactNotFoundException();
		}
		existingContact.addAll(newContacts);
		System.out.println("New contacts added successfully.");
		return true;
	}
}
