package org.Toty.Commons;

import java.io.*;
/**
 *
 * @author Shivam Sood
 */
public class Packet implements Serializable {
    
    private int code;
    private Object object;
    
    public Packet(){
        
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
1       login
2       add
3       admin login
4       admin add
100     Admin Login Failed
101     All Requests Reply
404     Login Failed     
*/
