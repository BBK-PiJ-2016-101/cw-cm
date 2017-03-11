import org.junit.*;
import static org.junit.Assert.*;

public class ContactTest {
    Contact testContact;

    @Before
    public void testSetup() {
	testContact = new Contact(7,"hisname is","robert paulson");
    }

    @Test
    public void testContactId() {
	assertEquals(7,testContact.getId());
    }

    @Test
    public void testContactName() {
	assertEquals("hisname is",testContact.getName());
    }

    @Test
    public void testContactNotes() {
	assertEquals("robert paulson",testContact.getNotes());
    }
}
