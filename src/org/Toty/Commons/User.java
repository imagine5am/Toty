package org.Toty.Commons;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Shivam Sood
 */
public class User implements Serializable{
    private String username;
    private String password;
    private HashMap<String,String> attributes;

    public User(String username,String password){
        this.username=username;
        this.password=password;
        attributes=new HashMap<>();
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<String,String> getAttributes() {
        return attributes;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAttributes(HashMap<String,String> attributes) {
        this.attributes = attributes;
    }
    
    public boolean addAttribute(String key,String value){
        if(attributes.containsKey(key)){
            return false;
        }else{
            attributes.put(key,value);
            return true;
        }
    }
    
    public String getAttribute(String key){
        if(attributes.containsKey(key)){
            return attributes.get(key);
        }else{
            return "-1";
        }
    }
}
