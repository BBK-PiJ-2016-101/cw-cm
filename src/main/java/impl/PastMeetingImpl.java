import java.util.Calendar;
import java.util.Set;

public class PastMeetingImpl extends MeetingImpl implements spec.PastMeeting {
    String notes;
    public PastMeetingImpl(int id, Calendar date, Set<spec.Contact> contacts, String notes) {
	super(id,date,contacts);
	this.notes = notes;
    }
    public String getNotes() {
	return this.notes;
    }
}
