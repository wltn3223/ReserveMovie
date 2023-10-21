package model;

import java.util.Objects;

public class MovieVO {
    private  String title;
    private  String runTime;
    private  String director;
    private  String country;
    private  String genre;
    private  String releaseDate;

    public MovieVO(String title, String runTime, String director, String country, String genre, String releaseDate) {
        this.title = title;
        this.runTime = runTime;
        this.director = director;
        this.country = country;
        this.genre = genre;
        this.releaseDate = releaseDate;

    }

    public String getTitle() {
        return title;
    }

    public String getRunTime() {
        return runTime;
    }

    public String getDirector() {
        return director;
    }

    public String getCountry() {
        return country;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public boolean equals(Object obj) {
       if(!(obj instanceof MovieVO)){
           return false;
       }
        MovieVO movieVO = (MovieVO) obj;
        return this.title.equals(movieVO.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "MovieVO{" +
                "title='" + title + '\'' +
                ", runTime='" + runTime + '\'' +
                ", director='" + director + '\'' +
                ", country='" + country + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }
}
