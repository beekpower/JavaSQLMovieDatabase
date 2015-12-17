package movieDatabase;

public class Movie {
	
	int id;
	String title;
	String parentalRating;
	int year;
	int duration;
	String genre;
	int directorId;
	String directorFirstName;
	String directorLastName;

	
	public Movie(int id, String title, String parentalRating, int year, int duration, String genre, int directorId, String directorFirstName, String directorLastName) {
		this.id = id;
		this.title = title;
		this.parentalRating = parentalRating;
		this.year = year;
		this.duration = duration;
		this.genre = genre;
		this.directorId = directorId;
		this.directorFirstName = directorFirstName;
		this.directorLastName = directorLastName;
	}
	
	public Movie(String title, String parentalRating, int year, int duration, String genre, int directorId) {
		this.title = title;
		this.parentalRating = parentalRating;
		this.year = year;
		this.duration = duration;
		this.genre = genre;
		this.directorId = directorId;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getParentalRating() {
		return parentalRating;
	}

	public void setParentalRating(String parentalRating) {
		this.parentalRating = parentalRating;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public int getDirectorId() {
		return directorId;
	}

	public void setDirectorId(int directorId) {
		this.directorId = directorId;
	}
	
    
}
