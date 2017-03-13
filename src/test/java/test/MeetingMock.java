import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MeetingMock extends MeetingImpl {
    public MeetingMock(int id, Calendar date, Set<spec.Contact> contacts) {
	super(id, date, contacts);
    }
}
