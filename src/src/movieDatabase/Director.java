package movieDatabase;

public class Director {
	
	int id;
	String firstName;
	String lastName;
	String gender;

	public Director(int id, String firstName, String lastName, String gender) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}
	
	public Director(String firstName, String lastName, String gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
