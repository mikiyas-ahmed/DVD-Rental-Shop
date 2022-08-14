package entity;

import java.util.Date;
import java.util.List;

public class Client {
    private long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private List<RentedMovie> rentedMovies;

    public Client(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<RentedMovie> getRentedMovies() {
        return rentedMovies;
    }

    public void setRentedMovies(List<RentedMovie> rentedMovies) {
        this.rentedMovies = rentedMovies;
    }
}
