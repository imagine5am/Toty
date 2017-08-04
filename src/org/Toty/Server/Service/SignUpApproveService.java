package org.Toty.Server.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.Toty.Commons.User;

/**
 *
 * @author Shivam Sood
 */
public class SignUpApproveService {
     private Connection connection;
     
     public SignUpApproveService(){
         try{
            Class.forName("com.mysql.jdbc.Driver");
            String username=new String("root");
            String password=new String("root");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/totyusers",username,password);
        }
        catch(Exception e){
            e.printStackTrace();
        }
     }
     
     public boolean initializeUser(User user){
         //create table sakshi(nationality varchar(15),role varchar(30), team varchar(30),branch varchar(15));
         try{
            Statement statement=connection.createStatement();
            String query="create table "+user.getUsername()+" (nationality varchar(15),role varchar(30),team varchar(30),branch varchar(15));";
            statement.executeUpdate(query);
            //statement=connection.createStatement();
            //insert into sakshi values('indian','developer','HP','New Delhi');
            query="insert into "+user.getUsername()+" values('"+user.getAttribute("nationality")+"','"+user.getAttribute("role")+"','"+user.getAttribute("team")+"','"+user.getAttribute("branch")+"');";
            statement.executeUpdate(query);
            return true;
         }
        catch(Exception e){
             e.printStackTrace();
             return false;
        }
    }
}
