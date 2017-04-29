package org.Toty.Server;

import org.Toty.Server.Service.LoginService;
import org.Toty.Commons.Login;
import org.Toty.Commons.Packet;

import java.net.*;
import java.util.*;
import java.io.*;
//import org.Toty.common.*;

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
            byte byteCode=packet.getCode();
            if(byteCode==1){
                Login login=(Login)packet.getObject();
                DataOutputStream out=new DataOutputStream(socket.getOutputStream());
                boolean result=LoginService.check(login);
                if(result){
                    out.writeUTF(new String("true"));
                }
                else{
                    out.writeUTF(new String("false"));
                }
                out.close();
            }
            else if(byteCode==2){
                Login login=(Login)packet.getObject();
                DataOutputStream out=new DataOutputStream(socket.getOutputStream());
                boolean result=LoginService.add(login);
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
