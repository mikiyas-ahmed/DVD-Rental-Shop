package entity;

import java.util.Date;

public class RentedMovie {
    private Date rentalDate;
    private Date returnDate;
    private String movieTitle;
    private String userFirstName;

    public RentedMovie(Date rentalDate, Date returnDate, String movieTitle, String userFirstName) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.movieTitle = movieTitle;
        this.userFirstName = userFirstName;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getUserFirstName() { return userFirstName; }

    public void setUserFirstName(String userFirstName) { this.userFirstName = userFirstName; }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
}
