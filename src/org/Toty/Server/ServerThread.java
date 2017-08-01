package org.Toty.Server;

import org.Toty.Server.Service.LoginService;
import org.Toty.Server.Service.AdminLoginService;
import org.Toty.Commons.Login;
import org.Toty.Commons.Packet;
import org.Toty.Commons.User;
import org.Toty.Server.Service.SignUpRequestService;

import java.net.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author Shivam Sood
 */
public class ServerThread extends Thread {
    private static ArrayList<Login> list;
    private Socket socket;
    static{
        list=new ArrayList<>();
    }
    public ServerThread(Socket socket){
        this.socket=socket;
    }
    public void run(){
        try{
            ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
            Packet packet=(Packet)in.readObject();
            int byteCode=packet.getCode();
            if(byteCode==1){
                Login login=(Login)packet.getObject();
                DataOutputStream out=new DataOutputStream(socket.getOutputStream());
                LoginService loginService=new LoginService();
                boolean result=loginService.check(login);
                if(result){
                    out.writeUTF(new String("true"));
                }
                else{
                    out.writeUTF(new String("false"));
                }
                out.close();
            }
            else if(byteCode==2){
                User user=(User)packet.getObject();
                System.out.println("Data User check");
                System.out.println(user);
                DataOutputStream out=new DataOutputStream(socket.getOutputStream());
                SignUpRequestService signUpService=new SignUpRequestService();
                boolean result=signUpService.add(user);
                if(result){
                    out.writeUTF(new String("true"));
                }
                else{
                    out.writeUTF(new String("false"));
                }
                out.close();
            }
            else if(byteCode==3){
                Login login=(Login)packet.getObject();
                System.out.println("Data Admin check");
                System.out.println("Username: "+login.getUsername()+" Password: "+login.getPassword());
                ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
                AdminLoginService loginService=new AdminLoginService();
                if(loginService.check(login)){
                    SignUpRequestService service=new SignUpRequestService();
                    ArrayList<User> allUsers=(ArrayList<User>)service.getAllRequests();
                    Packet p=new Packet(101,(Object)allUsers);
                    out.writeObject(p);
                }
                else{
                    Packet p=new Packet(100,null);
                }
            }
            else if(byteCode==4){
                Login login=(Login)packet.getObject();
                System.out.println("Data Admin add");
                System.out.println("Username: "+login.getUsername()+" Password: "+login.getPassword());
                DataOutputStream out=new DataOutputStream(socket.getOutputStream());
                AdminLoginService loginService=new AdminLoginService();
                boolean result=loginService.add(login);
                if(result){
                    out.writeUTF(new String("true"));
                }
                else{
                    out.writeUTF(new String("false"));
                }
                out.close();
            }
            in.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
