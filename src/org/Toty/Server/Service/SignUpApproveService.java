package org.Toty.Server.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.Toty.Commons.Login;
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
     
     public boolean approveUser(User user){
         //create table sakshi(nationality varchar(15),role varchar(30), team varchar(30),branch varchar(15));
         try{
            Statement statement=connection.createStatement();
            String query="create table "+user.getUsername()+" (nationality varchar(15),role varchar(30),team varchar(30),branch varchar(15));";
            statement.executeUpdate(query);
            //statement=connection.createStatement();
            //insert into sakshi values('indian','developer','HP','New Delhi');
            query="insert into "+user.getUsername()+" values('"+user.getAttribute("nationality")+"','"+user.getAttribute("role")+"','"+user.getAttribute("team")+"','"+user.getAttribute("branch")+"');";
            statement.executeUpdate(query);
            System.out.println("hello");
            SignUpRequestService service =new SignUpRequestService();
            user=service.getUser(user.getUsername());
            Login login=new Login(user.getUsername(),user.getPassword());
            LoginService loginService=new LoginService();
            if(loginService.add(login)){
                if(service.remove(user)){
                    return true;
                }
            }
         }
        catch(Exception e){
             e.printStackTrace();
        }
        return false;
    }
     
     public User getUser(String username){
        User user=new User(username); 
         try{
              Statement statement=connection.createStatement();
              //select * from username;
              ResultSet rs=statement.executeQuery("select * from "+username);
              rs.next();
              user.addAttribute("nationality",rs.getString("nationality"));
              user.addAttribute("role",rs.getString("role"));
              user.addAttribute("team",rs.getString("team"));
              user.addAttribute("branch",rs.getString("branch"));
              rs.close();
         }catch(Exception e){
             e.printStackTrace();
         }
         return user;
    }
}
