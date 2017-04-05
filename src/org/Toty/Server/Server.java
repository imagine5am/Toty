package org.Toty.Server;

import java.util.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author Shivam Sood
 */
public class Server {
    private ServerSocket serverSocket;
    public Server(){
        try{
            this.serverSocket=new ServerSocket(1234);
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("IOException IN SERVER CONSTRUCTOR");
        }
    }
    public void start() throws IOException{
        Socket socket;
        ServerThread serverThread;
        while(true){
            socket=serverSocket.accept();
            serverThread=new ServerThread(socket);
            serverThread.start();    
        }
    }
    public static void main(String args[]) throws IOException{
        Server server=new Server();
        server.start();
    }
}
