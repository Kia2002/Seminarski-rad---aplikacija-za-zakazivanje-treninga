/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;
import configuration.Configuration;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Aleksa
 */
public class DbConnectionFactory {
    private static DbConnectionFactory instance;
    private Connection connection;
    
    
    public DbConnectionFactory() {
        
        try {
            if(connection == null || connection.isClosed()){
            String url = Configuration.getInstance().getProperty("url");
            String user = Configuration.getInstance().getProperty("user");
            String pass = Configuration.getInstance().getProperty("password");
            connection = DriverManager.getConnection(url,user,pass);
            connection.setAutoCommit(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DbConnectionFactory getInstance() {
        if(instance==null){
        instance = new DbConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
    
}
