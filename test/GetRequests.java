import org.Toty.Commons.Packet;
import org.Toty.Commons.Login;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import org.Toty.Commons.Encryptor;
import org.Toty.Commons.User;
import org.Toty.Server.Service.SignUpRequestService;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

/**
 *
 * @author Shivam Sood
 */
public class GetRequests {
    public static void main(String args[]) throws Exception{
        SignUpRequestService service=new SignUpRequestService();
        User user=service.getUser("rishi");
        System.out.println(user.toString());
    }
}
