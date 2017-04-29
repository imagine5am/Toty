package org.Toty.Commons;

import org.jasypt.digest.PooledStringDigester;

/**
 *
 * @author Shivam Sood
 */
public class Encryptor {
    private String password;
    public Encryptor(String password){
        this.password=password;
    }
    public String run(){
        try{
            password=new String(password.getBytes("UTF-8"));
            PooledStringDigester passwordEncryptor = new PooledStringDigester();
            passwordEncryptor.setAlgorithm("SHA-1");
            passwordEncryptor.setPoolSize(4);
            passwordEncryptor.setIterations(50000);
            password=passwordEncryptor.digest(password);
            return password;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "null";
    }
}
