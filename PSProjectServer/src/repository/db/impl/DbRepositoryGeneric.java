/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import java.util.LinkedList;
import java.util.List;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;
import java.sql.*;
import domain.ApstraktniDomenskiObjekat;

/**
 *
 * @author Aleksa
 */
public class DbRepositoryGeneric implements DbRepository<ApstraktniDomenskiObjekat>{

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new LinkedList<>();
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
    public void add(ApstraktniDomenskiObjekat param) throws Exception {
        String query = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKoloneZaUbacivanje() + 
                 " ) VALUES ( " + param.vratiVrednostiZaUbacivanje() + " )";
        System.out.println(query);
         Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
         st.executeUpdate(query);
         st.close();
        
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        String query = "UPDATE " + param.vratiNazivTabele() + " SET " + 
                param.vratiVrednostiZaIzmenu();
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
         st.executeUpdate(query);
         st.close();
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        String query = "DELETE FROM " + param.vratiNazivTabele()+ " WHERE " +   
                param.vratiPrimarniKljuc();
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
         st.executeUpdate(query);
         st.close();
    }
    
    @Override
    public ApstraktniDomenskiObjekat get(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        
        ApstraktniDomenskiObjekat novi = null;
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        if(uslov!=null){
            upit+=uslov; 
        }
        
        System.out.println(upit);
        
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        List<ApstraktniDomenskiObjekat> lista = param.vratiListu(rs);
        if (!lista.isEmpty()) {
            novi = lista.get(0);
        }
        
        
        rs.close();
        st.close();
        return novi;
        
    }
    
    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }
    
}
