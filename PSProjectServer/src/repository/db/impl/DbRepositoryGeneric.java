/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import domain.AbstractDomainObject;
import java.util.LinkedList;
import java.util.List;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;
import java.sql.*;

/**
 *
 * @author Aleksa
 */
public class DbRepositoryGeneric implements DbRepository<AbstractDomainObject>{

    @Override
    public List<AbstractDomainObject> getAll(AbstractDomainObject param, String uslov) throws Exception {
        List<AbstractDomainObject> lista = new LinkedList<>();
        String query = "SELECT * FROM " + param.vratiNazivTabele();
        if(uslov != null){
        query+=uslov;
        }
        System.out.println(query);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(query);
        lista = param.vratiListu(rs);
        
        rs.close();
        st.close();
        return lista;
    }

    @Override
    public void add(AbstractDomainObject param) throws Exception {
        String query = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKoloneZaUbacivanje() + 
                 " ) VALUES ( " + param.vratiVrednostiZaUbacivanje() + " )";
        System.out.println(query);
         Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
         st.executeUpdate(query);
         st.close();
        
    }

    @Override
    public void edit(AbstractDomainObject param) throws Exception {
        String query = "UPDATE " + param.vratiNazivTabele() + " SET " + 
                param.vratiVrednostiZaIzmenu();
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
         st.executeUpdate(query);
         st.close();
    }

    @Override
    public void delete(AbstractDomainObject param) throws Exception {
        String query = "DELETE FROM " + param.vratiNazivTabele()+ " WHERE " +   
                param.vratiPrimarniKljuc();
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
         st.executeUpdate(query);
         st.close();
    }

    @Override
    public List<AbstractDomainObject> getAll() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }
    
}
