import org.junit.*;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import spec.*;

public class FutureMeetingTest {
    Calendar testDate = new GregorianCalendar();
    Set<Contact> testContacts = new HashSet<Contact>();
    Contact firstContact = new ContactImpl(1,"John Doe", "Unidentified male");
    Contact secondContact = new ContactImpl(2,"Jane Doe", "Unidentified female");
    FutureMeeting futureMeetingMock;

    @Before
    public void testSetup() {
    testDate.set(2017,03,19,23,59);
    testContacts.add(firstContact);
    testContacts.add(secondContact);
    this.futureMeetingMock = new FutureMeetingImpl(1,testDate,testContacts);
    }

    @Test
    public void testMeetingId() {
	assertEquals(1,futureMeetingMock.getId());
    }

    @Test
    public void testMeetingDate() {
	Calendar compareDate = new GregorianCalendar();
	compareDate.set(2017,03,19,23,59);
	assertEquals(compareDate,futureMeetingMock.getDate());
    }

    @Test
    public void testMeetingContacts() {
	assertEquals(2,futureMeetingMock.getContacts().size());
    }

}
