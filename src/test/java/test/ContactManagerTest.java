import org.junit.*;
import static org.junit.Assert.*;

import spec.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class ContactManagerTest {
    Calendar futureDate = new GregorianCalendar();
    Calendar pastDate = new GregorianCalendar();
    Set<Contact> testContacts = new HashSet<spec.Contact>();
    ContactImpl firstContact = new ContactImpl(1,"John Doe", "Unidentified male");
    ContactImpl secondContact = new ContactImpl(2,"Jane Doe", "Unidentified female");
    // String testNotes = "these are test notes";
    ContactManager ContactManagerMock;
    PastMeeting pastMeetingMock;
    // FutureMeeting futureMeetingMock;
    int futureMeetingId;
    int pastMeetingId;

    @Before
    public void testSetup() {
    this.pastDate.set(2017,01,01,01,01);
    this.futureDate.set(3017,01,01,01,01);
    this.testContacts.add(firstContact);
    this.testContacts.add(secondContact);
    this.ContactManagerMock = new ContactManagerImpl();

    this.futureMeetingId = ContactManagerMock.addFutureMeeting(testContacts,futureDate);
    this.pastMeetingId = ContactManagerMock.addNewPastMeeting(testContacts,pastDate,"pastMeetingMock");
    this.pastMeetingMock = new PastMeetingImpl(pastMeetingId,pastDate,testContacts,"pastMeetingMock");
    // this.futureMeetingMock = new FutureMeetingImpl(futureMeetingId,futureDate,testContacts);
    }

    @Test(expected=NullPointerException.class)
    public void CMTestAddFutureMeetingNullPointerException() {
	Set<Contact> thisTestContacts = null;
	ContactManagerMock.addFutureMeeting(thisTestContacts,futureDate);
    }

    @Test(expected=NullPointerException.class)
    public void CMTestAddFutureMeetingNullPointerException2() {
        Calendar thisTestDate = null;
	ContactManagerMock.addFutureMeeting(testContacts,thisTestDate);
    }

    @Test(expected=IllegalStateException.class)
    public void CMTestAddFutureMeetingIllegalStateException() {
	Calendar thisTestDate = new GregorianCalendar(1991,07,03,10,04);
	ContactManagerMock.addFutureMeeting(testContacts,thisTestDate);
    }
    
    @Test
    public void testAddFutureMeetingContacts() {
	assertEquals(ContactManagerMock.getFutureMeeting(futureMeetingId).getContacts(),testContacts);
    }

    @Test
    public void testAddFutureMeetingDate() {
	assertEquals(ContactManagerMock.getFutureMeeting(futureMeetingId).getDate(), futureDate);
    }

    @Test
    public void  CMTestAddNewMeetingSuccess() {
	assertNotNull(ContactManagerMock.addNewPastMeeting(pastMeetingMock.getContacts(),pastMeetingMock.getDate(),pastMeetingMock.getNotes()));
    }
	    
    @Test
    public void CMTestGetPastMeetingSuccessful() {
	assertEquals(ContactManagerMock.getPastMeeting(pastMeetingId).getId(),pastMeetingId);
    }

    @Test
    public void CMTestGetPastMeetingNull() {
	assertNull(ContactManagerMock.getPastMeeting(pastMeetingId*2));
    }

    @Test(expected=IllegalStateException.class)
    public void CMTestGetPastMeetingIllegalStateException() {
	ContactManagerMock.getPastMeeting(futureMeetingId);
    }

    @Test
    public void CMTestGetFutureMeetingSuccessful() {
	    assertEquals(ContactManagerMock.getFutureMeeting(futureMeetingId).getId(),futureMeetingId);
    }

    @Test
    public void CMTestGetFutureMeetingNull() {
	assertNull(ContactManagerMock.getFutureMeeting(futureMeetingId*2));
    }

    @Test(expected=IllegalStateException.class)
    public void CMTestGetFutureMeetingIllegalStateException() {
	ContactManagerMock.getFutureMeeting(pastMeetingId);
    }

    @Test(expected=IllegalArgumentException.class)
    public void CMTestGetMeetingListForIllegalArgumentException() {
        Contact testContact = new ContactImpl(1,"No Doe");
	ContactManagerMock.getFutureMeetingList(testContact);
    }

    @Test(expected=NullPointerException.class)
    public void CMTestGetMeetingListForNullPointerException() {
        Contact testContact = null;
	ContactManagerMock.getFutureMeetingList(testContact);
    }

    @Test
    public void CMTestGetMeetingListForSuccess() {
	assertNotNull(ContactManagerMock.getFutureMeetingList(firstContact));
    }

    @Test(expected=IllegalArgumentException.class)
    public void CMTestGetPastMeetingListForIllegalArgumentException() {
        Contact testContact = new ContactImpl(1,"No Doe");
	ContactManagerMock.getPastMeetingListFor(testContact);
    }

    @Test(expected=NullPointerException.class)
    public void CMTestGetPastMeetingListForNullPointerException() {
        Contact testContact = null;
	ContactManagerMock.getPastMeetingListFor(testContact);
    }

    @Test
    public void CMTestGetPastMeetingListForSuccess() {
	assertNotNull(ContactManagerMock.getPastMeetingListFor(firstContact));
    }

    @Test
    public void CMTestGetMeetingListOnSuccess() {
	assertEquals(ContactManagerMock.getMeetingListOn(pastDate).get(0).getId(),pastMeetingId);
    }

    @Test(expected=NullPointerException.class)
    public void CMTestGetMeetingListOnNullPointerException() {
	Calendar todaysDate = new GregorianCalendar();
	ContactManagerMock.getMeetingListOn(todaysDate);
    }

}

