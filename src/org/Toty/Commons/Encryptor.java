package org.Toty.Commons;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

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
            ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
            passwordEncryptor.setAlgorithm("SHA-1");
            passwordEncryptor.setPlainDigest(true);
            String encryptedPassword = passwordEncryptor.encryptPassword(password);
            return encryptedPassword;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "null";
    }
}
