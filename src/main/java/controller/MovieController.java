package controller;

import entity.Actor;
import entity.Movie;
import entity.Movie_copy;
import entity.RentedMovie;
import services.MovieManager;
import services.UserManager;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MovieController {
    MovieManager movieManager;
    UserManager  userManager;
    public MovieController(){
        movieManager = new MovieManager();
        userManager = new UserManager();
    }

    public void menu(){
        int input;
        Scanner scan= new Scanner(System.in);
        System.out.println("\t\tMovie Management \n\t\t please input number only press" +
                "\t\t1. To view available movies" +
                "\t\t2. To view rented movies" +
                "\t\t3. To view movie detail" +
                "\t\t4. To add new movie" +
                "\t\t5. To remove movie" +
                "\t\t6. To view movie List" +
                "\t\t7. To view Actors cast in the movie" );
        input = scan.nextInt();
        switch (input) {
            case 1 -> getAvailableMovies();
            case 2 -> printRentedMovies();
            case 3 -> printMovie();
            case 4 -> addMovie();
            case 5 -> removeMovie();
            case 6 -> getMovieList();
            case 7 -> printActors();
            default -> {
                System.out.println("wrong entry please insert only number between 1 upto 7");
                menu();
            }
        }
        menu();
    }

    public void getAvailableMovies() {
        try {
            List<Movie_copy> movieList= movieManager.getAvailableMovies();
            movieList.forEach(x -> {
                System.out.println("\nMovie Title: "+ x.getCopiedMovieTitle());
                System.out.println("\nMovie Availability : "+ x.isAvailable());
            });
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printRentedMovies() {
        try {
            List<RentedMovie> movieList= userManager.getRentedMovies();
            movieList.forEach(x -> {
                System.out.println("\nFirst Name: "+ x.getUserFirstName());
                System.out.println("\nMovie Title: "+ x.getMovieTitle());
                System.out.println("\nMovie Rented date:: "+ x.getRentalDate());
                System.out.println("\nMovie Return date:: "+ x.getReturnDate());
            });
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printMovie() {
        Scanner scan= new Scanner(System.in);
        System.out.println("insert Movie Title");
        String movieTitle=scan.next();
        try {
            Movie movie= movieManager.getMovie(movieTitle);
            System.out.println("\n\t\tMovie Title: "+ movie.getTitle());
            System.out.println("\n\t\tMovie Released Date: "+ movie.getReleasedYear());
            System.out.println("\n\t\tMovie Price: "+ movie.getPrice());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMovie()  {
        Scanner scan= new Scanner(System.in);
        System.out.println("insert Movie Title");
        String title=scan.nextLine();
        System.out.println("Insert the released year");
        Movie movie;
        try {
            String date= scan.nextLine();
            Date releasedDate= new SimpleDateFormat("dd/MM/yyyy").parse(date);
            System.out.println(" insert the price");
            int price= scan.nextInt();
            movie = new Movie(title, releasedDate,price);
            movieManager.addMovie(movie);
            addActor(movie.getTitle());
            addMovieCopy(movie.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addActor(String title){
        Scanner scan= new Scanner(System.in);
        System.out.println("insert the Actors casted in the movie");
        System.out.println("insert number of the Actors");
        int numberOfCopy = scan.nextInt();
        List<Actor> cast=new ArrayList<>();
        while(numberOfCopy>0){
            System.out.println("First name: ");
            String fname= scan.next();
            System.out.println("Last Name: ");
            String lname= scan.next();
            cast.add(new Actor(fname,lname,title));
            numberOfCopy --;
        }
        try {
            movieManager.addActorList(cast);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMovieCopy(String title){
        List<Movie_copy> cast=new ArrayList<>();
        Scanner scan= new Scanner(System.in);
        System.out.println("insert number of copy of the Movie");
        int numberOfCopy = scan.nextInt();
        while(numberOfCopy>0){
            cast.add(new Movie_copy(title,true));
            numberOfCopy --;
        }
        try {
            movieManager.addMovieCopy(cast);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeMovie() {
        Scanner scan= new Scanner(System.in);
        System.out.println("insert Movie Title");
        String movieTitle=scan.next();
        try {
            userManager.removeRentedMovieList(movieTitle);
            movieManager.removeMovie(movieTitle);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getMovieList() {
        try {
            List<Movie> movieList= movieManager.getMovieList();
            movieList.forEach(x -> {
                System.out.println("\nMovie Title: "+ x.getTitle());
                System.out.println("\nMovie Released Date: "+ x.getReleasedYear());
                System.out.println("\nMovie Price: "+ x.getPrice());
            });
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printActors() {
        Scanner scan= new Scanner(System.in);
        System.out.println("insert Movie Title");
        String title=scan.next();
        try {
            List<Actor> actorList= movieManager.getActorList(title);
            actorList.forEach(x -> {
                System.out.println("\nFirst Name: "+ x.getFirstName());
                System.out.println("\nLast Name: "+ x.getLastName());
                System.out.println("\nMovie Title: "+ title);
            });
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
