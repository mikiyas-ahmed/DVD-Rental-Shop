package DatabaseConnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnector {
  static Connection connection;
     public void openDatabaseConnection() throws SQLException {
        System.out.println("Connecting to the database");
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/DVD_Rental",
                "postgres","mi1106ki");
    }
    public void closeDatabaseConnection() throws SQLException {
        System.out.println("Closing database Connection");
        connection.close();
    }
    public void createAllTablesIfNotExist() throws SQLException {
       PreparedStatement statement = connection.prepareStatement("""
                    CREATE TABLE IF NOT EXISTS Client (
                       id serial PRIMARY KEY,
                       firstName VARCHAR ( 50 ) UNIQUE NOT NULL,
                       lastName VARCHAR ( 50 )  NOT NULL,
                       birthDate DATE NOT NULL);
                       
                    CREATE TABLE IF NOT EXISTS Movie (
                       movieId serial PRIMARY KEY,
                       title VARCHAR ( 50 ) UNIQUE NOT NULL,
                       price INT NOT NULL,
                       releasedYear DATE NOT NULL);
                       
                    CREATE TABLE IF NOT EXISTS Movie_copy (
                       copiedMovieId serial PRIMARY KEY,
                       available BOOLEAN UNIQUE NOT NULL,
                       movieId int NOT NULL,
                         CONSTRAINT fk_movie
                          FOREIGN KEY ( movieId )
                           REFERENCES  Movie ( movieId ));
                           
                    CREATE TABLE IF NOT EXISTS Actor (
                        actorId serial PRIMARY KEY,
                       firstName VARCHAR ( 50 ) UNIQUE NOT NULL,
                       lastName VARCHAR ( 50 )  NOT NULL,
                       birthDate DATE NOT NULL,
                       movieId int NOT NULL,
                         CONSTRAINT fk_movie
                          FOREIGN KEY ( movieId )
                           REFERENCES  Movie ( movieId ));
                           
                    CREATE TABLE IF NOT EXISTS RentedMovie (
                       rentalDate DATE NOT NULL,
                       returnDate DATE NOT NULL,
                       copiedMovieId int NOT NULL,
                         CONSTRAINT fk_Movie_copy
                          FOREIGN KEY ( copiedMovieId )
                           REFERENCES  Movie_copy ( copiedMovieId ))
                    """);
       statement.execute();

    }


}
