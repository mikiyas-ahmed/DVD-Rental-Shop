package DatabaseConnectivity;

import entity.RentedMovie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentedMovieRepository extends  DatabaseConnector {

    public List<RentedMovie> getRentedMovies() throws SQLException {
        System.out.println("fetching records from rented movie table");
        ResultSet rs;
        List<RentedMovie> movies= new ArrayList<>();
        String query = "SELECT * FROM rentedmovie";
        try(Statement statement=connection.createStatement()){
            rs= statement.executeQuery(query);
            rs.next();
            while(rs.next()){
                String userName=  rs.getString("userfirstname");
                String movieTitle=  rs.getString("copiedmovietitle");
                Date rentalDate= rs.getDate("rentaldate");
                Date returnDate= rs.getDate("returndate");
                movies.add(new RentedMovie(rentalDate,returnDate,movieTitle,userName));
            }
        }
        return movies;
    }

    public void addRentedMovie(RentedMovie movie) throws SQLException {
        System.out.println("inserting data to Rented Movie table");
        int rows;
        try (PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO rentedmovie(userfirstname,copiedmovietitle,rentaldate,returndate)
                    VALUES (?,?,?,?)
                    """)) {
            statement.setString(1,movie.getUserFirstName());
            statement.setString(2,movie.getMovieTitle());
            statement.setDate(3, (java.sql.Date) movie.getRentalDate());
            statement.setDate(4, (java.sql.Date) movie.getReturnDate());
            rows = statement.executeUpdate();
        }
        System.out.println("rows added: " + rows);
    }
    public List<RentedMovie> getRentedMoviesByUser(String userName) throws SQLException {
        System.out.println("fetching records from rented movie table");
        ResultSet rs;
        List<RentedMovie> movies= new ArrayList<>();
        String query = "SELECT * FROM rentedmovie WHERE userfirstname like "+ userName;
        try(Statement statement=connection.createStatement()){
            rs= statement.executeQuery(query);
            rs.next();
            while(rs.next()){
                String movieTitle=  rs.getString("copiedmovietitle");
                Date rentalDate= rs.getDate("rentaldate");
                Date returnDate= rs.getDate("returndate");
                movies.add(new RentedMovie(rentalDate,returnDate,movieTitle,userName));
            }
        }
        return movies;
    }
    public void removeRentedMovie(String userName,String movieTitle ) throws SQLException{
        System.out.println("Deleting records from Rented Movie table");
        String query = "DELETE FROM rentedmovie where firstName like "+ userName +" and copiedmovietitle like "+movieTitle;
        try(Statement statement=connection.createStatement()){
            statement.executeQuery(query);
        }
    }
    public void removeRentedMovieList(String movieTitle ) throws SQLException{
        System.out.println("Deleting records from Rented Movie table");
        String query = "DELETE FROM rentedmovie where copiedmovietitle like "+ movieTitle;
        try(Statement statement=connection.createStatement()){
            statement.executeQuery(query);
        }
    }

}
