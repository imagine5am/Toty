
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author imagine5am
 */
public class getFile {
    public ClassLoader objClassLoader = null;
    public FileInputStream objFileInputStream = null;
    /**
     * @param args the command line arguments
     */
    public getFile(){
        try{
        objClassLoader = getClass().getClassLoader();
        objFileInputStream = new FileInputStream(objClassLoader.getResource("Server/pub_key").getFile());
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException{
        getFile a=new getFile();
        byte[] b=new byte[a.objFileInputStream.available()];
        a.objFileInputStream.read(b);
        System.out.println(new String(b));
        
        //InputStream pubKeyReader=new FileInputStream("/Server/hey.txt");
    }
    
    
    
}
