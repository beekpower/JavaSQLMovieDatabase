package movieDatabase;

import java.sql.*;
import java.util.ArrayList;

public class Database {
	String ip;
	String port;
	String username;
	String password;
	Connection connection;

	public Database(String ip, String port, String username, String password) {
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	public boolean connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://"
					+ ip + ":" + port + "/" + "movies" + "?user="
					+ username + "&password=" + password);
			return true;
		} catch (Exception e) {
			System.err.println("Failed to connect.");
			System.err.println(e.getMessage());
			e.printStackTrace();
			
			return false;
		}
	}

	public void close() {
		try {
			connection.close();
		} catch (Exception e) {
			System.err.println("Failed to close.");
			System.err.println(e.getMessage());
		}

	}
	
	/*
	 * Movies
	 */
	public int insertMovie(Movie movie) {
		
		int movieId = -1;
		try {
			String query = "INSERT INTO movies (title, parental_rating, year, duration, genre, director_id) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, movie.getTitle());
			preparedStatement.setString(2, movie.getParentalRating());
			preparedStatement.setInt(3, movie.getYear());
			preparedStatement.setInt(4, movie.getDuration());
			preparedStatement.setString(5, movie.getGenre());
			preparedStatement.setInt(6, movie.getDirectorId());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			movieId = rs.getInt(1);
			preparedStatement.close();
						
		} catch (Exception e) {
			System.err.println("Failed to insert movie");
			System.err.println(e.getMessage());
		} 
		
		return movieId;
		
	}
	
	public void updateMovie(Movie movie) {
		try {
			String query = "UPDATE movies SET title = ?, parental_rating = ?, year = ?, duration = ?, genre = ?, director_id = ? WHERE movie_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, movie.getTitle());
			preparedStatement.setString(2, movie.getParentalRating());
			preparedStatement.setInt(3, movie.getYear());
			preparedStatement.setInt(4, movie.getDuration());
			preparedStatement.setString(5, movie.getGenre());
			preparedStatement.setInt(6, movie.getDirectorId());
			preparedStatement.setInt(7, movie.getId());
			preparedStatement.executeUpdate(); 
			preparedStatement.close();
		} catch (Exception e) {
			System.err.println("Failed to update movie");
			System.err.println(e.getMessage());
		} 
		
	}
	
	public void deleteMovie(Movie movie) {
		try {
			String query = "DELETE FROM movies WHERE movie_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, movie.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (Exception e) {
			System.err.println("Failed to delete movie");
			System.err.println(e.getMessage());
		} 
		
	}

	public ArrayList<Movie> getMovies() {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			String query = "SELECT * FROM movies INNER JOIN directors ON movies.director_id=directors.director_id";
		    Statement statement = connection.createStatement();
		    ResultSet result = statement.executeQuery(query);
		    
		    while (result.next()) {
		    	movies.add(new Movie(result.getInt("movie_id"), result.getString("title"), result.getString("parental_rating"), result.getInt("year"), result.getInt("duration"), result.getString("genre"), result.getInt("director_id"), result.getString("first_name"), result.getString("last_name")));
		    }
		    		    
		    statement.close();
		 			
		} catch (Exception e) {
			System.err.println("Failed to get movies");
			System.err.println(e.getMessage());
		} 
		
		return movies;
	}
	
	public ArrayList<Movie> getMovies(String search) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			String query = "SELECT * FROM movies INNER JOIN directors ON movies.director_id=directors.director_id WHERE Concat(title, '', year) LIKE ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + search + "%");
			ResultSet result = preparedStatement.executeQuery(); 

		    
		    while (result.next()) {
		    	movies.add(new Movie(result.getInt("movie_id"), result.getString("title"), result.getString("parental_rating"), result.getInt("year"), result.getInt("duration"), result.getString("genre"), result.getInt("director_id"), result.getString("first_name"), result.getString("last_name")));
		    }
		    
			preparedStatement.close();
		 			
		} catch (Exception e) {
			System.err.println("Failed to get movies");
			System.err.println(e.getMessage());
		} 
		
		return movies;
	}
	
	public void updateMovieActors(ArrayList<Actor> actors, int movieId) {
		try {
			String query = "DELETE FROM movie_actors WHERE movie_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, movieId);
			preparedStatement.executeUpdate(); 
			preparedStatement.close();
			
			for (Actor actor: actors) {
				query = "INSERT INTO movie_actors (movie_id, actor_id) VALUES (?, ?)";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, movieId);
				preparedStatement.setInt(2, actor.getId());
				preparedStatement.executeUpdate(); 
				preparedStatement.close();
			}
			
			
		} catch (Exception e) {
			System.err.println("Failed to update actors on movie");
			System.err.println(e.getMessage());
		} 
	}
	
	/*
	 * Actors
	 */
	
	public void insertActor(Actor actor) {
		try {
			String query = "INSERT INTO actors (first_name, last_name, gender) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, actor.getFirstName());
			preparedStatement.setString(2, actor.getLastName());
			preparedStatement.setString(3, actor.getGender());
			preparedStatement.executeUpdate(); 
			preparedStatement.close();
			
		} catch (Exception e) {
			System.err.println("Failed to insert actor");
			System.err.println(e.getMessage());
		} 
	}
	
	public void updateActor(Actor actor) {
		try {
			String query = "UPDATE actors SET first_name = ?, last_name = ?, gender = ? WHERE actor_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, actor.getFirstName());
			preparedStatement.setString(2, actor.getLastName());
			preparedStatement.setString(3, actor.getGender());
			preparedStatement.setInt(4, actor.getId());
			preparedStatement.executeUpdate(); 
			preparedStatement.close();
			
		} catch (Exception e) {
			System.err.println("Failed to update actor");
			System.err.println(e.getMessage());
		} 
	}
	
	
	public void deleteActor(Actor actor) {
		try {
			String query = "DELETE FROM actors WHERE actor_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, actor.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (Exception e) {
			System.err.println("Failed to delete actor");
			System.err.println(e.getMessage());
		} 
	}
	
	public ArrayList<Actor> getActors() {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		try {
			String query = "SELECT * FROM actors";
		    Statement statement = connection.createStatement();
		    ResultSet result = statement.executeQuery(query);
		    
		    
		    while (result.next()) {
		    	actors.add(new Actor(result.getInt("actor_id"), result.getString("first_name"), result.getString("last_name"), result.getString("gender")));		    	
		    }
		    		    
		    statement.close();
			
		} catch (Exception e) {
			System.err.println("Failed to get actors");
			System.err.println(e.getMessage());
		}
		return actors;
	}
	
	public ArrayList<Actor> getActors(int movieId) {
		ArrayList<Integer> actorIds = new ArrayList<Integer>();
		ArrayList<Actor> actors = new ArrayList<Actor>();
		try {
			String query = "SELECT * FROM movie_actors WHERE movie_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, movieId);
			ResultSet result = preparedStatement.executeQuery();
		    
		    while (result.next()) {
		    	actorIds.add(result.getInt("actor_id"));		    	
		    }
		    		    
		    preparedStatement.close();
		    
		    for (Integer actorId: actorIds) {
				query = "SELECT * FROM actors WHERE actor_id = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, actorId);
				result = preparedStatement.executeQuery();
			    result.next();
			    actors.add(new Actor(result.getInt("actor_id"), result.getString("first_name"), result.getString("last_name"), result.getString("gender")));	
			    preparedStatement.close();
		    }
		    			
		} catch (Exception e) {
			System.err.println("Failed to get actors");
			System.err.println(e.getMessage());
		}
		return actors;
	}
	
	public ArrayList<Actor> getActors(String search) {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		try {
			String query = "SELECT * FROM actors WHERE Concat(first_name, ' ', last_name) LIKE ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + search + "%");
			ResultSet result = preparedStatement.executeQuery(); 

		    
			 while (result.next()) {
			    	actors.add(new Actor(result.getInt("actor_id"), result.getString("first_name"), result.getString("last_name"), result.getString("gender")));		    	
			 }
		    
			preparedStatement.close();
		 			
		} catch (Exception e) {
			System.err.println("Failed to get movies");
			System.err.println(e.getMessage());
		} 
		
		return actors;
	}
	
	
	
	/*
	 * Directors
	 */
	
	public void insertDirector(Director director) {
		try {
			String query = "INSERT INTO directors (first_name, last_name, gender) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, director.getFirstName());
			preparedStatement.setString(2, director.getLastName());
			preparedStatement.setString(3, director.getGender());
			preparedStatement.executeUpdate(); 
			
		} catch (Exception e) {
			System.err.println("Failed to insert director");
			System.err.println(e.getMessage());
		}  
	}
	
	public void deleteDirector(Director director) {
		try {
			String query = "DELETE FROM directors WHERE director_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, director.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (Exception e) {
			System.err.println("Failed to delete director");
			System.err.println(e.getMessage());
		} 
	}
	
	public void updateDirector(Director director) {
		try {
			String query = "UPDATE directors SET first_name = ?, last_name = ?, gender = ? WHERE director_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, director.getFirstName());
			preparedStatement.setString(2, director.getLastName());
			preparedStatement.setString(3, director.getGender());
			preparedStatement.setInt(4, director.getId());
			preparedStatement.executeUpdate(); 
			preparedStatement.close();
			
		} catch (Exception e) {
			System.err.println("Failed to update director");
			System.err.println(e.getMessage());
		} 
	}
	
	
	public ArrayList<Director> getDirectors() {
		ArrayList<Director> directors = new ArrayList<Director>();
		try {
			String query = "SELECT * FROM directors";
		    Statement statement = connection.createStatement();
		    ResultSet result = statement.executeQuery(query);
		    
		    while (result.next()) {
		    	directors.add(new Director(result.getInt("director_id"), result.getString("first_name"), result.getString("last_name"), result.getString("gender")));
		    }
		    		    
		    statement.close();
		    
		} catch (Exception e) {
			System.err.println("Failed to get directors");
			System.err.println(e.getMessage());
		}
		return directors;
	}
	
	public ArrayList<Director> getDirectors(String search) {
		ArrayList<Director> directors = new ArrayList<Director>();
		try {
			String query = "SELECT * FROM directors WHERE Concat(first_name, ' ', last_name) LIKE ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + search + "%");
			ResultSet result = preparedStatement.executeQuery(); 

		    while (result.next()) {
		    	directors.add(new Director(result.getInt("director_id"), result.getString("first_name"), result.getString("last_name"), result.getString("gender")));
		    }
		    
			preparedStatement.close();
		 			
		} catch (Exception e) {
			System.err.println("Failed to get movies");
			System.err.println(e.getMessage());
		} 
		
		return directors;
	}
	
	/*
	 * Reviews
	*/
	public ArrayList<Review> getReviews(int movieId) {
		ArrayList<Review> reviews = new ArrayList<Review>();
		try {
			String query = "SELECT * FROM reviews WHERE movie_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, movieId);
			ResultSet result = preparedStatement.executeQuery();
					    
		    while (result.next()) {
		    	reviews.add(new Review(result.getInt("review_id"), result.getInt("movie_id"), result.getString("author"), result.getString("title"), result.getString("review"), result.getTimestamp("timestamp")));
		    }
		    		    
		    preparedStatement.close();
		    
		} catch (Exception e) {
			System.err.println("Failed to get reviews");
			System.err.println(e.getMessage());
		}
		return reviews;
	}
	
	public void deleteReview(Review review) {
		try {
			String query = "DELETE FROM reviews WHERE review_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, review.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (Exception e) {
			System.err.println("Failed to delete review");
			System.err.println(e.getMessage());
		} 
	}
	
	public void insertReview(Review review) {
		try {
			String query = "INSERT INTO reviews (movie_id, author, title, review, timestamp) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, review.getMovieId());
			preparedStatement.setString(2, review.getAuthor());
			preparedStatement.setString(3, review.getTitle());
			preparedStatement.setString(4, review.getReview());
			preparedStatement.setTimestamp(5, review.getTimestamp());
			preparedStatement.executeUpdate(); 
			
		} catch (Exception e) {
			System.err.println("Failed to insert director");
			System.err.println(e.getMessage());
		}  
	}

	
	
		
	

}
