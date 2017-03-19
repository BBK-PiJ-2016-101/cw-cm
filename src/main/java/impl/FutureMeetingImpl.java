import java.util.Set;
import java.util.Calendar;

public class FutureMeetingImpl extends MeetingImpl implements spec.FutureMeeting {
    public FutureMeetingImpl(int id, Calendar date, Set<spec.Contact> contacts) {
	super(id,date,contacts);
    }
}
