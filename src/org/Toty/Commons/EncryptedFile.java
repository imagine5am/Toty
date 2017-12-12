package org.Toty.Commons;

import java.io.Serializable;

/**
 *
 * @author imagine5am
 */
public class EncryptedFile implements Serializable{
    private byte[] fileBytes;
    private String filename;
    
    public EncryptedFile(String filename,byte[] fileBytes){
        this.filename=filename;
        this.fileBytes=fileBytes;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
}
