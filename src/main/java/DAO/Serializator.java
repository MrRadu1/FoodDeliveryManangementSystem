package DAO;

import BLL.DeliveryService;

import java.io.*;

public class Serializator {

    public static void serialization(DeliveryService deliveryService){
        FileOutputStream f;
        try {
            f = new FileOutputStream("service.ser");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(deliveryService);
            o.close();
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    public static DeliveryService deserialize() {
        // Deserialization
        DeliveryService service =  new DeliveryService();
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("service.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            service = (DeliveryService)in.readObject();

            in.close();
            file.close();
        } catch(IOException ex) {
            ex.printStackTrace();
            //System.out.println("IOException is caught during deserialization!");
        } catch(ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught during deserialization!");
        }
        return service;
    }

}