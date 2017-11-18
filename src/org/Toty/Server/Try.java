/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Toty.Server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author imagine5am
 */
public class Try {
    public static void main(String[] args) throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/totykeys","root","root");

    String sql = "select * from abekeys;";
    PreparedStatement stmt = conn.prepareStatement(sql);
    ResultSet resultSet = stmt.executeQuery();
    while (resultSet.next()) {
      File master_key = new File("/home/imagine5am/keytest/master_key");
      File pub_key = new File("/home/imagine5am/keytest/pub_key");
      FileOutputStream masterOut = new FileOutputStream(master_key);
      FileOutputStream pubOut = new FileOutputStream(pub_key);
      byte[] buffer = new byte[1];
      InputStream is = resultSet.getBinaryStream(1);
      while (is.read(buffer) > 0) {
        masterOut.write(buffer);
      }
      masterOut.close();
      is=resultSet.getBinaryStream(2);
      while (is.read(buffer) > 0) {
        pubOut.write(buffer);
      }
      pubOut.close();
    }
    conn.close();
  }
}
