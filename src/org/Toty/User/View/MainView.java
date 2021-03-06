package org.Toty.User.View;

import java.io.File;
import java.io.IOException;

import java.net.*;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.Scrollable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import org.Toty.Commons.EncryptedFile;
import org.Toty.Commons.Packet;
import org.Toty.Commons.User;
import org.Toty.Server.Service.FileSerializer;

/**
 *
 * @author Shivam Sood
 */
public class MainView extends javax.swing.JFrame {

    private Socket socket;
    private String filename,filepath;
    private User user;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String tempDir;
    private File userKey;
    private String[] allFiles;
    
    public MainView(Socket socket, User user, ObjectOutputStream out, ObjectInputStream in) {
        //super.setTitle("User");
        initComponents();
        //set username label
        usernameLabel.setText(user.getUsername());
        //set column width
        TableColumnModel columnModel=viewFilesJTable.getColumnModel();
        columnModel.getColumn(1).setMaxWidth(100);
        columnModel.getColumn(1).setMinWidth(100);
        columnModel.getColumn(1).setWidth(100);
        //set table renderer and editor
        viewFilesJTable.getColumn("Try Decryption").setCellRenderer(new PanelRenderer());
        viewFilesJTable.getColumn("Try Decryption").setCellEditor(new PanelEditor(new JCheckBox()));
        
        //set icon
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/User/userIcon.png")));
        //normal stuff
        this.user = user;
        this.socket = socket;
        this.out = out;
        this.in = in;
        this.filename = new String();
        this.tempDir = createTempFolder();
        System.out.println(user + "\n" + tempDir);
        moveKeys();
        generateUserKey();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Tabs = new javax.swing.JTabbedPane();
        uploadFileTab = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        filenameTextField = new javax.swing.JTextField();
        fileSelectionButton = new javax.swing.JButton();
        encryptUploadButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        policyTextArea = new javax.swing.JTextArea();
        viewFilesTab = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        viewFilesJTable = new javax.swing.JTable();
        logoutButton = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User");
        setPreferredSize(new java.awt.Dimension(720, 480));

        uploadFileTab.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                uploadFileTabComponentShown(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Select File:");

        filenameTextField.setEditable(false);

        fileSelectionButton.setText("Select");
        fileSelectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileSelectionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filenameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fileSelectionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filenameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(fileSelectionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        encryptUploadButton.setText("Encrypt and Upload");
        encryptUploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptUploadButtonActionPerformed(evt);
            }
        });

        policyTextArea.setColumns(20);
        policyTextArea.setRows(5);
        jScrollPane1.setViewportView(policyTextArea);

