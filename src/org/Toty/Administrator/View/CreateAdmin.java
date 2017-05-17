package org.Toty.Administrator.View;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import org.Toty.Commons.Encryptor;
import org.Toty.Commons.Login;
import org.Toty.Commons.Packet;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

/**
 *
 * @author Shivam Sood
 */
public class CreateAdmin {
    public static void main(String args[]) throws Exception{
        Socket socket=new Socket("localhost",1234);
        System.out.println("___USE THiS___");
        ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
        DataInputStream in=new DataInputStream(socket.getInputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter username");
        String username=br.readLine();
        System.out.println("Enter password");
        String tempPassword=br.readLine();
        String password=new Encryptor(tempPassword).run();
        //encrpyt
        Login login=new Login(username,password);
        byte b=4;
        Packet packet=new Packet(b,login);
        out.writeObject(packet);
        String result=in.readUTF();
        System.out.println(result);
        System.out.println("System Shut Down");
    }
}
