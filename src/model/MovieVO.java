package model;

import java.util.Objects;

public class MovieVO {
	private String movieTitle;
	private String movieGenre;
	private String movieRunTime;
	private String movieDirector;
	private String movieConutry;
	private String movieReleaseDate;
	
	
	
	



	public MovieVO(String movieTitle, String movieGenre, String movieRunTime, String movieDirector, String movieConutry,
			String movieReleaseDate) {
		super();
		this.movieTitle = movieTitle;
		this.movieGenre = movieGenre;
		this.movieRunTime = movieRunTime;
		this.movieDirector = movieDirector;
		this.movieConutry = movieConutry;
		this.movieReleaseDate = movieReleaseDate;
	}



	public String getMovieTitle() {
		return movieTitle;
	}



	public String getMovieGenre() {
		return movieGenre;
	}



	public String getMovieRunTime() {
		return movieRunTime;
	}



	public String getMovieDirector() {
		return movieDirector;
	}



	public String getMovieConutry() {
		return movieConutry;
	}



	public String getMovieReleaseDate() {
		return movieReleaseDate;
	}



	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(movieTitle);
	}



	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MovieVO)) {
			return false;
		}
		MovieVO other = (MovieVO) obj;
		return this.movieTitle.equals(other.getMovieTitle());
	}
	
	
	

}
