package movieDatabase;

import java.sql.*;

public class Review {
	
	int id;
	int movieId;
	String author;
	String title;
	String review;
	Timestamp timestamp;
	
	public Review(int id, int movieId, String author, String title, String review, Timestamp timestamp) {
		this.id = id;
		this.movieId = movieId;
		this.author = author;
		this.title = title;
		this.review = review;
		this.timestamp = timestamp;
		
	}
	
	public Review(int movieId, String author, String title, String review, Timestamp timestamp) {

		this.movieId = movieId;
		this.author = author;
		this.title = title;
		this.review = review;
		this.timestamp = timestamp;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	
	

}
