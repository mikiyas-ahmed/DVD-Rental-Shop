package services;

import DatabaseConnectivity.ActorRepository;
import DatabaseConnectivity.MovieCopyRepository;
import DatabaseConnectivity.MovieRepository;
import DatabaseConnectivity.RentedMovieRepository;
import entity.Actor;
import entity.Movie;
import entity.Movie_copy;


import java.sql.SQLException;
import java.util.List;

public class MovieManager {
    MovieRepository movieRpo;
    MovieCopyRepository movieCopyRpo;
    ActorRepository actorRepo;
    RentedMovieRepository rentedMovieRepo;

    public MovieManager(){
        movieRpo = new MovieRepository();
        movieCopyRpo = new MovieCopyRepository();
        actorRepo = new ActorRepository();
        rentedMovieRepo = new RentedMovieRepository();
    }
    public void addMovie(Movie movie) throws SQLException {
            try {
                movieRpo.openDatabaseConnection();
                movieRpo.addMovie(movie);
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                movieRpo.closeDatabaseConnection();
            }
    }
    public void removeMovie(String movieTitle) throws SQLException {
        try {
            movieRpo.openDatabaseConnection();
            movieRpo.removeMovie(movieTitle);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            movieRpo.closeDatabaseConnection();
        }
    }
    public Movie getMovie( String title) throws SQLException {
        try {
            movieRpo.openDatabaseConnection();
            return movieRpo.getMovie(title);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            movieRpo.closeDatabaseConnection();
        }
        return null;
    }
    public List<Movie> getMovieList() throws SQLException {
        try {
            movieRpo.openDatabaseConnection();
            return movieRpo.getMovieList();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            movieRpo.closeDatabaseConnection();
        }
        return null;
    }
    public List<Movie_copy> getAvailableMovies() throws SQLException {
        try {
            movieCopyRpo.openDatabaseConnection();
            return movieCopyRpo.getAvailableMovies();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            movieCopyRpo.closeDatabaseConnection();
        }
        return null;
    }
    public void addMovieCopy(List<Movie_copy> cast) throws SQLException {
        try {
            movieCopyRpo.openDatabaseConnection();
            cast.forEach(x-> {
                try {
                    movieCopyRpo.addMovie(x.getCopiedMovieTitle(),x.isAvailable());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            movieCopyRpo.closeDatabaseConnection();
        }
    }
    public List<Actor> getActorList(String title) throws SQLException {
        try {
            actorRepo.openDatabaseConnection();
            return actorRepo.getActorList(title);
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            actorRepo.closeDatabaseConnection();
        }
        return null;
    }
    public void addActorList(List<Actor> actorList) throws SQLException {
        try {
            actorRepo.openDatabaseConnection();
            actorList.forEach(x-> {
                try {
                    actorRepo.addActor(x.getFirstName(),x.getLastName(),x.getMovieTitle());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            actorRepo.closeDatabaseConnection();
        }
    }

}
