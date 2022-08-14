package services;

import DatabaseConnectivity.RentedMovieRepository;
import DatabaseConnectivity.UserRepository;
import entity.Client;
import entity.RentedMovie;
import java.sql.SQLException;
import java.util.List;

public class UserManager {

    RentedMovieRepository rentedMovieRepo;
    UserRepository userRepo;
    public UserManager() {
        this.rentedMovieRepo = new RentedMovieRepository();
        this.userRepo = new UserRepository();
    }

    public void addUser(Client client) throws SQLException{
        try {
            userRepo.openDatabaseConnection();
            userRepo.addUser(client);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            userRepo.closeDatabaseConnection();
        }
    }
    public void removeUser(String clientName) throws SQLException{
        try {
            userRepo.openDatabaseConnection();
            userRepo.removeUser(clientName);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            userRepo.closeDatabaseConnection();
        }
    }
    public Client getUser(String firstName)throws SQLException{
        try {
            userRepo.openDatabaseConnection();
            return userRepo.getUser(firstName);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            userRepo.closeDatabaseConnection();
        }
        return null;
    }
    public List<Client> getUserList()throws SQLException{
        try {
            userRepo.openDatabaseConnection();
            return userRepo.getUserList();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            userRepo.closeDatabaseConnection();
        }
        return null;
    }
    public List<RentedMovie> getRentedMovies() throws SQLException {
        try {
            rentedMovieRepo.openDatabaseConnection();
            return rentedMovieRepo.getRentedMovies();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            rentedMovieRepo.closeDatabaseConnection();
        }
        return null;
    }
    public void addRentedMovie(RentedMovie rentedMovie) throws SQLException {
        try {
            rentedMovieRepo.openDatabaseConnection();
            rentedMovieRepo.addRentedMovie(rentedMovie);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            rentedMovieRepo.closeDatabaseConnection();
        }
    }
    public List<RentedMovie> getRentedMoviesByUser(String userName) throws SQLException {

        try {
            rentedMovieRepo.openDatabaseConnection();
            return rentedMovieRepo.getRentedMoviesByUser(userName);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            rentedMovieRepo.closeDatabaseConnection();
        }
        return null;
    }
    public void removeRentedMovie(String userName,String movieTitle ) throws SQLException{
        try {
            rentedMovieRepo.openDatabaseConnection();
            rentedMovieRepo.removeRentedMovie(userName,movieTitle);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            rentedMovieRepo.closeDatabaseConnection();
        }
    }
    public void removeRentedMovieList(String movieTitle ) throws SQLException{
        try {
            rentedMovieRepo.openDatabaseConnection();
            rentedMovieRepo.removeRentedMovieList(movieTitle);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            rentedMovieRepo.closeDatabaseConnection();
        }
    }
}
