import spec.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * My implementation of a class to manage your contacts and meetings.
 */
public class ContactManagerImpl implements ContactManager {
    List<FutureMeeting> futureMeetingsList = new ArrayList<FutureMeeting>();
    List<PastMeeting> pastMeetingsList = new ArrayList<PastMeeting>();
    List<Contact> contactsList = new ArrayList<Contact>();
    
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
	if (contacts == null || date == null) {
	    throw new NullPointerException();
	}
	if (date.compareTo(new GregorianCalendar()) < 0) {
	    throw new IllegalStateException();
	}
	int futureMeetingId = futureMeetingsList.size();
	futureMeetingsList.add(new FutureMeetingImpl(futureMeetingId,date,contacts));
	return futureMeetingId;
    }

    /**
    
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
	try {
	    return pastMeetingsList.get(id);
	} catch(IndexOutOfBoundsException noPastMeetings) {
	    try {
		if (futureMeetingsList.get(id) != null) {
		    throw new IllegalStateException();
		}
	    } catch(IndexOutOfBoundsException noFutureMeetings) {
		return null;
	    }
	}
	return null;
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
	try {
	    return futureMeetingsList.get(id);
	} catch(IndexOutOfBoundsException noFutureMeetings) {
	    try {
		if (pastMeetingsList.get(id) != null) {
		    throw new IllegalStateException();
		}
	    } catch(IndexOutOfBoundsException noPastMeetings) {
		return null;
	    }
	}
	return null;
    }


  /**
   * Returns the meeting with the requested ID, or null if it there is none.
   *
   * @param id the ID for the meeting
   * @return the meeting with the requested ID, or null if it there is none.
   */
    public Meeting getMeeting(int id) {
	try { 
	    if (futureMeetingsList.get(id) != null) {
		return futureMeetingsList.get(id);
	    }
	} catch(IndexOutOfBoundsException noFutureMeetings) {
	    try {
		if (pastMeetingsList.get(id) != null) {
		    return pastMeetingsList.get(id);
		}
	    } catch (IndexOutOfBoundsException noPastMeetings) {
		return null;
	    }
	}
	return null;
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
   * Iterate futureMeetingsList and build a list of items where the meeting's contacts contains this contact
   */

    public List<Meeting> getFutureMeetingList(Contact contact) {
	List list = new ArrayList<FutureMeeting>();
	if (!contactsList.contains(contact)) {
	    throw new IllegalArgumentException();
	}
	if (contact == null) {
	    throw new NullPointerException();
	}
		for (FutureMeeting futureMeeting : futureMeetingsList) {
	    if (futureMeeting.getContacts().contains(contact)) {
		list.add(futureMeeting);
	    }
	}
	return list;
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
   * Iterate futureMeetingsList then pastMeetingsList and build a list of items where the meeting's date is equal to date
   */
    
    public List<Meeting> getMeetingListOn(Calendar date) {
	if (date == null) {
	    throw new NullPointerException();
	}
	List list = new ArrayList<Meeting>();
	for (FutureMeeting futureMeeting : futureMeetingsList) {
	    if (futureMeeting.getDate().compareTo(date) == 0) {
		list.add(futureMeeting);
	    }
	}
	for (PastMeeting thisPastMeeting : pastMeetingsList) {
	    if (thisPastMeeting.getDate().compareTo(date) == 0) {
		list.add(thisPastMeeting);
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
   * Iterate futureMeetingsList and build a list of items where the meeting's contacts contains this contact
   */
    
    public List<PastMeeting> getPastMeetingListFor(Contact contact) {
	List list = new ArrayList<PastMeeting>();
	if (!contactsList.contains(contact)) {
	    throw new IllegalArgumentException();
	}
	if (contact == null) {
	    throw new NullPointerException();
	}
		for (PastMeeting pastMeeting : pastMeetingsList) {
	    if (pastMeeting.getContacts().contains(contact)) {
		list.add(pastMeeting);
	    }
	}
	return list;
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

    /**
     * Check what the next index wil be with size(), use the ArrayList index as the id and return tat id to the user
     */
    public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
	int pastMeetingsListId = pastMeetingsList.size();
	pastMeetingsList.add(new PastMeetingImpl(pastMeetingsList.size(),date,contacts,text));
	return pastMeetingsListId;
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
	Calendar todaysDate = new GregorianCalendar();
	FutureMeeting thisMeeting = null;
	int pastMeetingId;
	try {
	    if (futureMeetingsList.get(id) != null) {
		thisMeeting = futureMeetingsList.get(id);
		if (thisMeeting.getDate().compareTo(todaysDate) > 0) {
		    throw new IllegalStateException();
		}
	    }

	} catch (IndexOutOfBoundsException noFutureMeeting) {
	    throw new IllegalArgumentException();
	}
	if (text != null) {
	    pastMeetingId = addNewPastMeeting(thisMeeting.getContacts(),thisMeeting.getDate(),text);
	    futureMeetingsList.remove(id);
	} else {
	    throw new NullPointerException();
	}
	return getPastMeeting(pastMeetingId);
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
    public int addNewContact(String name, String notes) {
	if (name == "" || notes == "") {
	    throw new IllegalArgumentException();
	}
	if (name == null || notes == null) {
	    throw new NullPointerException();
	}
	int contactId = contactsList.size();
	Contact newContact = new ContactImpl(contactId, name, notes);
	contactsList.add(newContact);
	return contactId;
    }
    

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
    public Set<Contact> getContacts(String name) {
	 Set<Contact> returnGetContacts = new HashSet<Contact>();
	 for (Contact contact : contactsList) {
	     if (contact.getName == name) {
		 returnGetContacts.add(contact);
	     }
	 }
	 return returnGetContacts;
    }

  /**
   * Returns a set containing the contacts that correspond to the IDs.
   * Note that this method can be used to retrieve just one contact by passing only one ID.
   *
   * @param ids an arbitrary number of contact IDs
   * @return a set containing the contacts that correspond to the IDs.
   * @throws IllegalArgumentException if no IDs are provided or if
   *                  any of the provided IDs does not correspond to a real contact
   */
  public Set<Contact> getContacts(int... ids) {
      Set<Contact> returnGetContacts = new HashSet<Contact>();
      	 for (Contact contact : contactsList) {
	     for (int id : ids) {
		 if (contact.getId() == id) {
		     returnGetContacts.add(contact);
		 }
	     }
	 }
      return returnGetContacts;
  }


  /**
   * Save all data to disk.
   *
   * <p>This method must be executed when the program is
   * closed and when/if the user requests it.</p>
   */
  public void flush() {
  }
}
