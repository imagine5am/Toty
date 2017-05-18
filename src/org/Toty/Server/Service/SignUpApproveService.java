package org.Toty.Server.Service;

import java.sql.Connection;
import java.sql.DriverManager;

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
}
