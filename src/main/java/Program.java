import controller.MovieController;
import controller.UserController;
import services.DatabaseConnectionManager;

import java.util.Scanner;

public class Program {
    public static MovieController movieController;
    public static UserController userController;
    public static void main(String []args){
        Program program= new Program();
        DatabaseConnectionManager.connect();
        program.menu();
    }
    public void menu(){
        int input;
        Scanner scan= new Scanner(System.in);
        System.out.println("\t\tMovie Management \n\t\t please input number only press" +
                "\t\t1. Movies Menu" +
                "\t\t2. User Menu"  );
        input = scan.nextInt();
        switch (input) {
            case 1 -> {
                movieController = new MovieController();
                movieController.menu();
            }
            case 2 -> {
                userController = new UserController();
                userController.menu();
            }
            default -> {
                System.out.println("wrong entry please insert only number between 1 upto 7");
                menu();
            }
        }
        menu();
    }
}
