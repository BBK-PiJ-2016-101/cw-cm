import java.util.Set;
import java.util.Calendar;

public class FutureMeetingImpl extends MeetingImpl {
    public FutureMeetingImpl(int id, Calendar date, Set<spec.Contact> contacts) {
	super(id,date,contacts);
    }
}
