package controller;

import entity.Client;
import entity.RentedMovie;
import services.UserManager;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserController {
    UserManager userManager;
    public  UserController(){
        this.userManager = new UserManager();
    }

    public void menu(){
        int input;
        Scanner scan= new Scanner(System.in);
        System.out.println("\t\tClient Management \n\t\t please input number only press" +
                "\t\t1. To view user List" +
                "\t\t2. To view user detail" +
                "\t\t3. To add user" +
                "\t\t4. To remove user" +
                "\t\t5. To view rented movie by user" +
                "\t\t6. To rent movie to the user" +
                "\t\t7. To delete rented movie from user");
        input = scan.nextInt();
        switch (input) {
            case 1 -> printUserList();
            case 2 -> printUserDetail();
            case 3 -> saveUser();
            case 4 -> removeUser();
            case 5 -> printRentedMovieByUser();
            case 6 -> rentMovie();
            case 7 -> deleteRentedMovie();
            default -> {
                System.out.println("wrong entry please insert only number between 1 upto 7");
                menu();
            }
        }
        menu();
    }

    public void printUserList(){
        try {
            List<Client> userList= userManager.getUserList();
            userList.forEach(x -> {
                System.out.println("\n\t\tFirst Name: "+ x.getFirstName());
                System.out.println("\n\t\tLast Name: "+ x.getLastName());
                System.out.println("\n\t\tBirth date: "+ x.getBirthDate());
            });
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printUserDetail(){
        Scanner scan= new Scanner(System.in);
        System.out.println("insert the user name: ");
        String userName = scan.next();
        try {
            Client user= userManager.getUser(userName);
            System.out.println("\n\t\tFirst Name: "+ user.getFirstName());
            System.out.println("\n\t\tLast Name: "+ user.getLastName());
            System.out.println("\n\t\tBirth date: "+ user.getBirthDate());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(){
        Scanner scan= new Scanner(System.in);
        System.out.println("User First Name: ");
        String fName=scan.next();
        System.out.println("User Last Name: ");
        String lName=scan.next();
        System.out.println("Insert the Birth date: ");
        Client user;
        try {
            String date= scan.nextLine();
            Date birthDate= new SimpleDateFormat("dd/MM/yyyy").parse(date);
            user = new Client(fName, lName,birthDate);
            userManager.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUser(){
        Scanner scan= new Scanner(System.in);
        System.out.println("insert the user name: ");
        String userName = scan.next();
        try {
            userManager.removeUser(userName);
            System.out.println("User removed successfully");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void printRentedMovieByUser(){
        Scanner scan= new Scanner(System.in);
        System.out.println("insert the user name: ");
        String userName = scan.next();
        try {
            List<RentedMovie> movieList= userManager.getRentedMoviesByUser(userName);
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

    public void rentMovie(){
        Scanner scan= new Scanner(System.in);
        System.out.println("insert Movie Title");
        String title=scan.next();
        System.out.println("insert User first name");
        String userName=scan.next();
        System.out.println("Insert the rental day");
        try {
            String rentDay= scan.next();
            Date rentalDate= new SimpleDateFormat("dd/MM/yyyy").parse(rentDay);
            System.out.println("Insert the return day");
            String returnDay= scan.next();
            Date returnDate= new SimpleDateFormat("dd/MM/yyyy").parse(returnDay);
            RentedMovie movie=new RentedMovie(rentalDate,returnDate,title,userName);
            userManager.addRentedMovie(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRentedMovie(){
        Scanner scan= new Scanner(System.in);
        System.out.println("insert the user name: ");
        String userName = scan.next();
        System.out.println("insert the movie title: ");
        String title = scan.next();
        try {
             userManager.removeRentedMovie(userName,title);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
