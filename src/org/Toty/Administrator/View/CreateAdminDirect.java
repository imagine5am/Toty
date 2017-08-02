package org.Toty.Administrator.View;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
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
public class CreateAdminDirect {
    public static void main(String args[]) throws Exception{
        Socket socket=new Socket("localhost",1234);
        System.out.println("___USE THiS___");
        ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
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
        Packet result=(Packet)in.readObject();
        if(result.getCode()==102){
            System.out.println("Admin Added");
        }
        System.out.println("System Shut Down");
    }
}
