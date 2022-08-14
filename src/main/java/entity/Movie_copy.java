package entity;

public class Movie_copy {
    private String copiedMovieTitle;
    private boolean available;

    public Movie_copy(String copiedMovieTitle, boolean available) {
        this.copiedMovieTitle = copiedMovieTitle;
        this.available = available;
    }

    public String getCopiedMovieTitle() {
        return copiedMovieTitle;
    }

    public void setCopiedMovieTitle(String copiedMovieTitle) {
        this.copiedMovieTitle = copiedMovieTitle;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
