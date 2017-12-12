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
import org.Toty.Commons.EncryptedFile;
import org.Toty.Server.Service.FileSerializer;
import org.Toty.Server.Service.SignUpApproveService;

/**
 *
 * @author Shivam Sood
 */
public class ServerThread extends Thread {

    private static ArrayList<Login> list;
    private String fileStorageLocation;
    private Socket socket;

    static {
        list = new ArrayList<>();
    }

    public ServerThread(Socket socket) {
        this.socket = socket;
        this.fileStorageLocation=getFileStorageLocation();
    }

    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            Packet packet = (Packet) in.readObject();
            int byteCode = packet.getCode();

            while (byteCode != 909) {
                if (byteCode == 1) {
                    System.out.println("User Login");
                    Login login = (Login) packet.getObject();
                    LoginService loginService = new LoginService();
                    boolean result = loginService.check(login);
                    if (result) {
                        /*
                        ClassLoader objClassLoader = getClass().getClassLoader();
                        FileInputStream objFileInputStream = new FileInputStream(objClassLoader.getResource("Server/master_key").getFile());
                        byte[] allBytes = new byte[objFileInputStream.available()];
                        objFileInputStream.read(allBytes);
                        //System.out.println(new String(allBytes));
                        */
                        SignUpApproveService s=new SignUpApproveService();
                        out.writeObject(new Packet(501, s.getUser(login.getUsername())));
                    } else {
                        out.writeObject(new Packet(500));
                    }
                    out.flush();
                } else if (byteCode == 2) {
                    User user = (User) packet.getObject();
                    System.out.println("Data User check");
                    System.out.println(user);
                    SignUpRequestService signUpService = new SignUpRequestService();
                    boolean result = signUpService.add(user);
                    if (result) {
                        out.writeObject(new Packet(503));
                    } else {
                        out.writeObject(new Packet(502));
                    }
                    out.flush();
                } else if (byteCode == 3) {
                    Login login = (Login) packet.getObject();
                    System.out.println("Data Admin check");
                    System.out.println("Username: " + login.getUsername() + " Password: " + login.getPassword());
                    AdminLoginService loginService = new AdminLoginService();
                    if (loginService.check(login)) {
                        SignUpRequestService service = new SignUpRequestService();
                        ArrayList<User> allUsers = (ArrayList<User>) service.getAllRequests();
                        out.writeObject(new Packet(101, (Object) allUsers));
                    } else {
                        out.writeObject(new Packet(100));
                    }
                } else if (byteCode == 4) {
                    Login login = (Login) packet.getObject();
                    System.out.println("Data Admin add");
                    System.out.println("Username: " + login.getUsername() + " Password: " + login.getPassword());
                    AdminLoginService loginService = new AdminLoginService();
                    boolean result = loginService.add(login);
                    if (result) {
                        out.writeObject(new Packet(102));
                    } else {
                        out.writeObject(new Packet(103));
                    }
                    out.flush();
                } else if (byteCode == 5) { //user request to added rejected
                    String username = (String) packet.getObject();
                    SignUpRequestService requestService = new SignUpRequestService();
                    boolean result = requestService.remove(username);
                    out.writeObject(new Packet(504, new Boolean(result)));
                    out.flush();
                } else if (byteCode == 6) { //user request to to added to login
                    User user = (User) packet.getObject();
                    SignUpApproveService approveService = new SignUpApproveService();
                    boolean result = approveService.approveUser(user);
                    out.writeObject(new Packet(505, new Boolean(result)));
                } else if(byteCode == 7){  //user sends the encrypted file, get encrypted file
                    EncryptedFile encFile=(EncryptedFile)packet.getObject();
                    File newFile=new File(this.fileStorageLocation,encFile.getFilename());
                    newFile.createNewFile();
                    newFile.setWritable(true);
                    FileOutputStream objFileOutputStream= new FileOutputStream(newFile);
                    objFileOutputStream.write(encFile.getFileBytes());
                    objFileOutputStream.flush();
                    objFileOutputStream.close();
                    //System.err.println(newFile.getAbsolutePath()+" "+newFile.getName());
                } else if(byteCode == 8){   //get list of All available files
                    String allFiles[]=new File(fileStorageLocation).list();
                    out.writeObject(new Packet(506,allFiles));
                    out.flush();
                } else if(byteCode == 9){   //user requests a particular file
                    String filename=(String)packet.getObject();
                    File file=new File(this.fileStorageLocation+"/"+filename);
                    FileInputStream objFileInputStream = new FileInputStream(file);
                    byte[] allBytes = new byte[objFileInputStream.available()];
                    objFileInputStream.read(allBytes);
                    objFileInputStream.close();
                    out.writeObject(new Packet(509,new EncryptedFile(filename,allBytes)));
                    out.flush();
                }
                packet = (Packet) in.readObject();
                byteCode = packet.getCode();
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String getFileStorageLocation() {
        String s = null;
        ClassLoader objClassLoader = getClass().getClassLoader();
        s = objClassLoader.getResource("Server/FileStorageLocation").getFile();
        try {
            FileReader fr = new FileReader(s);
            BufferedReader br = new BufferedReader(fr);
            s = br.readLine();
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
