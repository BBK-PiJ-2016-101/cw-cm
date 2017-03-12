import org.junit.*;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.Calendar;

public class MeetingTest {
    MeetingImpl testMeeting;

    @Before
    public void testSetup() {
	Calendar testCalendar = new Calendar();
	testCalendar.set(2017,03,19,23,59);
	Set<spec.Contact> testContacts = new Set<spec.Contact>();
	ContactImpl firstContact = new ContactImpl(1,"John Doe", "Unidentified male");
	ContactImpl secondContact = new ContactImpl(2,"Jane Doe", "Unidentified female");
	MeetingImpl testMeeting = new MeetingImpl(1,testCalendar,testContacts);
    }
}
