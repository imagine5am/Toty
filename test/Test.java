import org.Toty.Commons.Login;
import org.Toty.Server.Service.LoginService;
import java.io.*;
public class Test{
    public static void main(String args[]) throws IOException{
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        String username=in.readLine();
        String password=in.readLine();
        System.out.println(LoginService.add(new Login(username,password)));        
    }
}