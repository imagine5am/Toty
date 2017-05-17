package org.Toty.Example;

import org.Toty.Commons.Packet;
import org.Toty.Commons.Login;
import java.net.*;
import java.io.*;
import org.Toty.Commons.Encryptor;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

/**
 *
 * @author Shivam Sood
 */
public class Sender {
    public static void main(String args[]) throws Exception{
        Socket socket=new Socket("localhost",1234);
        System.out.println("___DO NOT USE THiS___");
        ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
        DataInputStream in=new DataInputStream(socket.getInputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        ConfigurablePasswordEncryptor passwordEncryptor=new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm("SHA-1");
        passwordEncryptor.setPlainDigest(true);
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
