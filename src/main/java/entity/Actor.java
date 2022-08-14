package entity;


public class Actor {
    private String firstName;
    private String lastName;
    private String movieTitle;

    public Actor(String firstName, String lastName, String movieTitle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.movieTitle = movieTitle;
    }
    public String getMovieTitle() { return movieTitle; }

    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
