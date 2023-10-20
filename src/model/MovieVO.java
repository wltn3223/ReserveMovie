package model;

import java.util.Objects;

public class MovieVO {
	String title;
	String genre;
	String runTime;
	String director;
	String conutry;
	String releaseDate;
	public MovieVO(String title, String genre, String runTime, String director, String conutry, String releaseDate) {
		super();
		this.title = title;
		this.genre = genre;
		this.runTime = runTime;
		this.director = director;
		this.conutry = conutry;
		this.releaseDate = releaseDate;
	}
	public String getTitle() {
		return title;
	}
	public String getGenre() {
		return genre;
	}
	public String getRunTime() {
		return runTime;
	}
	public String getDirector() {
		return director;
	}
	public String getConutry() {
		return conutry;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(conutry, director, genre, releaseDate, runTime, title);
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MovieVO)) {
			return false;
		}
		MovieVO other = (MovieVO) obj;
		return this.title.equals(other.title);
	}
	
	
	

}
