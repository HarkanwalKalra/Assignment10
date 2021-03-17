
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ContactsDao {
	static Connection con = DBConnectionUtil.getConnection();
	
	public static Set<Contact> populateContactUtil() {
		Contact contact = null;
		Set <Contact> contacts = new LinkedHashSet<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery("select * from contact_tbl");
			while(results.next()) {
				contact = new Contact();
				contact.setContactId(results.getInt(1));
				contact.setContactName(results.getString(2));
				contact.setAddress(results.getString(3));
				if(results.getString(4) != null) {
					contact.setContactNumber(List.of(results.getString(4).split(",")));
				}
				contacts.add(contact);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contacts;
	}

}
