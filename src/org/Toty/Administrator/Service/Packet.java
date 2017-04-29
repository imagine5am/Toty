package org.Toty.Administrator.Service;

import java.io.*;
/**
 *
 * @author Shivam Sood
 */
public class Packet implements Serializable {
    private byte code;
    private Object object;
    public Packet(){
        
    }
    public Packet(byte code,Object object){
        this.code=code;
        this.object=object;
    }
    public Object getObject(){
        return object;
    }
    public byte getCode(){
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
*/
