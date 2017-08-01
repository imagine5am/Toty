package org.Toty.Server.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.Toty.Commons.Login;

/**
 *
 * @author Shivam Sood
 */
public class AdminLoginService {
    
    private Connection connection;
        
    public AdminLoginService(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String username=new String("root");
            String password=new String("root");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/totyadmin",username,password);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void create(){
        try{
            Statement statement=connection.createStatement();
            statement.executeUpdate(new String("create table login(username varchar(50) unique primary key,password varchar(50));"));
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public boolean check(Login login){
        try{
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery("select * from login where username='"+login.getUsername()+"' and password='"+login.getPassword()+"';");
            if(rs.next()){
                if(rs.getString("username").equals(login.getUsername()) && rs.getString("password").equals(login.getPassword())){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean remove(Login login){
        if(check(login)){
            try{
                Statement statement=connection.createStatement();
                int result=statement.executeUpdate("delete from login where username='"+login.getUsername()+"';");
                if(result>0)
                    return true;
                else
                    return false;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public boolean add(Login login){
        try{
        Statement statement=connection.createStatement();
        int result=statement.executeUpdate("insert into login values(\""+login.getUsername()+"\",\""+login.getPassword()+"\");");
        if(result>0)
            return true;
        else
            return false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public void finalize() throws Throwable{
        connection.close();
    }
}