        javax.swing.GroupLayout uploadFileTabLayout = new javax.swing.GroupLayout(uploadFileTab);
        uploadFileTab.setLayout(uploadFileTabLayout);
        uploadFileTabLayout.setHorizontalGroup(
            uploadFileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(uploadFileTabLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(encryptUploadButton))
            .addComponent(jScrollPane1)
        );
        uploadFileTabLayout.setVerticalGroup(
            uploadFileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uploadFileTabLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(encryptUploadButton))
        );

        Tabs.addTab("Upload File", uploadFileTab);

        viewFilesTab.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                viewFilesTabComponentShown(evt);
            }
        });

        viewFilesJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Filename", "Try Decryption"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        viewFilesJTable.setAutoscrolls(false);
        viewFilesJTable.setRowHeight(45);
        jScrollPane2.setViewportView(viewFilesJTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout viewFilesTabLayout = new javax.swing.GroupLayout(viewFilesTab);
        viewFilesTab.setLayout(viewFilesTabLayout);
        viewFilesTabLayout.setHorizontalGroup(
            viewFilesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        viewFilesTabLayout.setVerticalGroup(
            viewFilesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Tabs.addTab("View Files", viewFilesTab);

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        usernameLabel.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tabs)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uploadFileTabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_uploadFileTabComponentShown
        
    }//GEN-LAST:event_uploadFileTabComponentShown

    private void encryptUploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptUploadButtonActionPerformed
        try {
            File policy=File.createTempFile("policy","");
            FileWriter fout=new FileWriter(policy);
            fout.write(policyTextArea.getText());
            fout.flush();
            fout.close();
            
            File file = File.createTempFile("run", ".sh");
            file.deleteOnExit();
            file.setExecutable(true);
            fout = new FileWriter(file);
            fout.write(new String("#!/bin/bash\n"));
            fout.write(new String("cp \"" + filepath + "\" \"" + tempDir + "\"\n"));
            fout.write(new String("cpabe-enc \""+tempDir+"/pub_key\" \""+tempDir+"/"+filename+"\" < \""+policy.getAbsolutePath()+"\"\n"));
            fout.flush();
            fout.close();
            String enc_output=executeCommandAndGetOutput(file.getAbsolutePath());
            System.err.println("Script: "+file.getAbsolutePath());
            if(enc_output.trim().equals("")){
                System.err.println("Correct");
                //read encrypted file
                File encryptedFile=new File(tempDir,filename+".cpabe");
                System.err.println(encryptedFile.getAbsolutePath());
                FileInputStream objFileInputStream = new FileInputStream(encryptedFile);
                //System.out.println(objFileInputStream.available());
                byte[] allBytes = new byte[objFileInputStream.available()];
                objFileInputStream.read(allBytes);
                objFileInputStream.close();
                
                EncryptedFile encFile=new EncryptedFile(filename+".cpabe",allBytes);
                Packet outputPacket=new Packet(7,(Object)encFile);
                try{
                    out.writeObject(outputPacket);
                    out.flush();
                    JOptionPane.showMessageDialog(null,"File Upload Complete.");  
                    filenameTextField.setText("");
                    policyTextArea.setText("");
                    encryptedFile.delete();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"File Encryption Unsuccessful.\nERROR: "+enc_output);
            } //System.out.println(enc_output);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_encryptUploadButtonActionPerformed

    private void fileSelectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileSelectionButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showDialog(MainView.this, "Select");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        File file = chooser.getSelectedFile();
        filepath = file.getAbsolutePath();
        filename=file.getName();
        System.out.println("filepath "+filepath+" filename "+filename);
        filenameTextField.setText(filename);
    }//GEN-LAST:event_fileSelectionButtonActionPerformed

    private void viewFilesTabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_viewFilesTabComponentShown
        try {
            out.writeObject(new Packet(8));
            Packet packet=(Packet)in.readObject();
            if(packet.getCode()==506){
                DefaultTableModel tableModel=(DefaultTableModel)viewFilesJTable.getModel();
                tableModel.setRowCount(0);
                String[] allFiles=(String[])packet.getObject();
                for(String f:allFiles){
                    Object[] tempFile={f,null};
                    tableModel.addRow(tempFile);
                }
                this.allFiles=allFiles;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_viewFilesTabComponentShown

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        Packet packet = new Packet(909);
        try {
            out.writeObject(packet);
            System.out.println("logout Successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void moveKeys() {
        ClassLoader objClassLoader = getClass().getClassLoader();
        String pub_key = objClassLoader.getResource("User/pub_key").getFile();
        String master_key = objClassLoader.getResource("User/master_key").getFile();
        try {
            File file = null;
            file = File.createTempFile("run", ".sh");
            file.deleteOnExit();
            file.setExecutable(true);
            FileWriter fout = new FileWriter(file);
            fout.write("#!/bin/bash\n");
            fout.write("cp \"" + pub_key + "\" \"" + tempDir + "\"\n");
            fout.write("cp \"" + master_key + "\" \"" + tempDir + "\"\n");
            fout.flush();
            fout.close();
            executeCommand(file.getAbsolutePath());
            /*
            file=new File("pub_key",tempDir);
            file.setReadable(true);
            file=new File("priv_key",tempDir);
            file.setReadable(true);
            */
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Needs editing in user attributes
    private void generateUserKey() {
        try {
            File file = null;
            file = File.createTempFile("run", ".sh");
            file.deleteOnExit();
            file.setExecutable(true);
            FileWriter fout = new FileWriter(file);
            fout.write("#!/bin/bash\n");
            //System.out.println("cpabe-keygen -o \"" + tempDir + "/priv_key\" \"" + tempDir + "/pub_key\" \"" + tempDir + "/master_key\" sysadmin it_dept");
            //String nationality=new String(user.getAttribute("nationality"));
            fout.write("cpabe-keygen -o \"" + tempDir + "/priv_key\" \"" + tempDir 
                    +"/pub_key\" \"" + tempDir + "/master_key\""
                    +" "+user.getAttribute("nationality").replace(" ","_")
                    +" "+user.getAttribute("role").replace(" ","_")
                    +" "+user.getAttribute("branch").replace(" ","_")
                    +" team_"+user.getAttribute("team").replace(" ","_"));
            System.err.println("cpabe-keygen -o \"" + tempDir + "/priv_key\" \"" + tempDir 
                    +"/pub_key\" \"" + tempDir + "/master_key\""
                    +" "+user.getAttribute("nationality").replace(" ","_")
                    +" "+user.getAttribute("role").replace(" ","_")
                    +" "+user.getAttribute("branch").replace(" ","_")
                    +" team_"+user.getAttribute("team").replace(" ","_"));
            fout.flush();
            fout.close();
            //System.out.println(file.getAbsolutePath());
            executeCommand(file.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void finalize() {
        try {
            executeCommand("rm -r " + tempDir);
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile(String file) {
        String s = null;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            s = br.readLine();
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    private void executeCommand(String path) {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String executeCommandAndGetOutput(String path) {
        StringBuffer output = new StringBuffer();
        try {
            Process proc = Runtime.getRuntime().exec(path); //Whatever you want to execute
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            proc.waitFor();
            //s=read.readLine();
            //if(s!=null) System.out.println("executeCommand Output: "+s);
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }
            //while (read.ready()) {
            //    System.out.println(read.readLine());
            //}
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    private String createTempFolder() {
        String s = null;
        try {
            Process proc = Runtime.getRuntime().exec("mktemp -d");
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            while (read.ready()) {
                s = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
    
    class PanelRenderer extends JPanel implements TableCellRenderer {
    
        public PanelRenderer(){
            setOpaque(true);
            init();
        }
        
        private void init(){
            setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
            add(new JButton(new ImageIcon(getClass().getResource("/User/unlock.png"))));
            pack();
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,
                                        boolean hasFocus,int row,int column){
            if ( isSelected ){
                setForeground( table.getSelectionForeground() );
                setBackground( table.getSelectionBackground() );
            }else{
                setForeground( table.getForeground() );
                setBackground( UIManager.getColor( "Button.background" ) );
            }
            return this;
        }
    }
    
    class PanelEditor extends DefaultCellEditor{
        protected JPanel panel;
        protected JButton button1,button2;
        protected int row,column;
        public PanelEditor(JCheckBox checkBox){
            super(checkBox);
            panel=new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
            button1=new JButton(new ImageIcon(getClass().getResource("/User/unlock.png")));
            button1.setOpaque(true);
            panel.add( button1 );
            button1.setActionCommand("Action 1");
            button1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    fireEditingStopped();
                    Packet outputPacket=new Packet(9,allFiles[row]);
                    try{
                        out.writeObject(outputPacket);
                        out.flush();
                        Packet inputPacket=(Packet)in.readObject();
                        if(inputPacket.getCode()==509){
                            EncryptedFile encFile=(EncryptedFile)inputPacket.getObject();
                            File newFile=new File(System.getProperty("user.home")+"/Desktop",encFile.getFilename());
                            newFile.createNewFile();
                            newFile.setWritable(true);
                            FileOutputStream objFileOutputStream= new FileOutputStream(newFile);
                            objFileOutputStream.write(encFile.getFileBytes());
                            objFileOutputStream.flush();
                            objFileOutputStream.close();
                            /*
                            System.out.println("cpabe-dec \""+tempDir+"/pub_key\" \""+tempDir+"/priv_key\" \""+newFile.getAbsolutePath()+"\"");
                            String result=executeCommandAndGetOutput("cpabe-dec \""+tempDir+"/pub_key\" \""+tempDir+"/priv_key\" \""+newFile.getAbsolutePath()+"\"");
                            */
                            String result=decrypt(newFile.getAbsolutePath());
                            System.out.println("File Decryption Results:"+result);
                            result.trim();
                            if(result.equals("")){
                                System.out.println("File Successfully Decrypted to Desktop");
                                JOptionPane.showMessageDialog(null,"File Successfully Decrypted to Desktop");
                            }else{
                                System.out.println("Your Attributes Do Not Satisfy the Required Policy");
                                JOptionPane.showMessageDialog(null,"Your Attributes Do Not Satisfy the Required Policy");
                                newFile.delete();
                            }
                        }
                    }catch(Exception exp){
                        exp.printStackTrace();
                    }
                    System.out.println("Checked Row: " + row + " Column: " + column);
                    System.out.println("File selected is "+allFiles[row]);
                }
            });
            panel.add(button1);
        }
        
        private String decrypt(String newFileString) {
        ClassLoader objClassLoader = getClass().getClassLoader();
        File file = null;
        try {
            file = File.createTempFile("run", ".sh");
            file.deleteOnExit();
            file.setExecutable(true);
            FileWriter fout = new FileWriter(file);
            fout.write("#!/bin/bash\n");
            fout.write("cpabe-dec \""+tempDir+"/pub_key\" \""+tempDir+"/priv_key\" \""+newFileString+"\"");
            fout.flush();
            fout.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        return executeCommandAndGetOutput(file.getAbsolutePath());
        }
    
        @Override
        public Component getTableCellEditorComponent(JTable jTable, Object value, boolean isSelected, int row, int column){
            if(isSelected){
                button1.setForeground(jTable.getSelectionForeground());
                button1.setBackground(jTable.getSelectionBackground());
            }
            else{
                button1.setForeground(jTable.getForeground());
                button1.setBackground(jTable.getBackground());
            }
            this.row=row;
            this.column=column;
            return panel;
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Tabs;
    private javax.swing.JButton encryptUploadButton;
    private javax.swing.JButton fileSelectionButton;
    private javax.swing.JTextField filenameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextArea policyTextArea;
    private javax.swing.JPanel uploadFileTab;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTable viewFilesJTable;
    private javax.swing.JPanel viewFilesTab;
    // End of variables declaration//GEN-END:variables
}
