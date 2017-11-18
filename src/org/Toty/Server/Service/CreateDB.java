package org.Toty.Server.Service;

import java.sql.*;

/**
 *
 * @author Shivam Sood
 */

public class CreateDB {
    
    public CreateDB(){
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        String DB_URL = "jdbc:mysql://localhost/";
        String USER = "root";
        String PASS = "root";
        Connection conn=null;
        Statement stmt=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(new String("create database totyadmin;"));
            stmt.executeUpdate(new String("create database toty;"));
            stmt.executeUpdate(new String("create database totyusers;"));
            stmt.executeUpdate(new String("create database totykeys;"));    //********Review This********
            System.out.println("Databases Created Successfully");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(stmt!=null){
                    stmt.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            try{
                if(conn!=null){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String args[]){
        new CreateDB();
    }
}
