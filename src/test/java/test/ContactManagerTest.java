import org.junit.*;
import static org.junit.Assert.*;

import spec.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class ContactManagerTest {
    Calendar testDate = new GregorianCalendar();
    Set<Contact> testContacts = new HashSet<spec.Contact>();
    ContactImpl firstContact = new ContactImpl(1,"John Doe", "Unidentified male");
    ContactImpl secondContact = new ContactImpl(2,"Jane Doe", "Unidentified female");
    // String testNotes = "these are test notes";
    ContactManager ContactManagerMock;
    PastMeeting pastMeetingMock;
    int futureMeetingId;
    int pastMeetingId;

    @Before
    public void testSetup() {
    testDate.set(2017,03,19,23,59);
    testContacts.add(firstContact);
    testContacts.add(secondContact);
    this.ContactManagerMock = new ContactManagerImpl();
    this.pastMeetingMock = new PastMeetingImpl(1,testDate,testContacts,"pastMeetingMock");
    futureMeetingId = ContactManagerMock.addFutureMeeting(testContacts,testDate);
    pastMeetingId = 13; // ContactManagerMock.addPastMeeting(testContacts,testDate);
    }

    @Test(expected=NullPointerException.class)
    public void testAddFutureMeetingException1() {
	Set<Contact> thisTestContacts = null;
	ContactManagerMock.addFutureMeeting(thisTestContacts,testDate);
    }

    @Test(expected=NullPointerException.class)
    public void testAddFutureMeetingException2() {
        Calendar thisTestDate = null;
	ContactManagerMock.addFutureMeeting(testContacts,thisTestDate);
    }

    @Test(expected=IllegalStateException.class)
    public void testAddFutureMeetingException3() {
	Calendar thisTestDate = new GregorianCalendar(1991,07,03,10,04);
	ContactManagerMock.addFutureMeeting(testContacts,thisTestDate);
    }
    
    @Test
    public void testAddFutureMeetingContacts() {
	assertEquals(ContactManagerMock.getFutureMeeting(futureMeetingId).getContacts(),testContacts);
    }

    @Test
    public void testAddFutureMeetingDate() {
	assertEquals(ContactManagerMock.getFutureMeeting(futureMeetingId).getDate(), testDate);
    }

    @Test
    public void CMTestGetPastMeetingSuccessful() {
	assertEquals(ContactManagerMock.getPastMeeting(pastMeetingId),pastMeetingMock);
    }

    @Test
    public void CMTestGetPastMeetingNull() {
	assertEquals(ContactManagerMock.getPastMeeting(10000),null);
    }

    @Test(expected=IllegalStateException.class)
    public void CMTestGetPastMeetingIllegalStateException() {
	ContactManagerMock.getPastMeeting(futureMeetingId);
    }

	   
}



    

