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
    Calendar  todaysDate = new GregorianCalendar();
    Set<Contact> testContacts = new HashSet<spec.Contact>();
    ContactImpl firstContact = new ContactImpl(1,"John Doe", "Unidentified male");
    ContactImpl secondContact = new ContactImpl(2,"Jane Doe", "Unidentified female");
    String testNotes = "These are test notes";
    ContactManager ContactManagerMock;
    int todaysMeetingId;
    int futureMeetingId;
    int pastMeetingId;
    int existingContactId;

    @Before
    public void testSetup() {
    this.pastDate.set(2017,01,01,01,01);
    this.futureDate.set(3017,01,01,01,01);
    this.testContacts.add(firstContact);
    this.ContactManagerMock = new ContactManagerImpl();
    this.existingContactId = ContactManagerMock.addNewContact(firstContact.getName(),firstContact.getNotes());
    this.futureMeetingId = ContactManagerMock.addFutureMeeting(testContacts,futureDate);
    this.todaysMeetingId = ContactManagerMock.addFutureMeeting(testContacts,todaysDate);
    this.pastMeetingId = ContactManagerMock.addNewPastMeeting(testContacts,pastDate,testNotes);
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
	assertNotNull(ContactManagerMock.addNewPastMeeting(testContacts,pastDate,testNotes));
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

    @Test(expected=IllegalArgumentException.class)
    public void CMTestAddMeetingNotesIllegalArgumentException() {
	ContactManagerMock.addMeetingNotes(futureMeetingId*2,"CMTestAddMeetingNotesIllegalArgumentException");
    }

    @Test(expected=IllegalStateException.class)
    public void CMTestAddMeetingNotesIllegalStateException() {
	ContactManagerMock.addMeetingNotes(futureMeetingId,"CMTestAddMeetingNotesIllegalStateException");
    }

    @Test(expected=NullPointerException.class)
    public void CMTestAddMeetingNotesNullPointerException() {
	String text = null;
	ContactManagerMock.addMeetingNotes(todaysMeetingId, text);
    }

    @Test
    public void CMTestAddMeetingNotesSuccessful() {
	PastMeeting hadMeeting = ContactManagerMock.addMeetingNotes(todaysMeetingId, "CMTestAddMeetingNotesSuccessful");
	assertNotNull(hadMeeting);
    }

    @Test(expected=IllegalArgumentException.class)
    public void CMTestAddNewContactIllegalArgumentException1() {
	String name = "";
	String notes = "CMTestAddNewConatactIllegalArgumentException1";
	int contactId = ContactManagerMock.addNewContact(name,notes);
    }

    @Test(expected=IllegalArgumentException.class)
    public void CMTestAddNewContactIllegalArgumentException2() {
	String name = "CMTestAddNewContactIllegalArgumentException2";
	String notes = "";
	int contactId = ContactManagerMock.addNewContact(name,notes);
    }

    @Test(expected=NullPointerException.class)
    public void CMTestAddNewContactNullPointerException1() {
	String name = null;
	String notes = "CMTestAddNewContactNullPointerException1";
	int contactId = ContactManagerMock.addNewContact(name,notes);
    }

    @Test(expected=NullPointerException.class)
    public void CMTestAddNewContactNullPointerException2() {
	String name = null;
	String notes = "CMTestAddNewContactNullPointerException2";
	int contactId = ContactManagerMock.addNewContact(name,notes);
    }

    @Test
    public void CMTestAddNewContactSucessful() {
	int testContactId = ContactManagerMock.addNewContact("CMTestAddNewContactSucessful","CMTestAddNewContactSucessful");
    }

    @Test(expected=NullPointerException.class)
    public void CMTestGetContacts1NullPointerException() {
	String testName = null;
	ContactManagerMock.getContacts(testName);
    }

    @Test
    public void CMTestGetContacts1Successful() {
	Set<Contact> testContacts = new HashSet<Contact>();
	testContacts = ContactManagerMock.getContacts("John Doe");
	for (Contact testContact : testContacts) {
	    if (testContact.getName() == "John Doe"){
		assertEquals(testContact.getId(),existingContactId);
	    }
	}
    }

    @Test(expected=IllegalArgumentException.class)
    public void	CMTestGetContacts2IllegalArgumentException1() {
	int[] ids = {};
	ContactManagerMock.getContacts(ids);
    }

    @Test(expected=IllegalArgumentException.class)
    public void	CMTestGetContacts2IllegalArgumentException2() {
	int notExistingContactId = existingContactId * 2;
	int[] ids = {notExistingContactId};
	ContactManagerMock.getContacts(ids);
    }

    @Test
    public void CMTestGetContacts2Successful() {
	Set<Contact> testContacts = new HashSet<Contact>();
	testContacts = ContactManagerMock.getContacts(1);
	for (Contact testContact : testContacts) {
	    if (testContact.getId() == 1){
		assertEquals(testContact.getName(),"John Doe");
	    }
	}
    }
}
