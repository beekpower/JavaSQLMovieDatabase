package movieDatabase;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		Database database = new Database("localhost", "3306", "root", "root");
		
		
		database.connect();
		//database.insertDirector(new Director("asdfdafd", "asdfa", "M"));
//		database.insertDirector(new Director("Nick", "VanBeek", "M"));
//		database.insertDirector(new Director("Andrew", "Strelke", "M"));
//		database.insertDirector(new Director("Jacob", "Gay", "M"));
//		database.insertDirector(new Director("Alec", "VanBeek", "M"));
//		database.insertMovie(new Movie("Steve Jobs", "PG", 2010, 20, "comedy", 2));
//		database.insertMovie(new Movie("Steve Blobs", "PG", 2010, 20, "comedy", 2));
//		database.insertMovie(new Movie("The Conjuring", "PG", 2010, 20, "comedy", 2));
//		database.insertMovie(new Movie("American Pie", "PG", 2010, 20, "comedy", 2));
//		database.insertMovie(new Movie("Dark Night", "PG", 2010, 20, "comedy", 2));
//		database.insertMovie(new Movie("Scooby Doo", "PG", 2010, 20, "comedy", 2));
		
		ArrayList<Director> directors = database.getDirectors("Andrew");
		
		for (Director director: directors) {
			System.out.println(director.getFirstName() + " " + director.getLastName());
		}
	}
}
