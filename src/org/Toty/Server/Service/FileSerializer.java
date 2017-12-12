package org.Toty.Server.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 *
 * @author imagine5am
 */
public class FileSerializer {
        /*
        public static byte[] serialize(Object obj) throws IOException {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(obj);
            return out.toByteArray();
        }
        public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream is = new ObjectInputStream(in);
            return is.readObject();
        }
        */
    public static byte[] serialize(Object yourObject) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] yourBytes;
        try {
          out = new ObjectOutputStream(bos);   
          out.writeObject(yourObject);
          out.flush();
          yourBytes = bos.toByteArray();
          //...
        } finally {
          try {
            bos.close();
          } catch (IOException ex) {
            // ignore close exception
          }
        }
        return yourBytes;
    }

    public static Object deserialize(byte[] bytes) {
        Object o = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        try {
          in = new ObjectInputStream(bis);
          o = in.readObject(); 
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
          try {
            if (in != null) {
              in.close();
            }
          } catch (IOException ex) {
            ex.printStackTrace();
          }
          
        }
        return o;
    }
    
    public static byte[] toByteArray (Object obj){
      byte[] bytes = null;
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      try {
        ObjectOutputStream oos = new ObjectOutputStream(bos); 
        oos.writeObject(obj);
        oos.flush(); 
        oos.close(); 
        bos.close();
        bytes = bos.toByteArray ();
      }
      catch (IOException ex) {
        //TODO: Handle the exception
      }
      return bytes;
    }

    public static Object toObject (byte[] bytes){
      Object obj = null;
      try {
        ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
        ObjectInputStream ois = new ObjectInputStream (bis);
        obj = ois.readObject();
      }
      catch (IOException ex) {
        //TODO: Handle the exception
      }
      catch (ClassNotFoundException ex) {
        //TODO: Handle the exception
      }
      return obj;
    }

    /*
    // Convert an object to a byte array

private byte[] ObjectToByteArray(Object obj)

{

    if(obj == null)

        return null;

    BinaryFormatter bf = new BinaryFormatter();

    MemoryStream ms = new MemoryStream();

    bf.Serialize(ms, obj);

    return ms.ToArray();

}

// Convert a byte array to an Object

private Object ByteArrayToObject(byte[] arrBytes)

{

    MemoryStream memStream = new MemoryStream();

    BinaryFormatter binForm = new BinaryFormatter();

    memStream.Write(arrBytes, 0, arrBytes.Length);

    memStream.Seek(0, SeekOrigin.Begin);

    Object obj = (Object) binForm.Deserialize(memStream);

    return obj;


}
*/
    
}
    
