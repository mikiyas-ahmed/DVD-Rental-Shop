package DatabaseConnectivity;

import entity.Client;
import entity.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository  extends DatabaseConnector {

    public void addUser(Client user) throws SQLException {
        System.out.println("inserting data to User table");
        int rows;
        try (PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO client(firstName,lastName,birthDate)
                    VALUES (?,?,?)
                    """)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setDate(3, (Date) user.getBirthDate());
            rows = statement.executeUpdate();
        }
        System.out.println("rows added: " + rows);
    }
    public void removeUser(String userName) throws SQLException{
        System.out.println("Deleting records from user table");
        int rows;
        try (PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM client
                    where firstName like ?
                    """)) {
            statement.setString(1, userName);
            rows = statement.executeUpdate();
        }
        System.out.println("rows deleted: " + rows);
    }
    public Client getUser(String firstName) throws SQLException{
        System.out.println("fetching records from user table");
        ResultSet rs;
        String query = "SELECT firstName,lastName,birthDate FROM client where firstName like "+firstName;
        try(Statement statement = connection.createStatement()){
            rs= statement.executeQuery(query);
            rs.next();
            String fName=  rs.getString("firstName");
            String lName = rs.getString("lastName");
            Date birthDate= rs.getDate("birthDate");
            return new Client(fName,lName,birthDate);
        }
    }
    public List<Client> getUserList() throws SQLException{
        System.out.println("fetching records from user table");
        ResultSet rs;
        List<Client> users= new ArrayList<>();
        String query = "SELECT firstName,lastName,birthDate FROM client";
        try (Statement statement = connection.createStatement()) {
            rs= statement.executeQuery(query);
            while(rs.next()){
                String fName=  rs.getString("firstName");
                String lName = rs.getString("lastName");
                Date birthDate= rs.getDate("birthDate");
                users.add(new Client(fName,lName,birthDate));
            }
        }
        return users;
    }


}
