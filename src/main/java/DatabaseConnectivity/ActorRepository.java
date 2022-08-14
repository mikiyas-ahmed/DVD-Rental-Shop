package DatabaseConnectivity;

import entity.Actor;
import entity.Movie_copy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ActorRepository extends  DatabaseConnector{
    public List<Actor> getActorList(String title) throws SQLException {
        System.out.println("fetching records from movie table");
        ResultSet rs;
        List<Actor> actors= new ArrayList<>();
        String query = "SELECT firstname,lastname FROM actor where title like "+title;
        try(Statement statement=connection.createStatement()){
            rs= statement.executeQuery(query);
            rs.next();
            while(rs.next()){
                String fname=  rs.getString("firstname");
                String lname=  rs.getString("lastname");
                actors.add(new Actor(fname,lname,title));
            }
        }
        return actors;
    }
    public void addActor(String fname,String lname,String title) throws SQLException {
        System.out.println("inserting data to Actor table");
        int rows;
        try (PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO actor(firstname,lastname,title)
                    VALUES (?,?,?)
                    """)) {
            statement.setString(1,fname);
            statement.setString(2,lname);
            statement.setString(2,title);
            rows = statement.executeUpdate();
        }
        System.out.println("rows added: " + rows);
    }
}
