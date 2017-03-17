import org.junit.*;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import spec.PastMeeting;
import spec.Meeting;

public class PastMeetingTest {
    Calendar testDate = new GregorianCalendar();
    Set<spec.Contact> testContacts = new HashSet<spec.Contact>();
    ContactImpl firstContact = new ContactImpl(1,"John Doe", "Unidentified male");
    ContactImpl secondContact = new ContactImpl(2,"Jane Doe", "Unidentified female");
    String testNotes = "these are test notes";

    @Before
    private void testSetup() {
    testDate.set(2017,03,19,23,59);
    testContacts.add(firstContact);
    testContacts.add(secondContact);
    PastMeeting PastMeetingTest = new PastMeetingImpl(1,testDate,testContacts,testNotes);
    }

    @Test
    public void testMeetingId() {
	assertEquals(1,PastMeetingTest.getId());
    }

    @Test
    public void testMeetingDate() {
	Calendar compareDate = new GregorianCalendar();
	compareDate.set(2017,03,19,23,59);
	assertEquals(compareDate,PastMeetingTest.getDate());
    }

    @Test
    public void testMeetingContacts() {
	assertEquals(2,PastMeetingTest.getContacts().size());
    }

    @Test
    public void testMeetingNotes() {
	assertEquals("these are test notes",PastMeetingTest.getNotes());
    }
}

