import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * My implementation of a class to manage your contacts and meetings.
 */
public class ContactManagerImpl implements spec.ContactManager {
    List<FutureMeeting> futureMeetings = new ArrayList<FutureMeeting>();
    List<PastMeeting> pastMeetings = new ArrayList<FutureMeeting>();
    
  /**
   * Adds a new meeting to be held in the future.
   *
   * <p>An ID is returned when the meeting is put into the system. This
   * ID must be positive and non-zero.</p>
   *
   * @param contacts a set of contacts that will participate in the meeting
   * @param date   the date on which the meeting will take place
   * @return the ID for the meeting
   * @throws IllegalArgumentException if the meeting is set for a time
   *                  in the past, of if any contact is unknown / non-existent.
   * @throws NullPointerException   if the meeting or the date are null
   */
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
	futureMeetings.add(new FutureMeetingImpl(futureMeetings.size(),date,contact));
    }

  /**
   * Returns the PAST meeting with the requested ID, or null if it there is none.
   *
   * <p>The meeting must have happened at a past date.</p>
   *
   * @param id the ID for the meeting
   * @return the meeting with the requested ID, or null if it there is none.
   * @throws IllegalStateException if there is a meeting with that ID happening
   *                 in the future
   */
    public PastMeeting getPastMeeting(int id) {
	if (pastMeeetings.get(id) != null) {
	    return pastMeetings.get(id);
	}
	else if (futureMeetings.get(id) != null) {
	    throw IllegalStateException;
	}
	else {
	    return null;
	}
    }

  /**
   * Returns the FUTURE meeting with the requested ID, or null if there is none.
   *
   * @param id the ID for the meeting
   * @return the meeting with the requested ID, or null if it there is none.
   * @throws IllegalStateException if there is a meeting with that ID happening
   *                 in the past
   */
    public FutureMeeting getFutureMeeting(int id) {
	if (futureMeeetings.get(id) != null) {
	    return futureMeetings.get(id);
	}
	else if (pastMeetings.get(id) != null) {
	    throw IllegalStateException;
	}
	else {
	    return null;
	}
    }

  /**
   * Returns the meeting with the requested ID, or null if it there is none.
   *
   * @param id the ID for the meeting
   * @return the meeting with the requested ID, or null if it there is none.
   */
    Meeting getMeeting(int id) {
	if (futureMeeetings.get(id) != null) {
	    return futureMeetings.get(id);
	}
	else if (pastMeetings.get(id) != null) {
	    return futureMeetings.get(id);
	}
	else {
	    return null;
	}
    }

  /**
   * Returns the list of future meetings scheduled with this contact.
   *
   * <p>If there are none, the returned list will be empty. Otherwise,
   * the list will be chronologically sorted and will not contain any
   * duplicates.</p>
   *
   * @param contact one of the user’s contacts
   * @return the list of future meeting(s) scheduled with this contact (maybe empty).
   * @throws IllegalArgumentException if the contact does not exist
   * @throws NullPointerException   if the contact is null
   */

  /**
   * Iterate futureMeetings and build a list of items where the meeting's contacts contains this contact
   */
    public List<Meeting> getFutureMeetingList(Contact contact) {
	List list = new ArrayList<FutureMeeting>();
	for (FutureMeeting futureMeeting : futureMeetings) {
	    if (futureMeeting.getContacts().contains(contact)) {
		return list.add(futureMeeting);
	    }
	}
    }

  /**
   * Returns the list of meetings that are scheduled for, or that took
   * place on, the specified date.
   *
   * <p>If there are none, the returned list will be empty. Otherwise,
   * the list will be chronologically sorted and will not contain any
   * duplicates.</p>
   *
   * @param date the date
   * @return the list of meetings
   * @throws NullPointerException if the date are null
   */

  /**
   * Iterate futureMeetings then pastMeetings and build a list of items where the meeting's date is equal to date
   */
    public List<Meeting> getMeetingListOn(Calendar date) {
	List list = new ArrayList<Meeting>();
	for (FutureMeeting futureMeeting : futureMeetings) {
	    if (futureMeeting.getDate().compareTo(date) == 0) {
		list.add(futureMeeting);
	    }
	}
	for (PastMeeting pastMeeting : pastMeetings) {
	    if (pastMeeting.getDate().compareTo(date) == 0) {
		list.add(pastMeeting);
	    }
	}
	return list;
    }


  /**
   * Returns the list of past meetings in which this contact has participated.
   *
   * <p>If there are none, the returned list will be empty. Otherwise,
   * the list will be chronologically sorted and will not contain any
   * duplicates.</p>
   *
   * @param contact one of the user’s contacts
   * @return the list of future meeting(s) scheduled with this contact (maybe empty).
   * @throws IllegalArgumentException if the contact does not exist
   * @throws NullPointerException   if the contact is null
   */

  /**
   * Iterate futureMeetings and build a list of items where the meeting's contacts contains this contact
   */    
    List<PastMeeting> getPastMeetingListFor(Contact contact) {
	List list = new ArrayList<PastMeeting>();
	for (PastMeeting pastMeeting : pastMeetings) {
	    if (pastMeeting.getContacts().contains(contact)) {
		return list.add(pastMeeting);
	    }
	}
    }

  /**
   * Create a new record for a meeting that took place in the past.
   *
   * @param contacts a set of participants
   * @param date   the date on which the meeting took place
   * @param text   messages to be added about the meeting.
   * @return the ID for the meeting
   * @throws IllegalArgumentException if the list of contacts is
   *                  empty, if any of the contacts does not exist, or if
   *                  the date provided is in the future
   * @throws NullPointerException   if any of the arguments is null
   */
    public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
	pastMeetings.add(new PastMeetingImpl(pastMeetings.size(),date,contact));
    }

  /**
   * Add notes to a meeting.
   *
   * <p>This method is used when a future meeting takes place, and is
   * then converted to a past meeting (with notes) and returned.</p>
   *
   * <p>It can be also used to add notes to a past meeting at a later date.</p>
   *
   * @param id   the ID of the meeting
   * @param text messages to be added about the meeting.
   * @throws IllegalArgumentException if the meeting does not exist
   * @throws IllegalStateException  if the meeting is set for a date in the future
   * @throws NullPointerException   if the notes are null
   */
    public PastMeeting addMeetingNotes(int id, String text) {
	
	PastMeeting pastMeeting = new PastMeeting(pastMeetings.size(),
	futureMeetings.remove(id)
    }

  /**
   * Create a new contact with the specified name and notes.
   *
   * @param name  the name of the contact.
   * @param notes notes to be added about the contact.
   * @return the ID for the new contact
   * @throws IllegalArgumentException if the name or the notes are empty strings
   * @throws NullPointerException   if the name or the notes are null
   */
  int addNewContact(String name, String notes);

  /**
   * Returns a set with the contacts whose name contains that string.
   *
   * <p>If the string is the empty string, this methods returns the set
   * that contains all current contacts.</p>
   *
   * @param name the string to search for
   * @return a set with the contacts whose name contains that string.
   * @throws NullPointerException if the parameter is null
   */
  Set<Contact> getContacts(String name);

  /**
   * Returns a set containing the contacts that correspond to the IDs.
   * Note that this method can be used to retrieve just one contact by passing only one ID.
   *
   * @param ids an arbitrary number of contact IDs
   * @return a set containing the contacts that correspond to the IDs.
   * @throws IllegalArgumentException if no IDs are provided or if
   *                  any of the provided IDs does not correspond to a real contact
   */
  Set<Contact> getContacts(int... ids);

  /**
   * Save all data to disk.
   *
   * <p>This method must be executed when the program is
   * closed and when/if the user requests it.</p>
   */
  void flush();
}
