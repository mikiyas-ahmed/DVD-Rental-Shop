package DatabaseConnectivity;

import entity.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository extends DatabaseConnector {

    public void addMovie(Movie movie) throws SQLException {
            System.out.println("inserting data to movie table");
            int rows;
            try (PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO client(title,price,releasedyear)
                    VALUES (?,?,?)
                    """)) {
                statement.setString(1, movie.getTitle());
                statement.setInt(2,movie.getPrice());
                statement.setDate(3, (Date) movie.getReleasedYear());
                rows = statement.executeUpdate();
            }
            System.out.println("rows added: " + rows);
    }


    public void removeMovie(String movieTitle) throws SQLException{
        System.out.println("Deleting records from movie table");
        int rows;
        try (PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM Movie
                    where title like ?
                    """)) {
            statement.setString(1, movieTitle);
            rows = statement.executeUpdate();
        }
        System.out.println("rows deleted: " + rows);
    }


    public Movie getMovie( String title) throws SQLException {
        System.out.println("fetching records from movie table");
        ResultSet rs;
        String query = "SELECT title, price, releasedyear FROM Movie where title like "+title;
        try(Statement statement=connection.createStatement()){
            rs= statement.executeQuery(query);
            rs.next();
                String movieTitle=  rs.getString("title");
                int price = rs.getInt("price");
                Date releasedYear= rs.getDate("releasedyear");
            return new Movie(movieTitle,releasedYear,price);
        }
    }


    public List<Movie> getMovieList() throws SQLException {
        System.out.println("fetching records from movie table");
        ResultSet rs;
        List<Movie> movies= new ArrayList<>();
        String query = "SELECT title, price, releasedyear FROM Movie";
        try (Statement statement = connection.createStatement()) {
            rs= statement.executeQuery(query);
            while(rs.next()){
                String movieTitle=  rs.getString("title");
                int price = rs.getInt("price");
                Date releasedYear= rs.getDate("releasedyear");
                movies.add(new Movie(movieTitle,releasedYear,price));
            }
        }
        return movies;
    }

}
