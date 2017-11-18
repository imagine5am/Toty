/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Toty.User.View;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.net.URI;

/**
 *
 * @author imagine5am
 */
public class Try {
    public static void main(String args[]){
        File name=null;
        try{
            name=File.createTempFile("createTempFolder",".sh");
            name.deleteOnExit();
            name.setExecutable(true);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        String path=name.getAbsolutePath();
        try{
            FileOutputStream file = new FileOutputStream(name);  
            DataOutputStream data = new DataOutputStream(file);
            data.writeChars("#!/bin/bash\n");
            data.writeChars("#mktemp -d > temp_folderName\n");
            data.writeChars("echo \"HI\"");
            data.flush();
            data.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(path);
        try{
            Process proc = Runtime.getRuntime().exec(path); //Whatever you want to execute
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            while (read.ready()) {
                System.out.println(read.readLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
/*
    file=File.createTempFile("createTempFolder",".sh");
            file.deleteOnExit();
            file.setExecutable(true);
            DataOutputStream fout = new DataOutputStream(new FileOutputStream(file));
            fout.writeChars("#!/bin/bash\n");
            fout.writeChars("mktemp -d > /tmp/temp_folderName\n");
            //fout.writeChars("echo \"HI\"");
            fout.flush();
            fout.close();
            executeCommand(file.getAbsolutePath());
*/
