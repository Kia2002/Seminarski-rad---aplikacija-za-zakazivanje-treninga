/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aleksa
 */
public class Configuration {
   private static Configuration instance;
   private Properties configuration;
   
   private Configuration() throws IOException{
       try {
           configuration = new Properties();
           configuration.load(new FileInputStream("C:\\Users\\Aleksa\\Documents\\NetBeansProjects\\Projektovanje sotvera - seminarski\\PSProjectServer\\config\\config.properties"));
       } catch (FileNotFoundException ex) {
           ex.printStackTrace();
           Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
       }
   }

    public static Configuration getInstance() throws IOException {
        if(instance == null) {
        instance = new Configuration();
        }
        return instance;
    }

   public String getProperty(String key){
   return configuration.getProperty(key, "n/a");
   }
   
   public void setProperty(String key, String value){
   configuration.setProperty(key, value);
   }
   
   public void saveChanges(){
   try{
        configuration.store(new FileOutputStream("C:\\Users\\Aleksa\\Documents\\NetBeansProjects\\Projektovanje sotvera - seminarski\\PSProjectServer\\config\\config.properties"),null);
   }catch(IOException ex){
   ex.printStackTrace();
   }
   }
}
