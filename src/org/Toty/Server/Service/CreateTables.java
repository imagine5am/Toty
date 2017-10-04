package org.Toty.Server.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Shivam Sood
 */
public class CreateTables {
    
    private String username;
    private String password;
    
    public CreateTables(){
        username=new String("root");
        password=new String("root");
        createTotyAdminTables();
        createTotyTables();
    }
    
    public void createTotyAdminTables(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/totyadmin",username,password);
            Statement statement=connection.createStatement();
            statement.executeUpdate(new String("create table login(username varchar(50) unique primary key,password varchar(50));"));
            System.out.println("Table 'login' created for TotyAdmin");
            connection.close();
            statement.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void createTotyTables(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/toty",username,password);
            Statement statement=connection.createStatement();
            statement.executeUpdate(new String("create table login(username varchar(50) unique primary key,password varchar(50));"));
            System.out.println("Tables 'login' created for Toty");
            statement=connection.createStatement();
            statement.executeUpdate(new String("create table request(username varchar(50) primary key,password varchar(50), nationality varchar(15), role varchar(30), team varchar(30), branch varchar(15));"));
            System.out.println("Tables 'request' created for Toty");
            connection.close();
            statement.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]){
        new CreateTables();
    }
}
