package org.Toty.Commons;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Shivam Sood
 */
public class User implements Serializable{
    private String username;
    private String password;
    private HashMap<String,String> attributes;

    public User(String username,String password){
        this.username=new String(username);
        this.password=new String(password);
        attributes=new HashMap<>();
    }
    
    public User(String username){
        this.username=new String(username);
        this.password=new String();
        attributes=new HashMap<>();
    }
    
    public String getUsername() {
        return new String(username);
    }

    public String getPassword() {
        return new String(username);
    }

    public HashMap<String,String> getAttributes() {
        return new HashMap<String,String>(attributes);
    }

    public void setUsername(String username) {
        this.username = new String(username);
    }

    public void setPassword(String password) {
        this.password = new String(password);
    }

    public void setAttributes(HashMap<String,String> attributes) {
        this.attributes = new HashMap<String,String>(attributes);
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
    
    @Override
    public String toString(){
        String string="Username: "+username+" Password: "+password+"\n";
        string+=attributes;
        return string;
    }
}
