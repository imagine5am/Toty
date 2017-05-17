import org.Toty.Commons.Login;
import org.Toty.Server.Service.LoginService;
import org.Toty.Commons.Encryptor;
import java.io.*;
public class Test{
    public static void main(String args[]) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter iterations");
        int i=Integer.parseInt(br.readLine());
        for(;i!=0;i--){
            System.out.println("Enter password");
            String password=br.readLine();
            password=new Encryptor(password).run();
            System.out.println("Encrypted Password: "+password);
        }
    }
}