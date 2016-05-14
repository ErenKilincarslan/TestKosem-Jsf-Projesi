/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controller;

import entity.Soru;
import entity.Test;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javaKodları.Baglanti;

/**
 *
 * @author celal
 */
public class SoruJpaController {

    public void create(Soru soru) {
         Baglanti b=new Baglanti();
        try {
            b.baglan();
            PreparedStatement ps=b.conn.prepareStatement(
                    "insert into soru (testid,soru) values (?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, soru.getTest().getTestid());
            ps.setString(2, soru.getSoru());
            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next())
                soru.setSoruid(rs.getInt(1));
        } catch (SQLException ex) {
            throw new Error(ex.getMessage());
        }
        finally{
            b.baglantiKes();
        }
    }
     public List<Soru> getAllSoruFromTestID(String test){
           Baglanti b = new Baglanti();
           List<Soru> list=new ArrayList<>();
        try {
            b.baglan();
            PreparedStatement ps = b.conn.prepareStatement(
                    "select * from soru where testid=?",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, test);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Soru s=new Soru(rs.getInt(1), rs.getString(3));
                list.add(s);
            }
        } catch (SQLException ex) {
            throw new Error(ex.getMessage());

        } finally {
            b.baglantiKes();
        }
        return list;
     }
    
}
