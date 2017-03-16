import java.util.Calendar;
import java.util.Set;

public class PastMeetingImpl extends MeetingImpl {
    String notes;
    public PastMeetingImpl(int id, Calendar date, Set<spec.Contact> contacts) {
	super(id,date,contacts);
    }
    String getNotes() {
	return this.notes;
    }
}
