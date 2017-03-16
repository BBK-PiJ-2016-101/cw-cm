import org.junit.*;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import spec.Meeting;

public class FutureMeetingTest {
    Calendar testDate = new GregorianCalendar();
    Set<spec.Contact> testContacts = new HashSet<spec.Contact>();
    ContactImpl firstContact = new ContactImpl(1,"John Doe", "Unidentified male");
    ContactImpl secondContact = new ContactImpl(2,"Jane Doe", "Unidentified female");

    @Before
    private void testSetup() {
    testDate.set(2017,03,19,23,59);
    testContacts.add(firstContact);
    testContacts.add(secondContact);
    FutureMeetingImpl FutureMeetingTest = new FutureMeetingImpl(1,testDate,testContacts);
    }

        @Test
    public void testMeetingId() {
	assertEquals(1,FutureMeetingTest.getId());
    }

    @Test
    public void testMeetingDate() {
	Calendar compareDate = new GregorianCalendar();
	compareDate.set(2017,03,19,23,59);
	assertEquals(compareDate,FutureMeetingTest.getDate());
    }

    @Test
    public void testMeetingContacts() {
	assertEquals(2,FutureMeetingTest.getContacts().size());
    }
}
