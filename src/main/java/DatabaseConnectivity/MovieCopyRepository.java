package DatabaseConnectivity;

import entity.Movie;
import entity.Movie_copy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieCopyRepository extends DatabaseConnector{
    public List<Movie_copy>  getAvailableMovies() throws SQLException {
        System.out.println("fetching records from movie table");
        ResultSet rs;
        List<Movie_copy> movies= new ArrayList<>();
        String query = "SELECT title,available FROM Movie where available="+"true";
        try(Statement statement=connection.createStatement()){
            rs= statement.executeQuery(query);
            while(rs.next()){
                String movieTitle=  rs.getString("title");
                boolean available=  rs.getBoolean("available");
                movies.add(new Movie_copy(movieTitle,available));
            }
        }
        return movies;
    }


    public void addMovie(String title, boolean availability) throws SQLException {
        System.out.println("inserting data to movie table");
        int rows;
        try (PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO movie_copy(title,available)
                    VALUES (?,?)
                    """)) {
            statement.setString(1,title);
            statement.setBoolean(2,availability);
            rows = statement.executeUpdate();
        }
        System.out.println("rows added: " + rows);
    }
}
