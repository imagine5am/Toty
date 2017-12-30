package org.Toty.Commons;

import java.io.*;
/**
 *
 * @author Shivam Sood
 */
public class Packet implements Serializable {
    
    private int code;
    private Object object;
    
    public Packet(int code){
        this.code=code;
    }
    
    public Packet(int code,Object object){
        this.code=code;
        this.object=object;
    }
    
    public Object getObject(){
        return object;
    }
    
    public int getCode(){
        return code;
    }
    
    public void setObject(Object object){
        this.object=object;
    }
    
    public void setCode(byte code){
        this.code=code;
    }
}
/*
Code    Object
1       user login
2       user add
3       admin login
4       admin add
5       user request to added rejected
6       user request to to added to login
7       user sends encrypted file
8       user request a list of all encrypted files
9       user requests a particular file
10      admin gets list of all the sign up requests

-----Reply------
100     Admin Login Failed
101     Admin Login correct
102     admin added
103     admin not added
104     send all user requests to admin
500     user login failed
501     user login correct
502     user request not added
503     user request added
504     user request to added rejected
505     user request to to added to login
506     user gets a list of all files
509     user gets the requested file

----Failure Messages----
404     Login Failed    

----Universal----
909     Logout
*/
