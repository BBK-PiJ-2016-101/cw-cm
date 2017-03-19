import java.util.Calendar;
import java.util.Set;

public abstract class MeetingImpl implements spec.Meeting {
    int id;
    Calendar date;
    Set<spec.Contact> contacts;

    public MeetingImpl(int id, Calendar date, Set<spec.Contact> contacts) {
	this.id = id;
	this.date = date;
	this.contacts = contacts;
    }

    public int getId() {
	return this.id;
    }

    public Calendar getDate() {
	return this.date;
    }

    public Set<spec.Contact> getContacts() {
	return this.contacts;
    }
}
