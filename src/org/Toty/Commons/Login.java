package org.Toty.Commons;

import java.io.*;
/**
 *
 * @author Shivam Sood
 */
public class Login implements Serializable{
    private String username;
    private String password;
    public Login(){
        username=new String();
        password=new String();
    }
    public Login(String username,String password){
        this.username=username;
        this.password=password;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public void setUsername(String username){
        this.username=new String(username);
    }
    public void setPassword(String password){
        this.password=new String(password);
    }
}
