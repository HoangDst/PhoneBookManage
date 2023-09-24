package io;

import model.Contact;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class SerializeFile {
    public static boolean saveFile(
            HashMap<Integer, Contact> data,
            String path){
        try {
            FileOutputStream fos =new FileOutputStream(path);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
            fos.close();
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    public static HashMap<Integer,Contact> readFile(String path){
        HashMap<Integer,Contact>data=new HashMap<Integer,Contact>();
        try {
            FileInputStream fis= new FileInputStream(path);
            ObjectInputStream ois=new ObjectInputStream(fis);
            Object object=ois.readObject();
            ois.close();
            fis.close();
            data=(HashMap<Integer, Contact>) object;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return data;
    }
}