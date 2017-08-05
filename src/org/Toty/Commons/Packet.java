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

-----Reply------
100     Admin Login Failed
101     Admin Login correct + All Requests Reply
102     admin added
103     admin not added
500     user login failed
501     user login correct
502     user request not added
503     user request added
504     user request to added rejected
505     user request to to added to login

----Failure Messages----
404     Login Failed    

----Universal----
909     Logout
*/
