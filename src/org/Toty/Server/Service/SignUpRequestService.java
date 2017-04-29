package org.Toty.Server.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.Toty.Commons.User;

/**
 *
 * @author Shivam Sood
 */
public class SignUpRequestService {
    private Connection connection;
        
    public SignUpRequestService(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String username=new String("root");
            String password=new String("root");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/toty",username,password);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public boolean check(User user){
        try{
            Statement statement=connection.createStatement();
            ResultSet rs=statement.executeQuery("select * from request where username='"+user.getUsername()+"' and password='"+user.getPassword()+"';");
            if(rs.next()){
                if(rs.getString("username").equals(user.getUsername()) && rs.getString("password").equals(user.getPassword())){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean remove(User user){
        if(check(user)){
            try{
                Statement statement=connection.createStatement();
                int result=statement.executeUpdate("delete from request where username='"+user.getUsername()+"';");
                if(result>0)
                    return true;
                else
                    return false;
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
    
    public boolean add(User user){
        try{
        Statement statement=connection.createStatement();
        int result=statement.executeUpdate("insert into request values('"+user.getUsername()+"','"
                +user.getPassword()+"','"+user.getAttribute("Nationality")+"','"+user.getAttribute("Role")
                +"','"+user.getAttribute("Team")+"','"+user.getAttribute("Branch")+"');");
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
