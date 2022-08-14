package entity;

import java.util.Date;
import java.util.List;

public class Movie {
    private long movieId;
    private String title;
    private Date releasedYear;
    private int price;
    private List<Actor> cast;

    public Movie( String title, Date releasedYear,  int price) {
        this.title = title;
        this.releasedYear = releasedYear;
        this.price = price;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(Date releasedYear) {
        this.releasedYear = releasedYear;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public void setCast(List<Actor> cast) {
        this.cast = cast;
    }
}
