package model;

import java.util.Objects;

public class MovieVO {
	private String Movie_title;
	private String Movie_genre;
	private String Movie_runTime;
	private String Movie_director;
	private String Movie_conutry;
	private String Movie_releaseDate;
	
	
	
	public MovieVO(String movie_title, String movie_genre, String movie_runTime, String movie_director,
			String movie_conutry, String movie_releaseDate) {
		super();
		Movie_title = movie_title;
		Movie_genre = movie_genre;
		Movie_runTime = movie_runTime;
		Movie_director = movie_director;
		Movie_conutry = movie_conutry;
		Movie_releaseDate = movie_releaseDate;
	}



	public String getMovie_title() {
		return Movie_title;
	}



	public String getMovie_genre() {
		return Movie_genre;
	}



	public String getMovie_runTime() {
		return Movie_runTime;
	}



	public String getMovie_director() {
		return Movie_director;
	}



	public String getMovie_conutry() {
		return Movie_conutry;
	}



	public String getMovie_releaseDate() {
		return Movie_releaseDate;
	}



	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(Movie_title);
	}



	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MovieVO)) {
			return false;
		}
		MovieVO other = (MovieVO) obj;
		return this.Movie_title.equals(other.Movie_title);
	}
	
	
	

}
