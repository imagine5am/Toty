package org.Toty.Sender;

import java.net.*;
import java.io.*;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.Toty.common.*;

/**
 *
 * @author Shivam Sood
 */
public class Sender {
    public static void main(String args[]) throws Exception{
        Socket socket=new Socket("localhost",1234);
        ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
        DataInputStream in=new DataInputStream(socket.getInputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        ConfigurablePasswordEncryptor passwordEncryptor=new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm("SHA-1");
        passwordEncryptor.setPlainDigest(true);
        String username=br.readLine();
        String tempPassword=br.readLine();
        String password=passwordEncryptor.encryptPassword(tempPassword);
        //encrpyt
        Login login=new Login(username,password);
        byte b=2;
        Packet packet=new Packet(b,login);
        out.writeObject(packet);
        String result=in.readUTF();
        System.out.println(result);
        System.out.println("System Shut Down");
    }
}
