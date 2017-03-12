public class Contact {
    int id;
    String name;
    String notes;

    public Contact(int id, String name) {
	this.id = id;
	this.name = name;
	this.notes = "";
    }
    
    public Contact(int id, String name, String notes) {
	this.id = id;
	this.name = name;
	this.notes = notes;
    }

    public int getId() {
	return this.id;
    }

    public String getName() {
	return this.name;
    }

    public String getNotes() {
	return this.notes;
    }

    public void addNotes(String note) {
	this.notes = this.notes + "\n" + note;
    }
}
